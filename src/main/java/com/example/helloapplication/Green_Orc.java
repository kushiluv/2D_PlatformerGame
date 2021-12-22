package com.example.helloapplication;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    public void set_orc_jump(TranslateTransition orcjump){
        orcjump.setDuration(Duration.seconds(0.55));
        orcjump.setByY(-100);
        orcjump.setCycleCount(Animation.INDEFINITE);
        orcjump.setAutoReverse(true);
        orcjump.setNode(grorc);
    }

    @Override
    Bounds getLocation() {
        return null;
    }

    @Override
    void if_collides(Hero hero) {

    }


}