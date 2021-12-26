package com.example.helloapplication;


import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class game  {

    @FXML
    private Label counter;
    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest;

    @FXML
    private ImageView island1;





    void cleanup() {
        // stop animations reset model ect.
    }

    void startGame(Stage gamestage) throws IOException {
        // initialisation from start method goes here
        Parent root = FXMLLoader.load(getClass().getResource("gamescene.fxml"));

        Scene scene = new Scene(root, 1200, 600);
        Hero hero = new Hero(scene);

        Green_Orc grorc = new Green_Orc(scene,"#orc");
        Green_Orc grorc1 = new Green_Orc(scene,"#orc11");
        Green_Orc grorc2 = new Green_Orc(scene,"#orc1");

        ArrayList<Green_Orc> green_orcs = new ArrayList<>();
        green_orcs.add(grorc);
        green_orcs.add(grorc1);
        green_orcs.add(grorc2);
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
        ArrayList<TranslateTransition> orcjumps = new ArrayList<>();
        for(int i =0; i<green_orcs.size();i++) {
            TranslateTransition orcjump = new TranslateTransition();
            orcjumps.add(orcjump);
            green_orcs.get(i).set_orc_jump(orcjumps.get(i));
            orcjumps.get(i).play();
        }

        TranslateTransition jump = new TranslateTransition();
        hero.set_hero_jump(jump);
        TranslateTransition fall = new TranslateTransition();
        hero.set_hero_fall(fall);
        fall.play();

        TranslateTransition knifeanimation = new TranslateTransition();
        TranslateTransition knifeanimation1 = new TranslateTransition();

        TranslateTransition death = new TranslateTransition();

        final int[] dashc = {0};

        final int[] flag1 = {0};
        weaponchest wchest = new weaponchest(scene);
        ArrayList<ImageView> gameelements = new ArrayList<>();
        gameelements.addAll(cislands.getIslands());
        gameelements.add(grorc.getHero());
        gameelements.add(grorc1.getHero());
        gameelements.add(grorc2.getHero());
        gameelements.add(defaultchest);
        gameelements.addAll(wchest.getChests_all());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {


                for(int i =0; i<cislands.getALLislands().size();i++) {
                    if (cislands.getIslands().get(i).getBoundsInParent().intersects(hero.getHero().getBoundsInParent())) {
                        fall.stop();
                        jump.play();
                        jump.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                fall.play();
                            }
                        });

                    }
                }


                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case SPACE:
                                for (ImageView i : gameelements) {
                                    TranslateTransition backshift = new TranslateTransition();
                                    backshift.setDuration(Duration.seconds(0.1));
                                    backshift.setByX(-100);
                                    dashc[0]++;
                                    counter.setText(Integer.toString(dashc[0]/14));
                                    backshift.setNode(i);
                                    backshift.play();
                                }
                                throwingknives knife = new throwingknives(scene, "#knife");
                                throwingknives knife1 = new throwingknives(scene,"#knife1");

                                if(knifeanimation.getStatus()==Animation.Status.STOPPED) {

                                    knife.run(knifeanimation, hero.getHero().getBoundsInParent());
                                    knifeanimation.play();
                                }
                                else{

                                    knife1.run(knifeanimation1,hero.getHero().getBoundsInParent());
                                    knifeanimation1.play();

                                }
                                AnimationTimer temp = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        for (int i = 0; i < green_orcs.size(); i++) {
                                            if (knife.getKnife().getBoundsInParent().intersects(green_orcs.get(i).getHero().getBoundsInParent()) || knife1.getKnife().getBoundsInParent().intersects(green_orcs.get(i).getHero().getBoundsInParent())) {
                                                green_orcs.get(i).death(death);
                                                death.play();
                                            }
                                        }
                                    }
                                };
