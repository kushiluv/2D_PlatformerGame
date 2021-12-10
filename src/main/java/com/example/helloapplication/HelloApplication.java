package com.example.helloapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }
    public void scene2(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("gamescene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        game game  = new game();
        game.start(stage);

    }

    public static void main(String[] args) {
        launch();
    }
}