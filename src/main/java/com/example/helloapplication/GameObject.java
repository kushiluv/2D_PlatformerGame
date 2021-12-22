package com.example.helloapplication;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

abstract class GameObject {
    private location Location;



    abstract Bounds getLocation();

    abstract void if_collides(Hero hero);
}
