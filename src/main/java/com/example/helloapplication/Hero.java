package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends GameObject {
//    private Helmet helmet;
    private boolean IsAlive;
    private int gravity;
    @FXML
    private ImageView hero;

    public Hero(Scene scene){
        super();
        this.hero = (ImageView) scene.lookup("#hero");

    }


    public ImageView getHero() {
        return hero;
    }

    @Override
    location getLocation() {
        return null;
    }

    @Override
    void if_collides() {

    }
}
