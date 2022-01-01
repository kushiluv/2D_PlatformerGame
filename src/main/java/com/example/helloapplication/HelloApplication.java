package com.example.helloapplication;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @FXML
    private Button temp;



    @FXML
    private Button load1;

    @FXML
    private Button exit;
    @FXML
    private ListView listView;


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 1200, 600);
        temp = (Button) scene.lookup("#temp");
        listView = (ListView) scene.lookup("#listview");
        load1 = (Button) scene.lookup("#load1");
        exit = (Button) scene.lookup("#exit");

//        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
//                game game  = new game();
//                game.start(stage);
//            }
//        });
        
            listView = new ListView<>();
            ArrayList<String> temp = new LoadGame().getGames();
            String[] str = new String[temp.size()];
            System.out.println("dkjfbs");
            for (int i = 0; i < temp.size(); i++) {
                str[i] = temp.get(i);
            }
            for (String k : str) {
                System.out.println(k);
            }
            listView.getItems().addAll(str);
//            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//
//                @Override
//                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
//
//                    Object toload = listView.getSelectionModel().getSelectedItem();
//
////                myLabel.setText(currentFood);
//
//                }
//            });
        
        load1.setOnMouseClicked(e -> {
            load load = new load(scene);
            load.displayloadmenu();

//            load.getLoad2().setOnMouseClicked(r ->{
//                FileChooser fo = new FileChooser();
//                fo.setInitialDirectory(new File("src/main/resources/load"));
//                File selectedfile = fo.showOpenDialog(null);
//                if (selectedfile!=null){
//                    listView.getItems().add(selectedfile.get);
//                }else{
//                    System.out.println("Error opening the file");
//                }
//
//
//            });
        });
        exit.setOnMouseClicked(e -> {
            Platform.exit();
//            menu.getFinalexit().setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    Platform.exit();
//                }
//            });
//            menu.getQuitno().setOnMouseClicked(d -> {
//                menu.quitpaneinvisible();
//            });
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