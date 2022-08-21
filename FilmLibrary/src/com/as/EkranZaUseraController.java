package com.as;

import com.as.model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EkranZaUseraController implements Initializable {

    @FXML
    private Button btnSend;

    @FXML
    private TextArea txtaDobivanjePoruka;

    @FXML
    private TextField txtSlanjePoruke;

    private boolean online = true;

    private String chatMessages = "";

    private PrintWriter outputChat;

    private BufferedReader inputChat;

    private Socket socket;

    private ChatProcess chatHandler;

    public void prikaziFilmove(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("TableFilm.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex)
        {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("TEST");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try{
               connectChat();
            }
            catch (Exception ex){
                System.out.println("Can't connect to chat");
                ex.printStackTrace();
            }
    }

    public void sendMessage(ActionEvent event) {
        if (online)
        {
            outputChat.println(txtSlanjePoruke.getText() + "\n\n");
            outputChat.flush();
        }

        txtSlanjePoruke.setText("");
    }

    private void connectChat() throws IOException {
            Socket socket = new Socket("localhost", 12345);
            chatHandler = new ChatProcess(socket);
            chatHandler.start();
    }

    class ChatProcess extends Thread
    {

        public ChatProcess (Socket sc)
        {
             socket = sc;

            try {
                outputChat = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
                inputChat = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void  run()
        {
            try {
                String msg;

                while (online) {
                    while ((msg = inputChat.readLine()) != null) {

                        txtaDobivanjePoruka.appendText(msg + "\n\n");

                    }
                }

            }
            catch (Exception ex){
                //ex.printStackTrace();
                System.out.println("Exception is coming: " + ex.getMessage());
            }
            finally {
                try {
                    socket.close();
                    outputChat.close();
                    inputChat.close();
                } catch (IOException ioex) {
                    System.out.println(ioex.getMessage());
                }
            }
        }
            
    }
}

