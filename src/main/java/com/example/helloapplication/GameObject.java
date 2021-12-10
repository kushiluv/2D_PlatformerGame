package com.example.helloapplication;

import javafx.scene.image.ImageView;

abstract class GameObject {
    private location Location;

    GameObject(location loc){
        this.Location = loc;
    }

    abstract location getLocation();

    abstract void if_collides();
}