//                                temp.start();


                        }
                    }
                });


                for(int i = 0;i<green_orcs.size();i++) {
                    if (green_orcs.get(i).getHero().getBoundsInParent().intersects(hero.getHero().getBoundsInParent())) {
                        System.out.println("boom");
                        TranslateTransition orcsidecollision = new TranslateTransition();
                        orcsidecollision.setNode(green_orcs.get(i).getHero());
                        orcsidecollision.setByX(50);
                        orcsidecollision.setDuration(Duration.seconds(0.07));
                        orcjumps.get(i).pause();
                        orcsidecollision.play();
                        int finalI = i;
                        orcsidecollision.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                AnimationTimer nestedtimer = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        TranslateTransition orcfall = new TranslateTransition();
                                        orcfall.setDuration(Duration.seconds(0.5));
                                        orcfall.setByY(400);
                                        orcfall.setCycleCount(1);
                                        orcfall.setAutoReverse(false);
                                        orcfall.setNode(green_orcs.get(finalI).getHero());
                                        orcfall.play();

                                        int orcislandflag=0;
                                        for(int j = 0; j<cislands.getALLislands().size();j++) {
                                            if (cislands.getIslands().get(j).getBoundsInParent().intersects(green_orcs.get(finalI).getHero().getBoundsInParent())) {
                                                orcfall.pause();
                                                orcjumps.get(finalI).play();
                                                System.out.println("orc intersects vo");
                                                orcislandflag = 1;
                                                break;
                                            }
                                        }
//                                        if(orcislandflag==0){
//
//                                        orcfall.play();
//                                        orcfall.setOnFinished(new EventHandler<ActionEvent>() {
//                                            @Override
//                                            public void handle(ActionEvent event) {
//                                                System.out.println("in here");
//                                                green_orcs.get(finalI).getHero().setVisible(false);
//                                            }
//                                        });
//
//                                    }
                                    }
                                };
                                nestedtimer.start();

//
                                }

//                                orcjumps.get(finalI).play();


                        });

                    }


                }

            }
        };
        timer.start();



//
////
////
//        fall.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                int flag = 0;
//
//
//                ArrayList<Bounds> islands = new ArrayList<>();
//                islands.addAll(cislands.getALLislands());
////                if(hero.getLocation().getMinY()>=600&& flag1[0] ==0){
////                    flag1[0] =1;
////                    gameoverwindow.gameoverpanevisible();
////
//////                    orcjump.pause();
////                    gameoverwindow.getQuitbutton().setOnMouseClicked(new EventHandler<MouseEvent>() {
////                        @Override
////                        public void handle(MouseEvent mouseEvent) {
////                            gameoverwindow.gameoverpaneinvisible();
////                            gameoverwindow.quitpanevisible();
////                            gameoverwindow.getFinalexit().setOnMouseClicked(new EventHandler<MouseEvent>() {
////                                @Override
////                                public void handle(MouseEvent mouseEvent) {
////                                    Platform.exit();
////                                }
////                            });
////
//////                            gameoverwindow.getQuitno().setOnAction(e -> {     //NO button kaam nhi kar rha. some null pointer exception
//////                               gameoverwindow.quitpaneinvisible();
//////                               gameoverwindow.gameoverpanevisible();
//////                            });
////
////
////                        }
////                    });
////                    gameoverwindow.getRestart1().setOnAction(e -> {
////                        try {
////                            restart(gamestage);
////                        } catch (IOException ex) {
////                            ex.printStackTrace();
////                        }
////                    });
////
////                }
//                for(int i =0;i<islands.size();i++) {
//                    System.out.println("island: "+islands.get(0).getMinX()+" "+islands.get(0).getMaxX()+" "+islands.get(0).getMaxY());
//                    System.out.println("hero min x"+hero.getLocation().getMinX()+"hero max x"+hero.getLocation().getMaxX()+"hero min y : "+hero.getLocation().getMaxY());
//
//                    if(hero.getLocation().getMaxX()>islands.get(i).getMinX()&&hero.getLocation().getMinX()<islands.get(i).getMaxX()&&Math.abs((hero.getLocation().getMaxY()) - (islands.get(i).getMinY()))<=52&&Math.abs((hero.getLocation().getMaxY()) - (islands.get(i).getMinY()))>=49){
//                        System.out.println("on island mah boi");
//                        fall.pause();
//                        jump.play();
//                        flag = 1;
//                        break;
//
//
//
//
//                    }
//                }
//
//                if(flag==0){
//                    fall.play();
//                }
////                if(hero.intersects(island.getBoundsInLocal())){
////                    System.out.println("jump karle bc");
////                    jump.play();
////                }
//
////                System.out.println(Math.floor(boundshero.getMaxY()));
////                System.out.println("island "+Math.floor(boundsisland.getMinY()));
//
//
//
//
//            }
//        });
//        jump.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                fall.play();
//            }
//        });

