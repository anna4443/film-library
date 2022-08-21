package com.as;

import com.as.handlers.JndiPropertiesHandler;
import com.as.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.util.*;


public class Main extends Application {

    private static final String WIDTH = "WIDTH";
    private static final String HEIGHT = "HEIGHT";
    public static List<Movie> movies = new ArrayList<>();
        public static List<Member> members = new ArrayList<>();
        public static List<Loan> loans = new ArrayList<>();
        public static List<Overdue> overdues = new ArrayList<>();

        public static final List<Korisnik> repozitorijKorisnika = new ArrayList<>();

        @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        Scene scene = new Scene(root);
       // scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());


        stage.setScene(scene);


        Properties props = JndiPropertiesHandler.getJNDIProps();
        if (props != null) {
            try {
                double width = Double.parseDouble(props.getProperty(WIDTH));
                double height = Double.parseDouble(props.getProperty(HEIGHT));
                stage.setWidth(width);
                stage.setWidth(height);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        stage.show();

            GridPane gridPane = new GridPane();
            gridPane.getStyleClass().addAll("grid", "gridPane");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
