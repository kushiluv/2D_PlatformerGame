package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero  {
//    private Helmet helmet;
    private boolean IsAlive;
    private int gravity;
    @FXML
    private ImageView hero;

    public Hero(Scene scene){
        this.hero = (ImageView) scene.lookup("#hero");

    }

    public ImageView getHero() {
        return hero;
    }
}
