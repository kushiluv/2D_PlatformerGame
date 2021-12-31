package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class load {

    @FXML
    private AnchorPane loadmenu;
    @FXML
    private Button load2;

    @FXML
    private Button back;

    public load(Scene scene) {
        load2 = (Button) scene.lookup("#load2");
        back = (Button) scene.lookup("#back");
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
}
