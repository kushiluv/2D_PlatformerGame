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

    @FXML
    private ImageView island6;

    @FXML
    private ImageView island7;

    @FXML
    private ImageView island8;

    @FXML
    private ImageView island9;

    @FXML
    private ImageView island10;

    @FXML
    private ImageView island11;

    @FXML
    private ImageView island12;
    @FXML
    private ImageView island13;


    private ArrayList<ImageView> islands;
    private ArrayList<Bounds> islands_bounds;

    public islands(Scene scene){
        islands = new ArrayList<>();
        island = (ImageView) scene.lookup("#island");
        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        island3 = (ImageView) scene.lookup("#island3");
        island3.setFitWidth(170);
        island4 = (ImageView) scene.lookup("#island4");
        island5 = (ImageView) scene.lookup("#island5");
        island6 = (ImageView) scene.lookup("#island6");
        island7 = (ImageView) scene.lookup("#island7");
        island8 = (ImageView) scene.lookup("#island8");
        island9 = (ImageView) scene.lookup("#island9");
        island10 = (ImageView) scene.lookup("#island10");
        island11 = (ImageView) scene.lookup("#island11");
        island12 = (ImageView) scene.lookup("#island12");
        island13 = (ImageView) scene.lookup("#island13");

        islands.add(island);
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        islands.add(island4);
        islands.add(island5);
        islands.add(island6);
        islands.add(island7);
        islands.add(island8);
        islands.add(island9);
        islands.add(island10);
        islands.add(island11);
        islands.add(island12);
        islands.add(island13);
    }

    public ArrayList<ImageView> getIslands() {
        return islands;
    }

    @Override
    public Bounds getLocation() {
        return null;
    }
    public ArrayList<Bounds> getALLislands(){
        Bounds boundsisland = island.localToScene(island.getBoundsInParent());
        Bounds boundsisland1 = island1.localToScene(island.getBoundsInParent());
        Bounds boundsisland2 = island2.localToScene(island.getBoundsInParent());
        Bounds boundsisland3 = island3.localToScene(island.getBoundsInParent());
        Bounds boundsisland4 = island4.localToScene(island.getBoundsInParent());
        Bounds boundsisland5 = island5.localToScene(island.getBoundsInParent());
        Bounds boundsisland6 = island6.localToScene(island.getBoundsInParent());
        Bounds boundsisland7 = island7.localToScene(island.getBoundsInParent());
        Bounds boundsisland8 = island8.localToScene(island.getBoundsInParent());
        Bounds boundsisland9 = island9.localToScene(island.getBoundsInParent());
        Bounds boundsisland10 = island10.localToScene(island.getBoundsInParent());
        Bounds boundsisland11 = island11.localToScene(island.getBoundsInParent());
        Bounds boundsisland12 = island12.localToScene(island.getBoundsInParent());
        Bounds boundsisland13 = island13.localToScene(island.getBoundsInParent());
        islands_bounds = new ArrayList<>();
        islands_bounds.add(boundsisland);
        islands_bounds.add(boundsisland1);
        islands_bounds.add(boundsisland2);
        islands_bounds.add(boundsisland3);
        islands_bounds.add(boundsisland4);
        islands_bounds.add(boundsisland5);
        islands_bounds.add(boundsisland6);
        islands_bounds.add(boundsisland7);
        islands_bounds.add(boundsisland8);
        islands_bounds.add(boundsisland9);
        islands_bounds.add(boundsisland10);
        islands_bounds.add(boundsisland11);
        islands_bounds.add(boundsisland12);
        islands_bounds.add(boundsisland13);

        return islands_bounds;
    }

    @Override
    void if_collides(Hero hero) {

    }
}
