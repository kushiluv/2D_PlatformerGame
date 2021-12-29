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
import javafx.scene.input.KeyCode;
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

        Scene scene = new Scene(root, 1200, 650);
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
        throwingknives knife = new throwingknives(scene, "#knife");
        throwingknives knife1 = new throwingknives(scene, "#knife1");
        gameelements.addAll(wchest.getChests_all());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                int knives = 0;

                for (int i = 0; i < cislands.getALLislands().size(); i++) {
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
                if(hero.getHero().getBoundsInParent().intersects(wchest.chestimg().getBoundsInParent())){
                    wchest.setOpen();
                    wchest.run();

                }




                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if(keyEvent.getCode()== KeyCode.SPACE) {

                                for (ImageView i : gameelements) {
                                    TranslateTransition backshift = new TranslateTransition();
                                    backshift.setDuration(Duration.seconds(0.1));
                                    backshift.setByX(-100);
                                    dashc[0]++;
                                    counter.setText(Integer.toString(dashc[0] / 14));
                                    backshift.setNode(i);
                                    backshift.play();
                                }


                                if (knifeanimation.getStatus() == Animation.Status.STOPPED&& wchest.getopen()&&knife.isEquipped()) {

                                    knife.run(knifeanimation, hero.getHero().getBoundsInParent());
                                    knifeanimation.play();
                                } else {
                                    if(wchest.getopen()&&knife.isEquipped()){
                                        knife1.run(knifeanimation1, hero.getHero().getBoundsInParent());
                                        knifeanimation1.play();}

                                }
                                AnimationTimer temp = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        for (int i = 0; i < green_orcs.size(); i++) {
                                            if ((knife.getKnife().getBoundsInParent().intersects(green_orcs.get(i).getHero().getBoundsInParent()) || knife1.getKnife().getBoundsInParent().intersects(green_orcs.get(i).getHero().getBoundsInParent()))&&((knife1.getKnife().getBoundsInParent().getCenterY()<=green_orcs.get(i).getHero().getBoundsInParent().getMaxY()&&knife1.getKnife().getBoundsInParent().getCenterY()>=green_orcs.get(i).getHero().getBoundsInParent().getMinY())||(knife.getKnife().getBoundsInParent().getCenterY()<=green_orcs.get(i).getHero().getBoundsInParent().getMaxY()&&knife.getKnife().getBoundsInParent().getCenterY()>=green_orcs.get(i).getHero().getBoundsInParent().getMinY()))) {
                                                if(wchest.getopen()&&knife.isEquipped()) {

                                                    green_orcs.get(i).death(death);
                                                    death.play();
                                                }
                                            }
                                        }
                                    }
                                };
                                temp.start();



                        }
                        if(keyEvent.getCode()==KeyCode.DIGIT1){
                            knife.setEquippedTrue();
                            knife1.setEquippedTrue();
                        }

                    }
                });



                for (int i = 0; i < green_orcs.size(); i++) {
                    if (green_orcs.get(i).getHero().getBoundsInParent().intersects(hero.getHero().getBoundsInParent())) {

                        TranslateTransition orcsidecollision = new TranslateTransition();
                        orcsidecollision.setNode(green_orcs.get(i).getHero());
                        orcsidecollision.setByX(50);
                        orcsidecollision.setDuration(Duration.seconds(0.07));
                        orcjumps.get(i).pause();
                        orcsidecollision.play();
                        int finalI = i;
                        TranslateTransition orcfall = new TranslateTransition();

                        orcsidecollision.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                orcfall.setDuration(Duration.seconds(0.5));
                                orcfall.setByY(400);
                                orcfall.setCycleCount(1);
                                orcfall.setAutoReverse(false);
                                orcfall.setNode(green_orcs.get(finalI).getHero());
                                orcfall.play();
                                orcfall.setOnFinished(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        green_orcs.get(finalI).getHero().setVisible(false);
                                    }
                                });
                                AnimationTimer orc = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        for (int j = 0; j < cislands.getALLislands().size(); j++) {
                                            if (cislands.getIslands().get(j).getBoundsInParent().intersects(green_orcs.get(finalI).getHero().getBoundsInParent())) {
                                                orcfall.pause();
                                                TranslateTransition orcjum = new TranslateTransition();
                                                green_orcs.get(finalI).set_orc_jump(orcjum);
                                                orcjum.play();
                                            }
                                        }
                                    }
                                };
                                orc.start();
                            }




                        });


                    }

                }
            }
        };
        timer.start();
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