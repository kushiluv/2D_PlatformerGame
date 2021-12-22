package com.example.helloapplication;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class islands extends GameObject {
    @FXML
    private ImageView island;

    @FXML
    private ImageView island1;

    @FXML
    private ImageView island2;

    private ArrayList<ImageView> islands;
    private ArrayList<Bounds> islands_bounds;

    public islands(Scene scene){
        islands = new ArrayList<>();
        island = (ImageView) scene.lookup("#island");
        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        islands.add(island);
        islands.add(island1);
        islands.add(island2);
    }

    public ArrayList<ImageView> getIslands() {
        return islands;
    }

    @Override
    public Bounds getLocation() {
        return null;
    }
    public ArrayList<Bounds> getALLislands(){
        Bounds boundsisland = island.localToScene(island.getBoundsInLocal());
        Bounds boundsisland1 = island1.localToScene(island.getBoundsInLocal());
        Bounds boundsisland2 = island2.localToScene(island.getBoundsInLocal());
        islands_bounds = new ArrayList<>();
        islands_bounds.add(boundsisland);
        islands_bounds.add(boundsisland1);
        islands_bounds.add(boundsisland2);
        return islands_bounds;
    }

    @Override
    void if_collides(Hero hero) {

    }
}
