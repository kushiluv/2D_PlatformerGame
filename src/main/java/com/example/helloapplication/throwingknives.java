package com.example.helloapplication;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class throwingknives {
    @FXML
    private ImageView knife;
    public throwingknives(Scene scene,String knifee){
        knife = (ImageView) scene.lookup(knifee);
        knife.setVisible(false);
    }
    public void run(TranslateTransition run, Bounds hero){
        System.out.println("in knife");
        knife.setVisible(true);
        run.setDuration(Duration.seconds(0.25));
        run.setFromY(hero.getCenterY()-250);
        run.setFromX(hero.getMinX()-100);
        run.setAutoReverse(false);
        run.setByX(300);
        run.setCycleCount(1);
        run.setNode(knife);

        run.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                knife.setVisible(false);
            }
        });
    }
    public ImageView getKnife(){
        return knife;
    }
}
