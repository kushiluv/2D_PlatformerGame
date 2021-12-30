package com.example.helloapplication;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Red_Orc extends Orcs {
    public Red_Orc(Scene scene, String string) {
        super(scene, string);
    }


    public void set_orc_jump(TranslateTransition orcjump){
        orcjump.setDuration(Duration.seconds(0.55));
        orcjump.setByY(-100);
        orcjump.setCycleCount(Animation.INDEFINITE);
        orcjump.setAutoReverse(true);
        orcjump.setNode(super.getHero());
    }

    @Override
    Bounds getLocation() {
        return null;
    }

    @Override
    void if_collides(Hero hero) {
        return;
    }



}
