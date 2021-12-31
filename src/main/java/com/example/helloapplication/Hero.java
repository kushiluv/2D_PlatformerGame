package com.example.helloapplication;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Hero extends GameObject implements serializable{
    private static final long serialVersionUID = 45L;

    //    private Helmet helmet;
    private boolean IsAlive;
    private int gravity;
    @FXML
    private ImageView hero;

    public Hero(Scene scene){
        this.hero = (ImageView) scene.lookup("#hero");

    }

    public void set_hero_jump(TranslateTransition jump){
        jump.setDuration(Duration.millis(600));
        jump.setByY(-200);
        jump.setNode(hero);
    }
    public void set_hero_fall(TranslateTransition fall){
        fall.setDuration(Duration.millis(1800));
        fall.setByY(600);
        fall.setCycleCount(1);
        fall.setAutoReverse(false);
        fall.setNode(hero);

    }

    public ImageView getHero() {
        return hero;
    }

    @Override
    public Bounds getLocation() {
        Bounds boundshero = hero.localToScene(hero.getBoundsInParent());
        return boundshero;
    }

    @Override
    void if_collides(Hero hero) {

    }
    void death(TranslateTransition dead, ScaleTransition big , RotateTransition rotate , ParallelTransition all){

        dead.setDuration(Duration.millis(2000));
        dead.setByY(-800);
        dead.setCycleCount(1);
        dead.setAutoReverse(false);
        dead.setNode(hero);
        rotate.setByAngle(360f);
        rotate.setCycleCount(15);
        rotate.setDuration(Duration.seconds(0.133));
        rotate.setNode(hero);
        big.setByX(.2);
        big.setByY(.2);
        big.setNode(hero);
        big.setDuration(Duration.seconds(2));


    }
}
