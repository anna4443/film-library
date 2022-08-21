package com.as;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static List<String> messages = new ArrayList<>();
    private static List<PrintWriter> users = new ArrayList<>();

    public static void main(String[] args) {
        int portNumber = 12345;

        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);

            //prihvaćanje klijenata
            while (true) {
                Socket socket = serverSocket.accept();

                //System.out.println("TEST");

                ClientProcessor cp = new ClientProcessor(socket);
                cp.start();

                System.out.println("Korisnik je spojen");
            }
        }
        catch (Exception ex){
            System.err.println("Could not listen on port: " + portNumber);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    static class ClientProcessor extends Thread{

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientProcessor(Socket socket) throws IOException {
            this.socket = socket;

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            ChatServer.addUser(out);
        }

        @Override
        public void run()
        {
            String input;
            try{
                while ((input = in.readLine()) != null) {

                    System.out.println("Received from client: " + input);

                    for (PrintWriter outClient : ChatServer.getUsers()) {
                        //outClient.write(input);
                        //outClient.flush();
                        //System.out.println("TEST");
                        outClient.println(input);
                        outClient.flush();
                    }
                }
            }
            catch (IOException ioex){
                ioex.printStackTrace();
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void addUser(PrintWriter user) {
        users.add(user);
    }

    private static List<PrintWriter> getUsers() {
        return users;
    }

}
