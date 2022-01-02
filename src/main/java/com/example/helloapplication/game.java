package com.example.helloapplication;


import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private Label saved_entry;

    @FXML
    private Group chests;
    @FXML
    private ImageView defaultchest;



    @FXML
    private ImageView island1;
    @FXML
    private ImageView her;
    @FXML
    private ImageView respawn;

    @FXML
    private TranslateTransition fall;

    private gameoverwindow gameoverwindow;
    void cleanup() {
        // stop animations reset model ect.
    }

    private Orcs grorc;
    private Orcs grorc1;
    private Orcs grorc2;
    private Orcs grorc3;
    private Orcs grorc4;
    private Orcs rorc1;
    private Orcs rorc2;
    private Orcs rorc3;
    private Orcs rorc4;
    private islands cislands;
    private chests wchest;
    private chests cchest;
    private chests wchest1;
    private chests wchest2;
    private chests wchest3;
    private chests cchest1;
    private tnt wtnt;private tnt wtnt1;
    private Orcs borc;
    private boolean rez;


    void startGame(Stage gamestage, String s) throws IOException {

        // initialisation from start method goes here
        Parent root = FXMLLoader.load(getClass().getResource("gamescene.fxml"));
        rez = false;
        Scene scene = new Scene(root, 1200, 650);

        her = (ImageView) scene.lookup("#hero");
        Hero hero = new Hero(her);
        grorc = new Green_Orc(scene,"#orc");
        grorc1 = new Green_Orc(scene,"#orc11");
        grorc2 = new Green_Orc(scene,"#orc3");
        grorc3 = new Green_Orc(scene,"#orc4");
        grorc4 = new Green_Orc(scene,"#orc5");
        rorc1 = new Red_Orc(scene,"#rorc1");
        rorc2 = new Red_Orc(scene,"#rorc2");
        rorc4 = new Red_Orc(scene,"#rorc4");
        rorc3 = new Red_Orc(scene,"#rorc3");
        borc = new boss_orc(scene,"#bossorc");
        ArrayList<Orcs> orcs = new ArrayList<>();
        orcs.add(grorc);
        orcs.add(grorc1);
        orcs.add(grorc2);
        orcs.add(grorc3);
        orcs.add(grorc4);
        orcs.add(rorc1);
        orcs.add(rorc2);
        orcs.add(rorc3);
        orcs.add(rorc4);
        orcs.add(borc);
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

        cislands = new islands(scene);
        ArrayList<TranslateTransition> orcjumps = new ArrayList<>();
        for(int i =0; i<orcs.size();i++) {
            TranslateTransition orcjump = new TranslateTransition();
            orcjumps.add(orcjump);
            orcs.get(i).set_orc_jump(orcjumps.get(i));
            orcjumps.get(i).play();
        }

        TranslateTransition jump = new TranslateTransition();
        hero.set_hero_jump(jump);
        fall = new TranslateTransition();
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
        respawn = (ImageView) scene.lookup("#respawn");
        final int[] dashc;
        final int[] coinss;

        final int[] flag1 = {0};
        wchest = new weaponchest(scene,"#chests","#defaultchest");
        cchest = new coinchest(scene,"#coinchests","#defaultcoinchest");
        cchest1 = new coinchest(scene,"#coinchests1","#defaultcoinchest1");
        wchest1 = new weaponchest(scene,"#chestss1","#defaultchest1");
        wchest2 = new weaponchest(scene,"#chestss2","#defaultchest2");
        wchest3 = new weaponchest(scene,"#chestss3","#defaultchest3");

        ArrayList<chests> chests = new ArrayList<>();
        chests.add(wchest);
        chests.add(cchest);
        chests.add(cchest1);
        chests.add(wchest1);
        chests.add(wchest2);
        chests.add(wchest3);
        wtnt = new tnt(scene,"#tnts","#defaulttnt");
        wtnt1 = new tnt(scene,"#tnts1","#defaulttnt1");

        ArrayList<ImageView> gameelements = GameElements();

        respawn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        throwingknives knife = new throwingknives(scene, "#knife","#knifeupgrade1");
        throwingknives knife1 = new throwingknives(scene, "#knife1","#knifeupgrade");
        throwingAxe axe = new throwingAxe(scene,"#axe");
        gameelements.addAll(wchest.getChests_all());
        gameelements.addAll(wtnt.getChests_all());
        gameelements.addAll(wtnt1.getChests_all());
        gameelements.addAll(cchest.getChests_all());
        gameelements.addAll(cchest1.getChests_all());
        gameelements.addAll(wchest1.getChests_all());
        gameelements.addAll(wchest2.getChests_all());
        gameelements.addAll(wchest3.getChests_all());

        if(s.equals("")){

            hero = new Hero(her);
            dashc = new int[]{0};
            coinss = new int[]{0};
        }else{
            LoadGame L1 = new LoadGame();
            Serialized_obj L = L1.Load(s);
            setnewcoordinates(L, gameelements, hero);
            System.out.println(hero.getHero().getX());
            dashc = L.getDashc();
            coinss = L.getCoinc();
        }

        Hero finalHero = hero;
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
                menu.getPause().setOnMouseClicked(e ->{
                    menu.panevisible();
                });
                menu.getResume().setOnMouseClicked(e ->{
                    menu.paneinvisible();

//                    saved_entry.setText("Game Saved!");
                });
                menu.getSave().setOnMouseClicked(e ->{
                    menu.paneinvisible();
                    System.out.println("Ja rha hai bc");

                    ArrayList<Coordinate> c = new ArrayList<Coordinate>();
                    setcoordinates(gameelements, c, finalHero);
                    Serialized_obj o = new Serialized_obj(coinss, dashc, c);
                    SaveGame save = new SaveGame(o);
                    menu.panevisible();

                });
                menu.getExit().setOnMouseClicked(e ->{
                    menu.paneinvisible();
                    gameoverwindow.quitpanevisible();
                    gameoverwindow.getFinalexit().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Platform.exit();
                        }
                    });
                    gameoverwindow.getQuitno().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            gameoverwindow.quitpaneinvisible();
                        }
                    });
                });

                for (int i = 0; i < cislands.getALLislands().size(); i++) {
                    if (cislands.getIslands().get(i).getBoundsInParent().intersects(finalHero.getHero().getBoundsInParent())) {
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

                                if(finalHero.getHero().getBoundsInParent().getMinY()>=600&& finalHero.isAlive()){
                                  finalHero.setAlive(false);
                                    gameover(finalHero);





                }

                for(int i =0; i<chests.size();i++) {
                    if (finalHero.getHero().getBoundsInParent().intersects(chests.get(i).chestimg().getBoundsInParent())) {
                        System.out.println("chesting");
                        if(chests.get(i).getClass()==wchest.getClass()&&!chests.get(i).getopen()){
                            Random weapo = new Random();
                            int weapon = weapo.nextInt(2);
                            if(weapon==0){
                                if(knife.getUpgrade_level()==2&&axe.getUpgrade_level()<2){
                                    axe.setUpgrade_level(axe.getUpgrade_level()+1);
                                    if(axe.getUpgrade_level()==2){
                                        axe.upgrade();
                                    }
                                    else if(axe.getUpgrade_level()==1){
                                        axe.setAxeicon();
                                    }
                                }
                                else {
                                    knife.setUpgrade_level(knife.getUpgrade_level() + 1);
                                    knife1.setUpgrade_level(knife1.getUpgrade_level() + 1);
                                    if (knife.getUpgrade_level() == 2) {
                                        knife.upgrade();
                                        knife1.upgrade();

                                    } else if (knife.getUpgrade_level() == 1) {
                                        knife.setKnifeicon();
                                    }
                                }
                            }
                            else{
                                if(axe.getUpgrade_level()==2&&knife.getUpgrade_level()<2){
                                    knife.setUpgrade_level(knife.getUpgrade_level() + 1);
                                    knife1.setUpgrade_level(knife1.getUpgrade_level() + 1);
                                    if (knife.getUpgrade_level() == 2) {
                                        knife.upgrade();
                                        knife1.upgrade();

                                    } else if (knife.getUpgrade_level() == 1) {
                                        knife.setKnifeicon();
                                    }
                                }
                                else {
                                    axe.setUpgrade_level(axe.getUpgrade_level() + 1);
                                    if (axe.getUpgrade_level() == 2) {
                                        axe.upgrade();
                                    } else if (axe.getUpgrade_level() == 1) {
                                        axe.setAxeicon();
                                    }
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
                if(finalHero.getHero().getBoundsInParent().intersects(wtnt.chestimg().getBoundsInParent())){
                    wtnt.setOpen();
                    int tntdead = wtnt.run(finalHero);
                    System.out.println("tntdead"+tntdead);
                    if(tntdead == 1) {
                        this.stop();
                        died(finalHero,this);
                    }
                }
                if(finalHero.getHero().getBoundsInParent().intersects(wtnt1.chestimg().getBoundsInParent())){
                    wtnt1.setOpen();
                    int tntdead = wtnt1.run(finalHero);
                    System.out.println("tntdead"+tntdead);
                    if(tntdead == 1) {
                        this.stop();
                        died(finalHero,this);
                    }
                }





                gameoverwindow.getRestart1().setOnMouseClicked(e -> {
                    try {
                        restart(gamestage,s);
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
                                    counter.setText(Integer.toString(dashc[0]/113));
                                    backshift.setNode(i);
                                    backshift.play();
                                }


                                if (knifeanimation.getStatus() == Animation.Status.STOPPED&& wchest.getopen()&&knife.isEquipped()&&knife.getUpgrade_level()>=1) {

                                    knife.run(knifeanimation, finalHero.getHero().getBoundsInParent());
                                    knifeanimation.play();
                                } else {
                                    if(knife.isEquipped()&&knife.getUpgrade_level()>=1){
                                        knife1.run(knifeanimation1, finalHero.getHero().getBoundsInParent());
                                        knifeanimation1.play();}

                                }
                            if (axeanimation.getStatus() == Animation.Status.STOPPED&& axe.getUpgrade_level()>=1&&axe.isEquipped()) {
                                System.out.println("status : "+axeanimation.getStatus());
                                axe.run(rotate,axe_bac,axe_for,axe_back,axe_forward,axeanimation, finalHero.getHero().getBoundsInParent());
                                axeanimation.play();
                            }

                                AnimationTimer temp = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        for (int i = 0; i < orcs.size(); i++) {
                                            if ((knife.getKnife().getBoundsInParent().intersects(orcs.get(i).getHero().getBoundsInParent()) || knife1.getKnife().getBoundsInParent().intersects(orcs.get(i).getHero().getBoundsInParent()))&&((knife1.getKnife().getBoundsInParent().getCenterY()<=orcs.get(i).getHero().getBoundsInParent().getMaxY()&&knife1.getKnife().getBoundsInParent().getCenterY()>=orcs.get(i).getHero().getBoundsInParent().getMinY())||(knife.getKnife().getBoundsInParent().getCenterY()<=orcs.get(i).getHero().getBoundsInParent().getMaxY()&&knife.getKnife().getBoundsInParent().getCenterY()>=orcs.get(i).getHero().getBoundsInParent().getMinY()))) {
                                                if(wchest.getopen()&&knife.isEquipped()) {
                                                    if(orcs.get(i)==borc){
                                                        if(borc.getHealth()==10000){
                                                            System.out.println("nibba do be ded");
                                                            orcs.get(i).setDead(1);
                                                            orcs.get(i).death(death);
                                                            death.play();
                                                        }
                                                        else{
                                                            System.out.println(borc.getHealth()+"<- health");
                                                            borc.setHealth(borc.getHealth()+1);
                                                        }
                                                    }
                                                    else {
                                                        System.out.println("nibba do be ded");
                                                        orcs.get(i).setDead(1);
                                                        orcs.get(i).death(death);
                                                        death.play();
                                                    }
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
//                        if(keyEvent.getCode()==KeyCode.K){
//                            Serialized_obj o = new Serialized_obj(hero, coinss, dashc);
//                            SaveGame save = new SaveGame(o);
//                        }

                    }
                });



                for (int i = 0; i < orcs.size(); i++) {

                    if ((orcs.get(i).getHero().getBoundsInParent().intersects(finalHero.getHero().getBoundsInParent()))&&!orcs.get(i).isDea()) {
                        double gbot = orcs.get(i).getHero().getBoundsInParent().getMinY();
                        double gtop = orcs.get(i).getHero().getBoundsInParent().getMaxY();
                        double hbot = finalHero.getHero().getBoundsInParent().getMinY();
                        double htop = finalHero.getHero().getBoundsInParent().getMaxY();
                        if ((!((htop-gbot>100) &&  ((htop<=gtop && hbot>=gbot) || (htop>=gtop&& hbot>=gbot)))||orcs.get(i).getClass()==borc.getClass())) {
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
                            died(finalHero,this);


                        }

                    }
                }
            }
        };
        timer.start();

        gamestage.setScene(scene);
        gamestage.show();


    }

    private void setnewcoordinates(Serialized_obj c, ArrayList<ImageView> gameelements, Hero hero) {
        for(int i = 0; i< gameelements.size(); i++){
            gameelements.get(i).setLayoutX(c.getCoordinates().get(i).getLayoutx());
            gameelements.get(i).setLayoutY(c.getCoordinates().get(i).getLayouty());
        }
        hero.getHero().setLayoutX(c.getCoordinates().get(c.getCoordinates().size()-1).getLayoutx());
        hero.getHero().setLayoutY(c.getCoordinates().get(c.getCoordinates().size()-1).getLayouty());

    }

    void setcoordinates(ArrayList<ImageView> gameelements, ArrayList<Coordinate> c, Hero hero){
        for(int i = 0; i< gameelements.size(); i++){
            c.get(i).setLayoutx(gameelements.get(i).getLayoutX());
            c.get(i).setLayouty(gameelements.get(i).getLayoutY());
        }

        Coordinate e = new Coordinate();
        e.setLayoutx(hero.getHero().getLayoutX());
        e.setLayouty(hero.getHero().getLayoutY());
        c.add(e);
    }
    void restart(Stage gamestage, String s) throws IOException {
        cleanup();
        startGame(gamestage, "");
    }
    void died(Hero hero,AnimationTimer timer){
        TranslateTransition dead = new TranslateTransition();
        RotateTransition rotate = new RotateTransition();
        ScaleTransition scale = new ScaleTransition();
        ParallelTransition parallel = new ParallelTransition(dead,rotate,scale);
        hero.death(dead,scale,rotate,parallel);
        parallel.play();
        parallel.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameover(hero);
                timer.start();


            }
        });
    }
    void gameover(Hero hero){
        gameoverwindow.gameoverpanevisible();
        respawn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (Integer.parseInt(coinscounter.getText()) >= 100&&!rez) {
                    gameoverwindow.gameoverpaneinvisible();
                    TranslateTransition respawnn = new TranslateTransition();
                    respawnn.setDuration(Duration.millis(1800));
                    respawnn.setToY(-270);
                    respawnn.setCycleCount(1);
                    respawnn.setAutoReverse(false);
                    respawnn.setNode(hero.getHero());
                    respawnn.play();
                    respawnn.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("we in");
                            hero.set_hero_fall(fall);
                            fall.play();
                            hero.setAlive(true);
                            rez=true;
                            coinscounter.setVisible(false);
                            respawn.setVisible(false);

                        }
                    });

                }

            }
        });


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

        gameoverwindow.getQuitno().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameoverwindow.quitpaneinvisible();
                gameover(hero);
            }
        });

    }

    public ArrayList<ImageView> GameElements(){
        ArrayList<ImageView> gameelements = new ArrayList<>();
        gameelements.addAll(cislands.getIslands());
        gameelements.add(grorc.getHero());
        gameelements.add(grorc1.getHero());
        gameelements.add(grorc2.getHero());
        gameelements.add(grorc3.getHero());
        gameelements.add(grorc4.getHero());
        gameelements.add(rorc2.getHero());
        gameelements.add(rorc3.getHero());
        gameelements.add(rorc4.getHero());
        gameelements.add(rorc1.getHero());
        gameelements.add(wchest.chestimg());
        gameelements.add(cchest1.chestimg());
        gameelements.add(wtnt.chestimg());
        gameelements.add(wtnt1.chestimg());
        gameelements.add(wchest2.chestimg());
        gameelements.add(wchest3.chestimg());
        gameelements.add(cchest.chestimg());
        gameelements.add(wchest1.chestimg());
        gameelements.add(borc.getHero());
        return gameelements;
    }



    public void start(Stage primaryStage, String s) throws IOException {
        startGame(primaryStage, s);
    }
}