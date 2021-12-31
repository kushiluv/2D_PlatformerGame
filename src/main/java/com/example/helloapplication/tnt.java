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

import java.util.ArrayList;

public class tnt implements serializable {
    private static final long serialVersionUID = 33L;

    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest,knifeicon;
    final private ImageView tnt1;
    final private ImageView tnt2;
    final private ImageView tnt3;
    final private ImageView tnt4;
    final private ImageView tnt5;
    final private ImageView tnt6;
    final private ImageView tnt7;
    final private ImageView tnt8;
    final private ImageView tnt9;
    final private ImageView tnt10;
    final private ImageView tnt11;
    final private ImageView tnt12;
    final private ImageView tnt13;
    final private ImageView tnt14;
    final private ImageView tnt15;
    final private ImageView tnt16;
    final private ImageView tnt17;
    final private ImageView tnt18;
    private ArrayList<ImageView> tnts_all;
    private Bounds boundschest;
    private boolean open;
    private Timeline tntt;
    private int tflag;
    public tnt(Scene scene,String group,String defaultc){
        tflag = 0;
        tnts_all = new ArrayList<>();
        tnt1 = new ImageView("TNT_explosion1.png");
        tnt2 = new ImageView("TNT_explosion2.png");
        tnt3 = new ImageView("TNT_explosion3.png");
        tnt4 = new ImageView("TNT_explosion4.png");
        tnt5 = new ImageView("TNT_explosion5.png");
        tnt6 = new ImageView("TNT_explosion6.png");
        tnt7 = new ImageView("TNT_explosion7.png");
        tnt8 = new ImageView("TNT_explosion8.png");
        tnt9 = new ImageView("TNT_explosion9.png");
        tnt10 = new ImageView("TNT_explosion10.png");
        tnt11 = new ImageView("TNT_explosion11.png");
        tnt12 = new ImageView("TNT_explosion12.png");
        tnt13 = new ImageView("TNT_explosion13.png");
        tnt14 = new ImageView("TNT_explosion14.png");
        tnt15 = new ImageView("TNT_explosion15.png");
        tnt16 = new ImageView("TNT_explosion16.png");
        tnt17 = new ImageView("TNT_explosion17.png");
        tnt18 = new ImageView("TNT_explosion18.png");

        chests = (Group) scene.lookup(group);
        open = false ;
        defaultchest = (ImageView) scene.lookup(defaultc);
        knifeicon = (ImageView) scene.lookup("#knifeicon");
        tnts_all.add(tnt1);
        tnts_all.add(tnt2);
        tnts_all.add(tnt3);
        tnts_all.add(tnt4);
        tnts_all.add(tnt5);
        tnts_all.add(tnt6);
        tnts_all.add(tnt7);
        tnts_all.add(tnt8);
        tnts_all.add(tnt9);
        tnts_all.add(tnt10);
        tnts_all.add(tnt11);
        tnts_all.add(tnt12);
        tnts_all.add(tnt13);
        tnts_all.add(tnt14);
        tnts_all.add(tnt15);
        tnts_all.add(tnt16);
        tnts_all.add(tnt17);
        tnts_all.add(tnt18);


        boundschest =defaultchest.localToScene(defaultchest.getBoundsInLocal());

//        chests.setTranslateX(903);
//        chests.setTranslateY(313);
        chests.setScaleX(0.35);
        chests.setScaleY(0.35);
//        chests.setVisible(false);
        tntt = new Timeline();
        tntt.setCycleCount(1);

        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(80),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt1);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(160),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt2);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(240),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt3);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(320),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt4);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(400),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt5);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(480),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt6);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(560),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt7);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(640),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt8);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(720),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt9);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(800),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt10);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(880),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt11);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(960),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt12);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(1040),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt13);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(1120),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt14);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(1200),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt15);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(1280),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt16);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(1360),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt17);
                    }
                }));
        tntt.getKeyFrames().add(new KeyFrame(Duration.millis(1440),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chests.getChildren().setAll(tnt18);
                    }
                }));

    }

    public int run(Hero hero){
        defaultchest.setVisible(false);
        tntt.play();

        tntt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(hero.getHero().getBoundsInParent().intersects(defaultchest.getBoundsInParent())){
                    System.out.println("int");
                    tflag = 1;
                }
                chests.setVisible(false);




            }
        });
        return tflag;

    }
    public ArrayList<ImageView> getChests_all(){
        return tnts_all;
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


}

