package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class menu {
    @FXML
    private Pane pausepane;

    @FXML
    private ImageView pause;

    @FXML
    private Button resume;



    private Button save;

    public menu(Scene scene){
        pause = (ImageView) scene.lookup("#pause");
        pausepane = (Pane) scene.lookup("#pausepane");
        resume = (Button) scene.lookup("#resume");

        save = (Button) scene.lookup("#save");

    }
    public ImageView getPause(){
        return pause;
    }
    public Button getResume(){
        return resume;
    }

    public Button getSave() {
        return save;
    }


    public Pane getPausepane() {
        return pausepane;
    }
    public void panevisible(){
        pausepane.setVisible(true);
    }
    public void paneinvisible(){
        pausepane.setVisible(false);
    }
}
