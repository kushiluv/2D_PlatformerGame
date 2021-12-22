package com.example.helloapplication;


import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class game {



    @FXML
    private ImageView chest1;


    @FXML
    private ImageView orc;
    public void start(Stage gamestage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gamescene.fxml"));
        Scene scene = new Scene(root, 1200, 600);
        Hero hero = new Hero(scene);
        Green_Orc grorc = new Green_Orc(scene);
        menu menu = new menu(scene);
        gameoverwindow gameoverwindow = new gameoverwindow(scene);

        menu.paneinvisible();
        gameoverwindow.gameoverpaneinvisible();
        gameoverwindow.quitpaneinvisible();

        chest1 = (ImageView) scene.lookup("#chest1");

        orc = (ImageView) scene.lookup("#orc");

        islands cislands = new islands(scene);
        TranslateTransition orcjump = new TranslateTransition();
        grorc.set_orc_jump(orcjump);
        orcjump.play();

        TranslateTransition jump = new TranslateTransition();
        hero.set_hero_jump(jump);

        TranslateTransition fall= new TranslateTransition();
        hero.set_hero_fall(fall);
        fall.play();

        final int[] flag1 = {0};
        ArrayList<ImageView> gameelements = new ArrayList<>();
        gameelements.addAll(cislands.getIslands());
        gameelements.add(grorc.getHero());
        gameelements.add(chest1);


//
//
        fall.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int flag = 0;
                Bounds boundshero = hero.getHero().localToScene(hero.getHero().getBoundsInLocal());

                ArrayList<Bounds> islands = new ArrayList<>();
                islands.addAll(cislands.getALLislands());
                if(boundshero.getMinY()>=600&& flag1[0] ==0){
                    flag1[0] =1;
                    gameoverwindow.gameoverpanevisible();

//                    orcjump.pause();
                    gameoverwindow.getQuitbutton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            gameoverwindow.gameoverpaneinvisible();
                            gameoverwindow.quitpanevisible();
                            gameoverwindow.getFinalexit().setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    Platform.exit();
                                }
                            });



                        }
                    });

                }
                for(int i =0;i<islands.size();i++) {
                    if (Math.abs(Math.floor(boundshero.getMaxY()) - Math.floor(islands.get(i).getMinY()))<=4 && islands.get(i).getMaxX() >= boundshero.getMinX() && islands.get(i).getMinX() <= boundshero.getMaxX()) {

                        fall.pause();
                        jump.play();
                        flag = 1;
                        break;



                    }
                }

                if(flag==0){
                    fall.play();
                }
//                if(hero.intersects(island.getBoundsInLocal())){
//                    System.out.println("jump karle bc");
//                    jump.play();
//                }

//                System.out.println(Math.floor(boundshero.getMaxY()));
//                System.out.println("island "+Math.floor(boundsisland.getMinY()));




            }
        });
        jump.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fall.play();
            }
        });

        gameelements.add(hero.getHero());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case SPACE:

                        fall.pause();
                        TranslateTransition pt = new TranslateTransition();
                        Bounds boundshero = hero.getHero().localToScene(hero.getHero().getBoundsInLocal());
                        Bounds boundsorc = grorc.getHero().localToScene(grorc.getHero().getBoundsInLocal());
                        TranslateTransition translate = new TranslateTransition();
                        System.out.println("hero max : "+boundshero.getMaxX());
                        System.out.println("orcc min : "+boundsorc.getMinX());
                        if(boundsorc.getMinX()-125<boundshero.getMaxX()+100&&boundsorc.getMinX()-125>boundshero.getMaxX()){

                            System.out.println("colliding");
                            translate.setDuration(Duration.seconds(0.07));
                            translate.setByX(boundsorc.getMinX()-boundshero.getMaxX());
                            translate.setNode(hero.getHero());
                            translate.play();
//                            Polyline path = new Polyline();
//                            path.getPoints().addAll(new Double[]{-200.0,0.0,-100.0,0.0,100.0,500.0});

                            pt.setNode(grorc.getHero());
                            pt.setByX(100);
                            pt.setDuration(Duration.seconds(0.07));


                        }
                        else {
                            translate.setDuration(Duration.seconds(0.07));
                            translate.setByX(100);
                            translate.setNode(hero.getHero());
                            translate.play();
                        }
                        fall.play();
                        translate.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                for (ImageView i : gameelements) {
                                    TranslateTransition backshift = new TranslateTransition();
                                    backshift.setDuration(Duration.seconds(0.25));
                                    backshift.setByX(-100);
                                    backshift.setNode(i);
                                    backshift.play();
                                    backshift.setOnFinished(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            if (pt.getNode()!=null) {
                                                int tflag = 0;
                                                orcjump.pause();
                                                pt.play();
                                                Bounds boundsorc = grorc.getHero().localToScene(grorc.getHero().getBoundsInLocal());

                                                ArrayList<Bounds> islands = new ArrayList<>();
                                                islands.addAll(cislands.getALLislands());
                                                //System.out.println("orc y : "+boundsorc.getMaxY()+"\n orc x : "+boundshero.getCenterX());
                                                //System.out.println("island y min : "+boundsisland1.getMinY()+"x : "+boundsisland1.getMinX()+" max: "+boundsisland1.getMaxX());
                                                //for (int i = 0; i < islands.size(); i++) {
////                                                    if (!(Math.abs(Math.floor(boundsorc.getMaxY()) - Math.floor(islands.get(i).getMinY())) <= 4 && islands.get(i).getMaxX() >= boundsorc.getMinX() && islands.get(i).getMinX() <= boundsorc.getMaxX())) {
////                                                        TranslateTransition translat = new TranslateTransition();
////                                                        translat.setDuration(Duration.seconds(2));
////                                                        translat.setByY(600);
////                                                        translat.setNode(grorc.getHero());
////                                                        translat.play();
////                                                        tflag = 1;
////
////                                                    }
//                                                }
                                                if(cislands.getALLislands().get(1).intersects(grorc.getHero().getBoundsInParent())){
                                                    System.out.println("bitch on the island");
                                                }

                                               if(tflag==0){
                                                   orcjump.play();

                                               }
                                            }
                                        }
                                    });

                                }
                            }
                        });
//                            Bounds boundsInScene = hero.localToScene(hero.getBoundsInLocal());
//                            Bounds boundsI = island.localToScene(island.getBoundsInLocal());
//                            System.out.println(boundsInScene.getMaxY()+" "+ boundsI.getMinY());
//                            System.out.println(hero.getX()+" "+hero.getLayoutX());
                }
            }
        });

        menu.getPause().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menu.panevisible();
                jump.pause();
                fall.pause();
                orcjump.pause();
            }
        });
        menu.getResume().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menu.paneinvisible();
                fall.play();
                orcjump.play();
            }
        });

        gamestage.setScene(scene);
        gamestage.show();

    }


}