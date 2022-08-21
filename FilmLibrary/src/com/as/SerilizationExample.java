package com.as;

import com.as.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerilizationExample {

    private static final String FILE_PATH = "membersandmovies.ser";
    private static final String FILE_PATHTWO = "members.ser";
    private static final String FILE_PATHTHREE = "loans.ser";
    private static final String FILE_PATHFOUR = "overdues.ser";

    public static void main(String[] args) {
        //List<AllInformations> allInfo = new ArrayList<>();
        //allInfo.add(new AllInformations(new Korisnik(), new Loan(), new Member(), new Movie(), new Overdue()));

       // writeMovieSerialised();

//        try (ObjectOutputStream stream = new ObjectOutputStream(
//                new FileOutputStream(FILE_PATH))) {
//            //stream.writeObject(allInfo);
//            stream.writeObject(Main.movies);
//        } catch (IOException ex) {
//            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {

            Object object = inStream.readObject();

            if (object instanceof ArrayList) {
//                List<AllInformations> allInformations = (List<AllInformations>) object;
//
//                for (AllInformations info : allInformations) {
//                    System.out.println(info.getKorisnik() + " " + info.getLoan() + " " + info.getMember() + " " +
//                    info.getMovie() + " " + info.getOverdue());
//                }
                List<Movie> allInformations= (List<Movie>)object;
                for (Movie m: allInformations) {
                    System.out.println(m.getName());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_PATHTWO))) {

            Object object = inStream.readObject();

            if (object instanceof ArrayList) {
//                List<AllInformations> allInformations = (List<AllInformations>) object;
//
//                for (AllInformations info : allInformations) {
//                    System.out.println(info.getKorisnik() + " " + info.getLoan() + " " + info.getMember() + " " +
//                    info.getMovie() + " " + info.getOverdue());
//                }
                List<Member> allInfoMb= (List<Member>)object;
                for (Member mb: allInfoMb) {
                    System.out.println(mb.getName());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_PATHTHREE))) {

            Object object = inStream.readObject();

            if (object instanceof ArrayList) {
//                List<AllInformations> allInformations = (List<AllInformations>) object;
//
//                for (AllInformations info : allInformations) {
//                    System.out.println(info.getKorisnik() + " " + info.getLoan() + " " + info.getMember() + " " +
//                    info.getMovie() + " " + info.getOverdue());
//                }
                List<Loan> allInfoLoan= (List<Loan>)object;
                java.util.Date posudba = new java.util.Date();
                for (Loan l: allInfoLoan) {
                    posudba = l.getDateLoan();
                    System.out.println(posudba);
                    System.out.println(l.getMemeberId());
                    System.out.println(l.getMovieId());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_PATHFOUR))) {

            Object object = inStream.readObject();

            if (object instanceof ArrayList) {
//                List<AllInformations> allInformations = (List<AllInformations>) object;
//
//                for (AllInformations info : allInformations) {
//                    System.out.println(info.getKorisnik() + " " + info.getLoan() + " " + info.getMember() + " " +
//                    info.getMovie() + " " + info.getOverdue());
//                }
                List<Overdue> allInfoOverdue= (List<Overdue>)object;
                for (Overdue o: allInfoOverdue) {
                    System.out.println(o.getMovieId());
                    System.out.println(o.getMemberId());
                    System.out.println(o.getCharged());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeMovieSerialised(List<Movie> list) {
        try (ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            //stream.writeObject(allInfo);
            stream.writeObject(list);
        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void writeMemberSerialsed(List<Member> list) {
        try (ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(FILE_PATHTWO))) {
            //stream.writeObject(allInfo);
            stream.writeObject(list);
        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static void writeLoans(List<Loan> list) {
        try (ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(FILE_PATHTHREE))) {
            //stream.writeObject(allInfo);
            stream.writeObject(list);
        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeOverdues(List<Overdue> list) {
        try (ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(FILE_PATHFOUR))) {
            //stream.writeObject(allInfo);
            stream.writeObject(list);
        } catch (IOException ex) {
            Logger.getLogger(SerilizationExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
