package com.example.helloapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class weaponchest {
    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest;
    final private ImageView chest1;
    final private ImageView chest2;
    final private ImageView chest3;
    final private ImageView chest4;
    private ArrayList<ImageView> chests_all;
    private Bounds boundschest;
    public weaponchest(Scene scene){
        chests_all = new ArrayList<>();
        chest1 = new ImageView("wep_0006 #56893.png");
        chest2 = new ImageView("wep_0007 #37947.png");
        chest3 = new ImageView("wep_0008 #30876.png");
        chest4 = new ImageView("wep_0009 #57652.png");
        chests = (Group) scene.lookup("#chests");
        defaultchest = (ImageView) scene.lookup("#defaultchest");
        chests_all.add(chest1);
        chests_all.add(chest2);
        chests_all.add(chest3);
        chests_all.add(chest4);
        boundschest =defaultchest.localToScene(defaultchest.getBoundsInLocal());

//        chests.setTranslateX(903);
//        chests.setTranslateY(313);
        chests.setScaleX(0.35);
        chests.setScaleY(0.35);
//        chests.setVisible(false);
        Timeline chestt = new Timeline();
        chestt.setCycleCount(1);

        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(200),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(chest1);
                    }
                }));
        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(600),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(chest2);
                    }
                }));
        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(chest3);
                    }
                }));
        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(1400),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(chest4);
                    }
                }));
        defaultchest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                defaultchest.setVisible(false);
                chestt.play();
                chestt.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.setVisible(false);

                    }
                });
            }
        });
    }
    public ArrayList<ImageView> getChests_all(){
        return chests_all;
    }
    public Bounds getchest(){

        return boundschest;
    }


}