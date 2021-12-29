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

    private boolean equipped;
    public throwingknives(Scene scene,String knifee){
        knife = (ImageView) scene.lookup(knifee);
        knife.setVisible(false);
        equipped = false;
    }
    public void run(TranslateTransition run, Bounds hero){
//        System.out.println("in knife");
        knife.setVisible(true);
        knife.setDisable(true);
        run.setDuration(Duration.seconds(0.25));
        run.setFromY(hero.getCenterY()-250);
        run.setFromX(hero.getMinX()-100);
        run.setAutoReverse(false);
        run.setByX(250);
        run.setCycleCount(1);
        run.setNode(knife);

        run.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                knife.setDisable(false);
                knife.setVisible(false);
            }
        });
    }
    public ImageView getKnife(){
        return knife;
    }
    public void setEquippedTrue(){
        equipped = true;
    }
    public void setEquippedfalse(){
        equipped = false;
    }

    public boolean isEquipped() {
        return equipped;
    }
}
