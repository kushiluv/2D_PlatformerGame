package com.example.helloapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public interface chests {
    public void run();
    public ArrayList<ImageView> getChests_all();
    public ImageView chestimg();
    public void setOpen();
    public boolean getopen();

}
