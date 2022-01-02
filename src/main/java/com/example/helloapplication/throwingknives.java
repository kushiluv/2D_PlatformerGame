package com.example.helloapplication;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class throwingknives extends GameObject implements serializable,Weapons {
    private static final long serialVersionUID = 47L;

    @FXML
    private ImageView knife;

    @FXML
    private ImageView knifeupgrade;

    @FXML
    private ImageView knifeicon;
    private int upgrade_level;
    private int range;
    private boolean equipped;
    public throwingknives(Scene scene,String knifee,String knifeupgradee) {
        range = 250;
        upgrade_level= 0;
        knife = (ImageView) scene.lookup(knifee);
        knifeupgrade = (ImageView) scene.lookup(knifeupgradee);
        knife.setVisible(false);
        knifeicon = (ImageView) scene.lookup("#knifeicon");
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
        run.setByX(range);
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
    public void upgrade(){
        knife.setImage(knifeupgrade.getImage());
        range = 400;
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
    public int getUpgrade_level() {
        return upgrade_level;
    }
    public void setUpgrade_level(int i){
        upgrade_level=i;
    }
    public void setKnifeicon(){
        knifeicon.setVisible(true);
    }

    @Override
    Bounds getLocation() {
        return knife.getBoundsInParent();
    }

    @Override
    void if_collides(Hero hero) {

    }
}
