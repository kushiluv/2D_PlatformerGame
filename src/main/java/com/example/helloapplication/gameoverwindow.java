package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class gameoverwindow {
    @FXML
    private Pane gameover;

    @FXML
    private Button quitbutton;

    @FXML
    private Button finalexit;

    @FXML
    private Pane quitpane;

    public gameoverwindow(Scene scene){
        gameover = (Pane) scene.lookup("#gameover");
        quitpane = (Pane) scene.lookup("#quitpane");
        quitbutton = (Button) scene.lookup("#quitbutton");
        finalexit = (Button) scene.lookup("#finalexit");
    }

    public Pane getGameover() {
        return gameover;
    }

    public Button getQuitbutton() {
        return quitbutton;
    }

    public Button getFinalexit() {
        return finalexit;
    }

    public Pane getQuitpane() {
        return quitpane;
    }
    public void quitpanevisible(){
        quitpane.setVisible(true);
    }
    public void quitpaneinvisible(){
        quitpane.setVisible(false);
    }
    public void gameoverpanevisible(){
        gameover.setVisible(true);
    }
    public void gameoverpaneinvisible(){
        gameover.setVisible(false);
    }
}
