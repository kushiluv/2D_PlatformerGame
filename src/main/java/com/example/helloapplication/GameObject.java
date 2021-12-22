package com.example.helloapplication;

import javafx.scene.image.ImageView;

abstract class GameObject {
    private location Location;



    abstract location getLocation();

    abstract void if_collides(Hero hero);
}
