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
import java.util.Random;

public class game  {

    @FXML
    private Label counter;

    @FXML
    private Label coinscounter;
    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest;



    @FXML
    private ImageView island1;

    private gameoverwindow gameoverwindow;
    void cleanup() {
        // stop animations reset model ect.
    }



    void startGame(Stage gamestage) throws IOException {
        // initialisation from start method goes here
        Parent root = FXMLLoader.load(getClass().getResource("gamescene.fxml"));

        Scene scene = new Scene(root, 1200, 650);
        Hero hero = new Hero(scene);

        Orcs grorc = new Green_Orc(scene,"#orc");
        Orcs grorc1 = new Green_Orc(scene,"#orc11");
        Orcs grorc2 = new Green_Orc(scene,"#orc3");
        Orcs rorc1 = new Red_Orc(scene,"#rorc1");
        Orcs rorc2 = new Red_Orc(scene,"#rorc2");
        ArrayList<Orcs> orcs = new ArrayList<>();
        orcs.add(grorc);
        orcs.add(grorc1);
        orcs.add(grorc2);
        orcs.add(rorc1);
        orcs.add(rorc2);
        menu menu = new menu(scene);
        gameoverwindow = new gameoverwindow(scene);


        menu.paneinvisible();
        gameoverwindow.gameoverpaneinvisible();
        gameoverwindow.quitpaneinvisible();
        counter  = (Label) scene.lookup("#counter");
        coinscounter  = (Label) scene.lookup("#coinscounter");
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
        for(int i =0; i<orcs.size();i++) {
            TranslateTransition orcjump = new TranslateTransition();
            orcjumps.add(orcjump);
            orcs.get(i).set_orc_jump(orcjumps.get(i));
            orcjumps.get(i).play();
        }

        TranslateTransition jump = new TranslateTransition();
        hero.set_hero_jump(jump);
        TranslateTransition fall = new TranslateTransition();
        hero.set_hero_fall(fall);
        fall.play();

        TranslateTransition knifeanimation = new TranslateTransition();
        TranslateTransition knifeanimation1 = new TranslateTransition();
        TranslateTransition axe_forward = new TranslateTransition();
        TranslateTransition axe_back = new TranslateTransition();
        RotateTransition rotate = new RotateTransition();
        ParallelTransition axe_for = new ParallelTransition(rotate,axe_forward);
        ParallelTransition axe_bac = new ParallelTransition(rotate,axe_back);
        SequentialTransition axeanimation = new SequentialTransition(axe_for,new PauseTransition(Duration.seconds(0.15)),axe_bac);

        TranslateTransition death = new TranslateTransition();

        final int[] dashc = {0};
        final int[] coinsc = {0};
        final int[] flag1 = {0};
        chests wchest = new weaponchest(scene,"#chests","#defaultchest");
        chests cchest = new coinchest(scene,"#coinchests","#defaultcoinchest");
        chests cchest1 = new coinchest(scene,"#coinchests1","#defaultcoinchest1");
        chests wchest1 = new weaponchest(scene,"#chestss1","#defaultchest1");
        chests wchest2 = new weaponchest(scene,"#chestss2","#defaultchest2");
        chests wchest3 = new weaponchest(scene,"#chestss3","#defaultchest3");

        ArrayList<chests> chests = new ArrayList<>();
        chests.add(wchest);
        chests.add(cchest);
        chests.add(cchest1);
        chests.add(wchest1);
        chests.add(wchest2);
        chests.add(wchest3);
        tnt wtnt = new tnt(scene,"#tnts","#defaulttnt");
        ArrayList<ImageView> gameelements = new ArrayList<>();
        gameelements.addAll(cislands.getIslands());
        gameelements.add(grorc.getHero());
        gameelements.add(grorc1.getHero());
        gameelements.add(grorc2.getHero());
        gameelements.add(rorc2.getHero());
        gameelements.add(rorc1.getHero());
        gameelements.add(wchest.chestimg());
        gameelements.add(cchest1.chestimg());
        gameelements.add(wtnt.chestimg());
        gameelements.add(wchest2.chestimg());
        gameelements.add(wchest3.chestimg());
        gameelements.add(cchest.chestimg());
        gameelements.add(wchest1.chestimg());
        throwingknives knife = new throwingknives(scene, "#knife","#knifeupgrade1");
        throwingknives knife1 = new throwingknives(scene, "#knife1","#knifeupgrade");
        throwingAxe axe = new throwingAxe(scene,"#axe");
        gameelements.addAll(wchest.getChests_all());
        gameelements.addAll(wtnt.getChests_all());
        gameelements.addAll(cchest.getChests_all());
        gameelements.addAll(cchest1.getChests_all());
        gameelements.addAll(wchest1.getChests_all());
        gameelements.addAll(wchest2.getChests_all());
        gameelements.addAll(wchest3.getChests_all());
        final int[] coinss = {0};
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                int knives = 0;
                for (int i = 0; i < orcs.size(); i++) {
                    if (orcs.get(i).isDead() == 1&&!orcs.get(i).isDea()) {

                        coinss[0] = coinss[0] + 10;
                        coinscounter.setText(Integer.toString(coinss[0]));
                        orcs.get(i).setDead(2);
                        orcs.get(i).setDea(true);
                        break;

                    }

                }

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

                                if(hero.getHero().getBoundsInParent().getMinY()>=600&& flag1[0] ==0){
                                   flag1[0] =1;
                                    gameover();




                }
                for(int i =0; i<chests.size();i++) {
                    if (hero.getHero().getBoundsInParent().intersects(chests.get(i).chestimg().getBoundsInParent())) {
                        System.out.println("chesting");
                        if(chests.get(i).getClass()==wchest.getClass()&&!chests.get(i).getopen()){
                            Random weapo = new Random();
                            int weapon = weapo.nextInt(2);
                            if(weapon==0){
                                knife.setUpgrade_level(knife.getUpgrade_level()+1);
                                knife1.setUpgrade_level(knife1.getUpgrade_level()+1);
                                if(knife.getUpgrade_level()==2){
                                    knife.upgrade();
                                    knife1.upgrade();

                                }
                                else if(knife.getUpgrade_level()==1){
                                    knife.setKnifeicon();
                                }
                            }
                            else{
                                axe.setUpgrade_level(axe.getUpgrade_level()+1);
                                if(axe.getUpgrade_level()==2){
                                    axe.upgrade();
                                }
                                else if(axe.getUpgrade_level()==1){
                                    axe.setAxeicon();
                                }
                            }

                        }
                        if(chests.get(i).getClass()==cchest.getClass()&&!chests.get(i).getopen()){
                            coinss[0] = coinss[0] + 50;
                            coinscounter.setText(Integer.toString(coinss[0]));
                        }
                        chests.get(i).setOpen();
                        chests.get(i).run();
                        break;
                    }
                }
                if(hero.getHero().getBoundsInParent().intersects(wtnt.chestimg().getBoundsInParent())){
                    wtnt.setOpen();
                    int tntdead = wtnt.run(hero);
                    System.out.println("tntdead"+tntdead);
                    if(tntdead == 1) {
                        this.stop();
                        died(hero);
                    }
                }



                menu.getSave().setOnMouseClicked(e -> {

                });

                gameoverwindow.getRestart1().setOnMouseClicked(e -> {
                    try {
                        restart(gamestage);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });


                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if(keyEvent.getCode()== KeyCode.SPACE) {


                                for (ImageView i : gameelements) {
                                    TranslateTransition backshift = new TranslateTransition();
                                    backshift.setDuration(Duration.seconds(0.1));
                                    backshift.setByX(-100);
                                    dashc[0]++;
                                    counter.setText(Integer.toString(dashc[0]/87));
                                    backshift.setNode(i);
                                    backshift.play();
                                }


                                if (knifeanimation.getStatus() == Animation.Status.STOPPED&& wchest.getopen()&&knife.isEquipped()&&knife.getUpgrade_level()>=1) {

                                    knife.run(knifeanimation, hero.getHero().getBoundsInParent());
                                    knifeanimation.play();
                                } else {
                                    if(knife.isEquipped()&&knife.getUpgrade_level()>=1){
                                        knife1.run(knifeanimation1, hero.getHero().getBoundsInParent());
                                        knifeanimation1.play();}

                                }
                            if (axeanimation.getStatus() == Animation.Status.STOPPED&& axe.getUpgrade_level()>=1&&axe.isEquipped()) {
                                System.out.println("status : "+axeanimation.getStatus());
                                axe.run(rotate,axe_bac,axe_for,axe_back,axe_forward,axeanimation, hero.getHero().getBoundsInParent());
                                axeanimation.play();
                            }

                                AnimationTimer temp = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        for (int i = 0; i < orcs.size(); i++) {
                                            if ((knife.getKnife().getBoundsInParent().intersects(orcs.get(i).getHero().getBoundsInParent()) || knife1.getKnife().getBoundsInParent().intersects(orcs.get(i).getHero().getBoundsInParent()))&&((knife1.getKnife().getBoundsInParent().getCenterY()<=orcs.get(i).getHero().getBoundsInParent().getMaxY()&&knife1.getKnife().getBoundsInParent().getCenterY()>=orcs.get(i).getHero().getBoundsInParent().getMinY())||(knife.getKnife().getBoundsInParent().getCenterY()<=orcs.get(i).getHero().getBoundsInParent().getMaxY()&&knife.getKnife().getBoundsInParent().getCenterY()>=orcs.get(i).getHero().getBoundsInParent().getMinY()))) {
                                                if(wchest.getopen()&&knife.isEquipped()) {
                                                    orcs.get(i).setDead(1);
                                                    orcs.get(i).death(death);
                                                    death.play();
                                                }
                                            }
                                            if ((axe.getKnife().getBoundsInParent().intersects(orcs.get(i).getHero().getBoundsInParent()) )&&((axe.getKnife().getBoundsInParent().getCenterY()<=orcs.get(i).getHero().getBoundsInParent().getMaxY()&&axe.getKnife().getBoundsInParent().getCenterY()>=orcs.get(i).getHero().getBoundsInParent().getMinY()))) {
                                                if(wchest.getopen()&&axe.isEquipped()) {
                                                    orcs.get(i).setDead(1);
                                                    orcs.get(i).death(death);
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
                            axe.setEquippedfalse();
                        }
                        if(keyEvent.getCode()==KeyCode.DIGIT2){
                            knife.setEquippedfalse();
                            knife1.setEquippedfalse();
                            axe.setEquippedTrue();
                        }

                    }
                });



                for (int i = 0; i < orcs.size(); i++) {

                    if (orcs.get(i).getHero().getBoundsInParent().intersects(hero.getHero().getBoundsInParent())) {
                        double gbot = orcs.get(i).getHero().getBoundsInParent().getMinY();
                        double gtop = orcs.get(i).getHero().getBoundsInParent().getMaxY();
                        double hbot = hero.getHero().getBoundsInParent().getMinY();
                        double htop = hero.getHero().getBoundsInParent().getMaxY();
                        if (!((htop-gbot>100) &&  ((htop<=gtop && hbot>=gbot) || (htop>=gtop&& hbot>=gbot)))) {
                            System.out.println(htop);
                            System.out.println(hbot);
                            System.out.println(gtop);
                            System.out.println(gbot);
                            TranslateTransition orcsidecollision = new TranslateTransition();
                            orcsidecollision.setNode(orcs.get(i).getHero());
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
                                    orcfall.setNode(orcs.get(finalI).getHero());
                                    orcfall.play();
                                    orcfall.setOnFinished(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            System.out.println("1111111111");
                                            orcs.get(finalI).getHero().setVisible(false);
                                            orcs.get(finalI).setDead(1);
                                        }
                                    });
                                    AnimationTimer orc = new AnimationTimer() {
                                        @Override
                                        public void handle(long l) {
                                            for (int j = 0; j < cislands.getALLislands().size(); j++) {
                                                if (cislands.getIslands().get(j).getBoundsInParent().intersects(orcs.get(finalI).getHero().getBoundsInParent())) {
                                                    orcfall.pause();
                                                    TranslateTransition orcjum = new TranslateTransition();
                                                    orcs.get(finalI).set_orc_jump(orcjum);
                                                    orcjum.play();
                                                }
                                            }
                                        }
                                    };
                                    orc.start();
                                }


                            });


                        }
                        else{
                            System.out.println("youd idead");
                            System.out.println(htop);
                            System.out.println(hbot);
                            System.out.println(gtop);
                            System.out.println(gbot);
                            this.stop();
                            died(hero);

                        }

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
    void died(Hero hero){
        TranslateTransition dead = new TranslateTransition();
        RotateTransition rotate = new RotateTransition();
        ScaleTransition scale = new ScaleTransition();
        ParallelTransition parallel = new ParallelTransition(dead,rotate,scale);
        hero.death(dead,scale,rotate,parallel);
        parallel.play();
        parallel.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameover();
            }
        });
    }
    void gameover(){
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


    public void start(Stage primaryStage) throws IOException {
        startGame(primaryStage);
    }
}