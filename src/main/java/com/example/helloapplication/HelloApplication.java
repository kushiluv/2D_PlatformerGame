package com.example.helloapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @FXML
    private Button temp;



    @FXML
    private Button load1;


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 1200, 600);
        temp = (Button) scene.lookup("#temp");

        load1 = (Button) scene.lookup("#load");
//        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
//                game game  = new game();
//                game.start(stage);
//            }
//        });
        load load = new load(scene);

        load1.setOnMouseClicked(e -> {
            load.loadvisible();

        });
        load.getBack().setOnMouseClicked(e -> {
            load.loadinvisible();
        });

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