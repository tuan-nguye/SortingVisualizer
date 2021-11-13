package com.sorting;

import com.sorting.javafx.SortingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SortingApplication extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SortingApplication.class.getResource("sorting.fxml"));

        stage.setTitle("Sorting Application");
        stage.setScene(new Scene(loader.load()));

        /*
        stage.setOnShown(e -> {
            ((SortingController) loader.getController()).randomize();
        });

         */
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
