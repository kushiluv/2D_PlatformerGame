package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Green_Orc extends GameObject {
    //    private Helmet helmet;
    private boolean IsAlive;
    @FXML
    private ImageView grorc;

    public Green_Orc(Scene scene){
        super();
        this.grorc = (ImageView) scene.lookup("#orc");
    }

    public ImageView getHero() {
        return grorc;
    }

    @Override
    location getLocation() {
        return null;
    }

    @Override
    void if_collides() {

    }
}