package com.example.helloapplication;



import javafx.animation.Animation;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class game {

    @FXML
    private Pane gameover;

    @FXML
    private Button quitbutton;

    @FXML
    private Button finalexit;

    @FXML
    private Pane quitpane;

    @FXML
    private Pane pausepane;

    @FXML
    private ImageView pause;

    @FXML
    private Button resume;

    @FXML
    private ImageView island;

    @FXML
    private ImageView island1;

    @FXML
    private ImageView island2;

    @FXML
    private ImageView orc;
    public void start(Stage gamestage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gamescene.fxml"));
//        Rectangle rectangle = new Rectangle();
//        rectangle.setFill(Color.BLUE);
//        rectangle.setHeight(70);
//        rectangle.setWidth(50);
//        rectangle.setLayoutX(50);
//        rectangle.setLayoutY(290);
//
//        Pane root = new Pane();
//        root.getChildren().add(rectangle);
        Scene scene = new Scene(root, 1200, 600);
        Hero hero = new Hero(scene);
        Green_Orc grorc = new Green_Orc(scene);
        pause = (ImageView) scene.lookup("#pause");
        pausepane = (Pane) scene.lookup("#pausepane");
        gameover = (Pane) scene.lookup("#gameover");
        quitpane = (Pane) scene.lookup("#quitpane");
        quitbutton = (Button) scene.lookup("#quitbutton");
        finalexit = (Button) scene.lookup("#finalexit");
        pausepane.setVisible(false);
        gameover.setVisible(false);
        quitpane.setVisible(false);

        resume = (Button) scene.lookup("#resume");
        island = (ImageView) scene.lookup("#island");
        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        orc = (ImageView) scene.lookup("#orc");
        TranslateTransition orcjump = new TranslateTransition();

        orcjump.setDuration(Duration.seconds(0.55));
        orcjump.setByY(-100);
        orcjump.setCycleCount(Animation.INDEFINITE);
        orcjump.setAutoReverse(true);
        orcjump.setNode(orc);
        orcjump.play();

        TranslateTransition jump = new TranslateTransition();
        jump.setDuration(Duration.millis(600));
        jump.setByY(-200);
        jump.setNode(hero.getHero());


        TranslateTransition fall= new TranslateTransition();
        fall.setDuration(Duration.millis(12));
        fall.setByY(4);
        fall.setCycleCount(1);
        fall.setAutoReverse(false);
        fall.setNode(hero.getHero());
        fall.play();
        final int[] flag1 = {0};

//
//
        fall.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int flag = 0;


                Bounds boundshero = hero.getHero().localToScene(hero.getHero().getBoundsInLocal());
                Bounds boundsisland = island.localToScene(island.getBoundsInLocal());
                Bounds boundsisland1 = island1.localToScene(island.getBoundsInLocal());
                Bounds boundsisland2 = island2.localToScene(island.getBoundsInLocal());
                ArrayList<Bounds> islands = new ArrayList<>();
                islands.add(boundsisland);
                islands.add(boundsisland1);
                islands.add(boundsisland2);
                if(boundshero.getMinY()>=600&& flag1[0] ==0){
                    flag1[0] =1;
                    gameover.setVisible(true);

//                    orcjump.pause();
                    quitbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            gameover.setVisible(false);
                            quitpane.setVisible(true);
                            finalexit.setOnMouseClicked(new EventHandler<MouseEvent>() {
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

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case SPACE:

                        fall.pause();
                        TranslateTransition translate = new TranslateTransition();
                        translate.setDuration(Duration.seconds(0.07));
                        translate.setByX(100);
                        translate.setNode(hero.getHero());
                        translate.play();
                        fall.play();
//                            Bounds boundsInScene = hero.localToScene(hero.getBoundsInLocal());
//                            Bounds boundsI = island.localToScene(island.getBoundsInLocal());
//                            System.out.println(boundsInScene.getMaxY()+" "+ boundsI.getMinY());
//                            System.out.println(hero.getX()+" "+hero.getLayoutX());
                }
            }
        });

        pause.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pausepane.setVisible(true);
                jump.pause();
                fall.pause();
                orcjump.pause();
            }
        });
        resume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pausepane.setVisible(false);
                fall.play();
                orcjump.play();
            }
        });

        gamestage.setScene(scene);
        gamestage.show();

    }


}
