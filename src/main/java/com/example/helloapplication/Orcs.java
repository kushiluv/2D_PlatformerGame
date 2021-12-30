package com.example.helloapplication;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Orcs extends GameObject {
    @FXML
    private ImageView grorc;

    @FXML
    private ImageView death;

    public Orcs(Scene scene, String string){
        this.grorc = (ImageView) scene.lookup(string);
        this.death = (ImageView) scene.lookup("#death");
    }
    public ImageView getHero() {
        return grorc;
    }

    public abstract void set_orc_jump(TranslateTransition orcjump);

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

    void if_collides(Hero hero) {

    }

}
