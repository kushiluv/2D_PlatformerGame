package com.example.helloapplication;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class coinchest extends GameObject implements chests, Serializable {
    private static final long serialVersionUID = 42L;
    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest;
    final private ImageView c1;
    final private ImageView c2;
    final private ImageView c3;
    final private ImageView c4;
    final private ImageView c5;
    final private ImageView c8;
    final private ImageView c9;
    final private ImageView c10;
    final private ImageView c11;

    private ArrayList<ImageView> cc_all;
    private Bounds boundschest;
    private boolean open;
    private Timeline cc;

    public coinchest(Scene scene,String group,String defaultc){
        
        cc_all = new ArrayList<>();
        c1 = new ImageView("Pirate_chest__openning_001.png");
        c2 = new ImageView("Pirate_chest__openning_002.png");
        c3 = new ImageView("Pirate_chest__openning_003.png");
        c4 = new ImageView("Pirate_chest__openning_004.png");
        c5 = new ImageView("Pirate_chest__openning_005.png");
        c8 = new ImageView("Pirate_chest__openning_008.png");
        c9 = new ImageView("Pirate_chest__openning_009.png");
        c10 = new ImageView("Pirate_chest__openning_010.png");
        c11 = new ImageView("Pirate_chest_gold.png");
        

        chests = (Group) scene.lookup(group);
        open = false ;
        defaultchest = (ImageView) scene.lookup(defaultc);

        cc_all.add(c1);
        cc_all.add(c2);
        cc_all.add(c3);
        cc_all.add(c4);
        cc_all.add(c5);
        cc_all.add(c8);
        cc_all.add(c9);
        cc_all.add(c10);
        cc_all.add(c11);




        boundschest =defaultchest.localToScene(defaultchest.getBoundsInLocal());

//        chests.setTranslateX(903);
//        chests.setTranslateY(313);
        chests.setScaleX(0.35);
        chests.setScaleY(0.35);
//        chests.setVisible(false);
        cc = new Timeline();
        cc.setCycleCount(1);

        cc.getKeyFrames().add(new KeyFrame(Duration.millis(160),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c1);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(320),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c2);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(480),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c3);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(640),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c4);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(800),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c5);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(960),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c8);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(1120),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c9);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(1280),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c10);
                    }
                }));
        cc.getKeyFrames().add(new KeyFrame(Duration.millis(1440),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(c11);
                    }
                }));


    }
    public void run(){
        defaultchest.setVisible(false);
        cc.play();
        cc.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chests.setVisible(false);

            }
        });

    }
    public ArrayList<ImageView> getChests_all(){
        return cc_all;
    }
    public Bounds getchest(){

        return boundschest;
    }
    public ImageView chestimg(){
        return defaultchest;
    }
    public void setOpen(){
        open = true;
    }
    public boolean getopen(){
        return open;
    }


    @Override
    Bounds getLocation() {
        return defaultchest.getBoundsInParent();
    }

    @Override
    void if_collides(Hero hero) {

    }
}
