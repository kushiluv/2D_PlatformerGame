package com.example.helloapplication;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class load {

    @FXML
    private AnchorPane loadmenu;
    @FXML
    private Button load2;

    @FXML
    private Button back;

    @FXML
    private ListView<String> listView;

    private LoadGame loadGame;
    public load(Scene scene) {
        loadmenu = (AnchorPane) scene.lookup("#loadmenu");
        load2 = (Button) scene.lookup("#load2");
        back = (Button) scene.lookup("#back");
        listView = (ListView<String>) scene.lookup("#listview");
    }

    public void displayloadmenu(){
        loadvisible();
        System.out.println("Test 1");
        menufunctions();
//        initializ();


    }

    public void menufunctions(){
        back.setOnMouseClicked(e -> {
            loadinvisible();
        });



    }
    public void loadvisible(){
        loadmenu.setVisible(true);
    }
    public void loadinvisible(){
        loadmenu.setVisible(false);
    }

    public AnchorPane getLoadmenu() {
        return loadmenu;
    }

    public Button getLoad2() {
        return load2;
    }

    public Button getBack() {
        return back;
    }
    private String toload;


//    public void initializ() {
//         listView = new ListView<>();
//        ArrayList<String> temp = new LoadGame().getGames();
//        String[] str = new String[temp.size()];
//        System.out.println("dkjfbs");
//        for (int i = 0; i < temp.size(); i++) {
//            str[i] = temp.get(i);
//        }
//        for (String k : str) {
//            System.out.println(k);
//        }
//         listView.getItems().addAll(str);
//         listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//
//            @Override
//            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
//
//                toload = listView.getSelectionModel().getSelectedItem();
//
////                myLabel.setText(currentFood);
//
//            }
//        });
//    }
}
