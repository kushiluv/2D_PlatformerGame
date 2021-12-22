package com.example.helloapplication;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Hero extends GameObject{
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
        fall.setDuration(Duration.millis(12));
        fall.setByY(4);
        fall.setCycleCount(1);
        fall.setAutoReverse(false);
        fall.setNode(hero);

    }

    public ImageView getHero() {
        return hero;
    }

    @Override
    public Bounds getLocation() {
        Bounds boundshero = hero.localToScene(hero.getBoundsInLocal());
        return boundshero;
    }

    @Override
    void if_collides(Hero hero) {

    }
}
