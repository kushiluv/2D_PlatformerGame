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

public class game implements Initializable {

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
//        final ImageView chest1 = new ImageView("wep_0006 #56893.png");
//        final ImageView chest2 = new ImageView("wep_0007 #37947.png");
//        final ImageView chest3 = new ImageView("wep_0008 #30876.png");
//        final ImageView chest4 = new ImageView("wep_0009 #57652.png");
//        chests = (Group) scene.lookup("#chests");
//
////        chests.setTranslateX(903);
////        chests.setTranslateY(313);
//        chests.setScaleX(0.35);
//        chests.setScaleY(0.35);
////        chests.setVisible(false);
//        Timeline chestt = new Timeline();
//        chestt.setCycleCount(1);
//
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(200),
//                        new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//                                chests.getChildren().setAll(chest1);
//                            }
//                        }));
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(600),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest2);
//                    }
//                }));
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest3);
//                    }
//                }));
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(1400),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest4);
//                    }
//                }));
////        defaultchest.setTranslateX(900);
////        defaultchest.setTranslateY(313);
//        defaultchest.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                defaultchest.setVisible(false);
//                chestt.play();
//                chestt.setOnFinished(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.setVisible(false);
//
//                    }
//                });
//            }
//        });


        islands cislands = new islands(scene);
        TranslateTransition orcjump = new TranslateTransition();
        grorc.set_orc_jump(orcjump);
        orcjump.play();

        TranslateTransition jump = new TranslateTransition();
        hero.set_hero_jump(jump);

        TranslateTransition fall= new TranslateTransition();
        hero.set_hero_fall(fall);
        fall.play();

        final int[] dashc = {0};

        final int[] flag1 = {0};
        weaponchest wchest = new weaponchest(scene);
        ArrayList<ImageView> gameelements = new ArrayList<>();
        gameelements.addAll(cislands.getIslands());
        gameelements.add(grorc.getHero());
        gameelements.add(defaultchest);
//        gameelements.add(chest1);
//        gameelements.add(chest2);
//        gameelements.add(chest3);
//        gameelements.add(chest4);



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

//                        System.out.println("hero max : "+boundshero.getMaxX());
//                        System.out.println("orcc min : "+boundsorc.getMinX());
                        if(boundsorc.getMinX()-125<boundshero.getMaxX()+100&&boundsorc.getMinX()-125>boundshero.getMaxX()){

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
                                                   TranslateTransition orcfall = new TranslateTransition();
                                                   orcfall.setDuration(Duration.seconds(1));
                                                   orcfall.setByY(800);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        final ImageView chest1 = new ImageView("wep_0006 #56893.png");
//        final ImageView chest2 = new ImageView("wep_0007 #37947.png");
//        final ImageView chest3 = new ImageView("wep_0008 #30876.png");
//        final ImageView chest4 = new ImageView("wep_0009 #57652.png");
//        chests.setTranslateX(200);
//        chests.setTranslateY(220);
//
//        Timeline chestt = new Timeline();
//        chestt.setCycleCount(Timeline.INDEFINITE);
//
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(200),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest1);
//                    }
//                }));
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(400),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest2);
//                    }
//                }));
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(600),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest3);
//                    }
//                }));
//        chestt.getKeyFrames().add(new KeyFrame(Duration.millis(800),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        chests.getChildren().setAll(chest4);
//                    }
//                }));
//        chestt.play();
    }
}