//        gameelements.add(hero.getHero());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case SPACE:



                        TranslateTransition orcfall = new TranslateTransition();
                        TranslateTransition pt = new TranslateTransition();
                        Bounds boundshero = hero.getHero().localToScene(hero.getHero().getBoundsInLocal());
                        Bounds boundsorc = grorc.getHero().localToScene(grorc.getHero().getBoundsInLocal());
                        Bounds boundsorc1 = grorc1.getHero().localToScene(grorc1.getHero().getBoundsInLocal());
                        Bounds boundsorc2 = grorc2.getHero().localToScene(grorc2.getHero().getBoundsInLocal());
                        ArrayList<Bounds> boundsorcs = new ArrayList<>();
                        boundsorcs.add(boundsorc);
                        boundsorcs.add(boundsorc1);
                        boundsorcs.add(boundsorc2);
//                        if(knifeanimation.getStatus()==Animation.Status.STOPPED) {
//
//                            knife.run(knifeanimation, boundshero);
//                            knifeanimation.play();
//                        }
//                        else{
//
//                            knife1.run(knifeanimation1,boundshero);
//                            knifeanimation1.play();
//
//                        }
                        TranslateTransition translate = new TranslateTransition();

//                        System.out.println("hero max : "+boundshero.getMaxX());
//                        System.out.println("orcc min : "+boundsorc.getMinX());
                        SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(250)),death);
                        int weaponint = 0;
                        int orcloopflag=0;
                        int flaggedorc=-1;

//                        for(int i =0; i<boundsorcs.size();i++) {
//                            if (boundsorcs.get(i).getMinX() - 125 < boundshero.getMaxX() + 350 && boundsorcs.get(i).getMinX() - 125 > boundshero.getMaxX() && weaponint == 1) {
//                                System.out.println("weapon hits");
//                                green_orcs.get(i).death(death);
//                                orcjumps.get(i).pause();
//
//                                seqTransition.play();
//                                break;
//                            }
//
//
//
////                            if (boundsorcs.get(i).getMinX() - 125 < boundshero.getMaxX() + 100 && boundsorcs.get(i).getMinX() - 125 > boundshero.getMaxX() && seqTransition.getStatus() == Animation.Status.STOPPED) {
////                                orcloopflag=1;
////                                flaggedorc=i;
////                                System.out.println("colliding");
////                                translate.setDuration(Duration.seconds(0.07));
////                                translate.setByX(boundsorcs.get(i).getMinX() - boundshero.getMaxX());
////                                translate.setNode(hero.getHero());
////                                translate.play();
//////                            Polyline path = new Polyline();
//////                            path.getPoints().addAll(new Double[]{-200.0,0.0,-100.0,0.0,100.0,500.0});
////                                ArrayList<Bounds> islands = new ArrayList<>();
////                                islands.addAll(cislands.getALLislands());
////                                pt.setNode(green_orcs.get(i).getHero());
////                                pt.setByX(150);
////                                pt.setDuration(Duration.seconds(0.07));
////
////                                System.out.println("island1 " + islands.get(1).getMaxX() + " " + islands.get(1).getMinX() + " " + islands.get(1).getMinY());
////                                System.out.println("island2: " + islands.get(2).getMaxX() + " " + islands.get(2).getMinX() + " " + islands.get(2).getMinY());
////                                System.out.println("orc y : " + boundsorc.getMaxY() + "\n orc x : " + boundsorc.getMaxX());
////                                System.out.println("hero min x" + hero.getLocation().getMaxX());
////                                break;
////                            }
//
//
//                        }
                        if(orcloopflag==0) {
                        translate.setDuration(Duration.seconds(0.07));
                        translate.setByX(0);


                        translate.setNode(hero.getHero());
                        translate.play();

                    }
