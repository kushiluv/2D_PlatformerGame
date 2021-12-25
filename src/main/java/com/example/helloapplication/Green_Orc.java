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

public class Green_Orc extends GameObject {
    //    private Helmet helmet;
    private boolean IsAlive;
    @FXML
    private ImageView grorc;
    private Image deathimg;
    @FXML
    private ImageView death;

    public Green_Orc(Scene scene){
        super();

        this.grorc = (ImageView) scene.lookup("#orc");
        this.death = (ImageView) scene.lookup("#death");
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
    void death(TranslateTransition death){
        grorc.setImage(this.death.getImage());
        death.setDuration(Duration.seconds(0.5));
        death.setByY(400);
        death.setCycleCount(1);
        death.setAutoReverse(false);
        death.setNode(grorc);

        death.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grorc.setVisible(false);
            }
        });
    }


}