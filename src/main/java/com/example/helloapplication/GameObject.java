package com.example.helloapplication;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

abstract class GameObject {

    abstract Bounds getLocation();
    abstract void if_collides(Hero hero);


}
