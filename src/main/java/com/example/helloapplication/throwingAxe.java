package com.example.helloapplication;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class throwingAxe {
    @FXML
    private ImageView axe;

    private boolean equipped;
    public throwingAxe(Scene scene,String knifee){
        axe = (ImageView) scene.lookup(knifee);
        axe.setVisible(false);
        equipped = false;
    }
    public void run(RotateTransition rotate, ParallelTransition bac, ParallelTransition forw , TranslateTransition back, TranslateTransition run, SequentialTransition forward, Bounds hero){
//        System.out.println("in knife");

        axe.setVisible(true);
        axe.setDisable(true);
        rotate.setByAngle(360f);
        rotate.setCycleCount(5);
        rotate.setDuration(Duration.seconds(0.1));
        rotate.setNode(axe);
        run.setDuration(Duration.seconds(0.5));
        run.setFromY(hero.getCenterY()-250);
        run.setFromX(hero.getMinX()-100);
        run.setAutoReverse(false);
        run.setByX(250);
        run.setCycleCount(1);
        run.setNode(axe);


        back.setDuration(Duration.seconds(0.5));
        back.setToX(hero.getMinX()-100);
        back.setToY(hero.getCenterY()-200);

        back.setNode(axe);
        forward.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                axe.setDisable(false);
                axe.setVisible(false);
            }
        });


    }
    public ImageView getKnife(){
        return axe;
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