//
//                        fall.play();
//                        int finalFlaggedorc = flaggedorc;
//                        translate.setOnFinished(new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//
//                                for (ImageView i : gameelements) {
//                                    TranslateTransition backshift = new TranslateTransition();
//                                    backshift.setDuration(Duration.seconds(0.1));
//                                    backshift.setByX(-100);
//                                    dashc[0]++;
//                                    counter.setText(Integer.toString(dashc[0]/14));
//                                    backshift.setNode(i);
//                                    backshift.play();
//                                    backshift.setOnFinished(new EventHandler<ActionEvent>() {
//                                        @Override
//                                        public void handle(ActionEvent event) {
////                                            if (pt.getNode()!=null) {
////                                                int tflag = 0;
////                                                orcjumps.get(finalFlaggedorc).pause();
////                                                pt.play();
////                                                Bounds boundsorc = green_orcs.get(finalFlaggedorc).getHero().localToScene(green_orcs.get(finalFlaggedorc).getHero().getBoundsInLocal());
////
////                                                ArrayList<Bounds> islands = new ArrayList<>();
////                                                islands.addAll(cislands.getALLislands());
////                                                //System.out.println("orc y : "+boundsorc.getMaxY()+"\n orc x : "+boundshero.getCenterX());
////                                                //System.out.println("island y min : "+boundsisland1.getMinY()+"x : "+boundsisland1.getMinX()+" max: "+boundsisland1.getMaxX());
////                                                for(int i =0;i<islands.size();i++) {
////                                                    if (islands.get(i).getMaxX() -161 >= boundsorc.getMinX() && islands.get(i).getMinX()-161 <= boundsorc.getMaxX()) {
////                                                        tflag = 1;
////                                                        orcjumps.get(finalFlaggedorc).play();
////                                                        break;
////
////
////
////                                                    }
////                                                }
////
////                                                if(cislands.getALLislands().get(1).intersects(green_orcs.get(finalFlaggedorc).getHero().getBoundsInParent())){
////                                                    System.out.println("bitch on the island");
////                                                }
////
////                                                if(tflag==0){
////
////                                                    orcfall.setDuration(Duration.seconds(0.5));
////                                                    orcfall.setByY(400);
////                                                    orcfall.setCycleCount(1);
////                                                    orcfall.setAutoReverse(false);
////                                                    orcfall.setNode(green_orcs.get(finalFlaggedorc).getHero());
////
////                                                    orcfall.play();
////                                                    orcfall.setOnFinished(new EventHandler<ActionEvent>() {
////                                                        @Override
////                                                        public void handle(ActionEvent event) {
////                                                            green_orcs.get(finalFlaggedorc).getHero().setVisible(false);
////                                                        }
////                                                    });
////
////                                                }
////                                            }
//                                        }
//                                    });
//
//                                }
//                            }
                        };
//                            Bounds boundsInScene = hero.localToScene(hero.getBoundsInLocal());
//                            Bounds boundsI = island.localToScene(island.getBoundsInLocal());
//                            System.out.println(boundsInScene.getMaxY()+" "+ boundsI.getMinY());
//                            System.out.println(hero.getX()+" "+hero.getLayoutX());
                }

        });

//        menu.getPause().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                menu.panevisible();
//                jump.pause();
//                fall.pause();
////                orcjump.pause();
//            }
//        });
//        menu.getResume().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                menu.paneinvisible();
//                fall.play();
////                orcjump.play();
//            }
//        });

        gamestage.setScene(scene);
        gamestage.show();


    }

    void restart(Stage gamestage) throws IOException {
        cleanup();
        startGame(gamestage);
    }


    public void start(Stage primaryStage) throws IOException {
        startGame(primaryStage);
    }



}