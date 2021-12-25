package com.example.helloapplication;


import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;

public class game  {

    @FXML
    private Label counter;
    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest;

    @FXML
    private ImageView island1;



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
        counter  = (Label) scene.lookup("#counter");
        defaultchest = (ImageView) scene.lookup("#defaultchest");
        island1 = (ImageView) scene.lookup("#island1");
        grorc.getHero().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TranslateTransition death = new TranslateTransition();
                grorc.death(death);
                death.play();
            }
        });

        islands cislands = new islands(scene);
        TranslateTransition orcjump = new TranslateTransition();
        grorc.set_orc_jump(orcjump);
        orcjump.play();

        TranslateTransition jump = new TranslateTransition();
        hero.set_hero_jump(jump);

        TranslateTransition fall= new TranslateTransition();
        hero.set_hero_fall(fall);
        fall.play();

        TranslateTransition death = new TranslateTransition();

        final int[] dashc = {0};

        final int[] flag1 = {0};
        weaponchest wchest = new weaponchest(scene);
        ArrayList<ImageView> gameelements = new ArrayList<>();
        gameelements.addAll(cislands.getIslands());
        gameelements.add(grorc.getHero());
        gameelements.add(defaultchest);
        gameelements.addAll(wchest.getChests_all());



//
//
        fall.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int flag = 0;


                ArrayList<Bounds> islands = new ArrayList<>();
                islands.addAll(cislands.getALLislands());
                if(hero.getLocation().getMinY()>=600&& flag1[0] ==0){
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
                    if (Math.abs(Math.floor(hero.getLocation().getMaxY()) - Math.floor(islands.get(i).getMinY()))<=4 && islands.get(i).getMaxX() >= hero.getLocation().getMinX() && islands.get(i).getMinX() <= hero.getLocation().getMaxX()) {
//                        System.out.println("island: "+islands.get(0).getMaxX()+" "+islands.get(0).getMinX()+" "+islands.get(0).getMinY());
//                        System.out.println("hero min x"+hero.getLocation().getMaxX());
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
        TranslateTransition knifeanimation = new TranslateTransition();
        TranslateTransition knifeanimation1 = new TranslateTransition();
        gameelements.add(hero.getHero());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case SPACE:

                        fall.pause();

                        TranslateTransition orcfall = new TranslateTransition();
                        TranslateTransition pt = new TranslateTransition();
                        Bounds boundshero = hero.getHero().localToScene(hero.getHero().getBoundsInLocal());
                        Bounds boundsorc = grorc.getHero().localToScene(grorc.getHero().getBoundsInLocal());

                        if(knifeanimation.getStatus()==Animation.Status.STOPPED) {
                            throwingknives knife = new throwingknives(scene, "#knife");
                            knife.run(knifeanimation, boundshero);
                            knifeanimation.play();
                        }
                        else{
                            throwingknives knife1 = new throwingknives(scene,"#knife1");
                            knife1.run(knifeanimation1,boundshero);
                            knifeanimation1.play();

                        }
                        TranslateTransition translate = new TranslateTransition();

//                        System.out.println("hero max : "+boundshero.getMaxX());
//                        System.out.println("orcc min : "+boundsorc.getMinX());
                        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(250)),death);
                        if(boundsorc.getMinX()-125<boundshero.getMaxX()+350&&boundsorc.getMinX()-125>boundshero.getMaxX())
                        {
                            System.out.println("weapon hits");
                            grorc.death(death);
                            orcjump.pause();

                            seqTransition.play();
                        }
                        if(boundsorc.getMinX()-125<boundshero.getMaxX()+100&&boundsorc.getMinX()-125>boundshero.getMaxX()&&seqTransition.getStatus()== Animation.Status.STOPPED){

                            System.out.println("colliding");
                            translate.setDuration(Duration.seconds(0.07));
                            translate.setByX(boundsorc.getMinX()-boundshero.getMaxX());
                            translate.setNode(hero.getHero());
                            translate.play();
//                            Polyline path = new Polyline();
//                            path.getPoints().addAll(new Double[]{-200.0,0.0,-100.0,0.0,100.0,500.0});
                            ArrayList<Bounds> islands = new ArrayList<>();
                            islands.addAll(cislands.getALLislands());
                            pt.setNode(grorc.getHero());
                            pt.setByX(150);
                            pt.setDuration(Duration.seconds(0.07));

                            System.out.println("island1 "+islands.get(1).getMaxX()+" "+islands.get(1).getMinX()+" "+islands.get(1).getMinY());
                            System.out.println("island2: "+islands.get(2).getMaxX()+" "+islands.get(2).getMinX()+" "+islands.get(2).getMinY());
                            System.out.println("orc y : "+boundsorc.getMaxY()+"\n orc x : "+boundsorc.getMaxX());
                            System.out.println("hero min x"+hero.getLocation().getMaxX());



                        }

                        else {
                            translate.setDuration(Duration.seconds(0.07));
                            translate.setByX(100);
                            dashc[0]++;
                            counter.setText(Integer.toString(dashc[0]));

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
                                                for(int i =0;i<islands.size();i++) {
                                                    if (islands.get(i).getMaxX() -161 >= boundsorc.getMinX() && islands.get(i).getMinX()-161 <= boundsorc.getMaxX()) {
                                                        tflag = 1;
                                                        orcjump.play();
                                                        break;



                                                    }
                                                }

                                                if(cislands.getALLislands().get(1).intersects(grorc.getHero().getBoundsInParent())){
                                                    System.out.println("bitch on the island");
                                                }

                                               if(tflag==0){

                                                   orcfall.setDuration(Duration.seconds(0.5));
                                                   orcfall.setByY(400);
                                                   orcfall.setCycleCount(1);
                                                   orcfall.setAutoReverse(false);
                                                   orcfall.setNode(grorc.getHero());

                                                   orcfall.play();
                                                   orcfall.setOnFinished(new EventHandler<ActionEvent>() {
                                                       @Override
                                                       public void handle(ActionEvent event) {
                                                           grorc.getHero().setVisible(false);
                                                       }
                                                   });

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