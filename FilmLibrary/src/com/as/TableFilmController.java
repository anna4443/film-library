package com.as;

import com.as.dal.DataSourceSingleton;
import com.as.model.Movie;
import com.as.model.MovieUtility;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TableFilmController implements Initializable {

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, String> col_name;

    @FXML
    private TableColumn<Movie, String> col_media;

    @FXML
    private TableColumn<Movie, String> col_director;

    @FXML
    private TableColumn<Movie, String> col_leadingActor;

    @FXML
    private TableColumn<Movie, String> col_genre;

    private ObservableList<Movie> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        DataSource dataSource = DataSourceSingleton.getInstance();
        try {
        Connection con = dataSource.getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("select Name, Media, Director, LeadingActor, Genre from Movie");
        while (resultSet.next()) {
                oblist.add(
                        new Movie(
                                resultSet.getString("Name"),
                                resultSet.getString("Media"),
                                resultSet.getString("Director"),
                                resultSet.getString("LeadingActor"),
                                resultSet.getString("Genre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col_media.setCellValueFactory(new PropertyValueFactory<>("Media"));
        col_director.setCellValueFactory(new PropertyValueFactory<>("Director"));
        col_leadingActor.setCellValueFactory(new PropertyValueFactory<>("LeadingActor"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    MovieUtility.assignRateToRndMovie(oblist);
                }
            }
        }).start();


        tableView.setItems(oblist);
    }
}
