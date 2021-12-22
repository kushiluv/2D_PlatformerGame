package com.example.helloapplication;

import javafx.fxml.FXML;
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
    location getLocation() {
        return null;
    }

    @Override
    void if_collides(Hero hero) {

    }
}
