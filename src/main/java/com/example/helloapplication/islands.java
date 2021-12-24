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
    @FXML
    private ImageView island3;

    @FXML
    private ImageView island4;

    @FXML
    private ImageView island5;

    private ArrayList<ImageView> islands;
    private ArrayList<Bounds> islands_bounds;

    public islands(Scene scene){
        islands = new ArrayList<>();
        island = (ImageView) scene.lookup("#island");
        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        island3 = (ImageView) scene.lookup("#island3");
        island4 = (ImageView) scene.lookup("#island4");
        island5 = (ImageView) scene.lookup("#island5");
        islands.add(island);
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        islands.add(island4);
        islands.add(island5);
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
        Bounds boundsisland3 = island3.localToScene(island.getBoundsInLocal());
        Bounds boundsisland4 = island4.localToScene(island.getBoundsInLocal());
        Bounds boundsisland5 = island5.localToScene(island.getBoundsInLocal());
        islands_bounds = new ArrayList<>();
        islands_bounds.add(boundsisland);
        islands_bounds.add(boundsisland1);
        islands_bounds.add(boundsisland2);
        islands_bounds.add(boundsisland3);
        islands_bounds.add(boundsisland4);
        islands_bounds.add(boundsisland5);
        return islands_bounds;
    }

    @Override
    void if_collides(Hero hero) {

    }
}
