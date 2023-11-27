package com.example.project_main.SpaceWar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main_game extends Application {

    private Stage stage;

    private Stage mainBigStage;

    public Main_game(Stage stage, Stage mainBigStage) {
        this.stage = stage;
        this.mainBigStage = mainBigStage;
    }

    // variables
    public static final double HEIGHT = 720;
    public static final double WIDTH = 480;

    //javaFx sounds
    private AudioClip laserSound;

    // javafx attributes
    private Stage mainStage;
    private AnimationTimer game;
    private Pane root = new Pane();
    private VBox rootBox = new VBox();
    private Scene scene;
    private Scene gameOverScene;
    private List<ImageView> images = new ArrayList<ImageView>();

    // game state boolean
    boolean isHumanCollisionMeteors = false;
    boolean isBulletCollisionMeteor = false;
    boolean isGameOver = false;
    boolean isEngtoViet = true;
    boolean isFallOver = false;

    // game object
    Player player = new Player(WIDTH / 16, 5 * HEIGHT / 6, WIDTH / 8, HEIGHT / 8, "banana");
    List<Meteorites> Asteroids = new ArrayList<Meteorites>();
    List<Bullets> bullets = new ArrayList<Bullets>();
    Words wordList = new Words();

    List<String> words = new ArrayList<String>(); // this random 4 words into wordsInGame

    private void update() {
        // System.out.println(player.getPoint());

        if (isGameOver) {
            mainStage.setScene(gameOverScene);
            images.clear();
            bullets.clear();
            Asteroids.clear();
            if (gameOverScene == null) {
                gameOverScene = new Scene(VboxDef(), WIDTH, HEIGHT);
            }
        }

        if (isHumanCollisionMeteors) {
            isHumanCollisionMeteors = false;
            // System.out.println("isHumanCollisionMeteors");
            isGameOver = true;
        }

        if (player.getHp() == 0) {
            isGameOver = true;
        }

        if (isFallOver) {
            if (isEngtoViet) {
                words.clear();
                words = wordList.randomWords();
                player.setText(wordList.getMeaning(words));
            } else {
                words.clear();
                words = wordList.randomMeanings();
                player.setText(wordList.getWord(words));
            }

            Iterator<Meteorites> asteroidIterator2 = Asteroids.iterator();
            while (asteroidIterator2.hasNext()) {
                Meteorites meteo = asteroidIterator2.next();
                root.getChildren().removeAll(meteo.getRect(), meteo.getSprite(), meteo.getContent());
                asteroidIterator2.remove();
            }

            for (int i = 0; i < 4; i++) {
                Meteorites newMeteor = new Meteorites((i * WIDTH / 4), -150, WIDTH / 4, HEIGHT / 6,
                        words.get(i));
                Asteroids.add(newMeteor);
                root.getChildren().addAll(newMeteor.getRect(), newMeteor.getSprite(), newMeteor.getContent());
            }
        }

        Iterator<Meteorites> asteroidIterator = Asteroids.iterator();
        while (asteroidIterator.hasNext()) {
            Meteorites meteor = asteroidIterator.next();
            isFallOver = meteor.falldown(HEIGHT);

            if (meteor.checkCollision(player.getRect())) {
                isHumanCollisionMeteors = true;
            }
        }

        Iterator<Bullets> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullets bullet = iterator.next();
            Iterator<Meteorites> asteroidIterator2 = Asteroids.iterator();
            while (asteroidIterator2.hasNext()) {
                Meteorites meteor = asteroidIterator2.next();
                if (bullet.checkCollision(meteor.getRect())) {
                    if (isEngtoViet) {
                        if (wordList.isRightWord(meteor.getContent().getText(), player.getContent().getText())) {
                            root.getChildren().removeAll(meteor.getRect(), meteor.getSprite(), meteor.getContent());
                            asteroidIterator2.remove();
                            player.setPoint(player.getPoint() + 1);
                            isEngtoViet = false;
                        } else {
                            root.getChildren().removeAll(images.get(player.getHp() - 1));
                            images.remove(player.getHp() - 1);
                            player.setHp(player.getHp() - 1);
                        }
                    } else {
                        if (wordList.isRightWord(player.getContent().getText(), meteor.getContent().getText())) {
                            root.getChildren().removeAll(meteor.getRect(), meteor.getSprite(), meteor.getContent());
                            asteroidIterator2.remove();
                            isEngtoViet = true;
                            player.setPoint(player.getPoint() + 1);
                        } else {
                            root.getChildren().removeAll(images.get(player.getHp() - 1));
                            images.remove(player.getHp() - 1);
                            player.setHp(player.getHp() - 1);
                        }
                    }

                    root.getChildren().remove(bullet.getRect());
                    iterator.remove();
                    break;
                }
            }
        }

        if (!bullets.isEmpty()) {
            Iterator<Bullets> iteratorBullet = bullets.iterator();
            while (iteratorBullet.hasNext()) {
                Bullets bullet = iteratorBullet.next();
                if (bullet.fire() > HEIGHT) {
                    root.getChildren().remove(bullet.getRect());
                    iteratorBullet.remove();
                }
            }
        }
    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        root = (Pane) createContent();
        scene = new Scene(root);

        // System.out.println(player.getRect().getLayoutY());

        setKey(scene);

        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        Pane rootB = new Pane();
        rootB.setPrefSize(WIDTH, HEIGHT);
        rootB.setStyle("-fx-background-color:black;");

        player.resetPos(WIDTH);
        player.setPoint(0);

        laserSound = new AudioClip(getClass().getResource("/laserShot.wav").toString());

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(new Image(getClass().getResource("/Image/heart.png").toString()));
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);

            // Set translations
            imageView.setTranslateX(20 + i * 30);
            imageView.setTranslateY(20);

            images.add(imageView);
            rootB.getChildren().add(imageView);
        }

        if (isEngtoViet) {
            words = wordList.randomWords();
            player.setText(wordList.getMeaning(words));
        }

        for (int i = 0; i < 3; i++) {
            Line tmp = new Line((i + 1) * WIDTH / 4, 0, (i + 1) * WIDTH / 4, HEIGHT);
            tmp.setStroke(Color.WHITE);
            rootB.getChildren().add(tmp);
        }

        rootB.getChildren().addAll(player.getRect(), player.getContent(), player.getSprite());

        Asteroids.clear();
        for (int i = 0; i < 4; i++) {
            Meteorites meteor = new Meteorites((i * WIDTH / 4), 0, WIDTH / 4, HEIGHT / 6, words.get(i));
            Asteroids.add(meteor);
            rootB.getChildren().addAll(meteor.getRect(), meteor.getSprite(), meteor.getContent());
        }

        // Create the AnimationTimer if it hasn't been created yet

        if (game == null) {
            game = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            // Start the AnimationTimer
            game.start();
        }

        return rootB;
    }

    private Parent VboxDef() {
        rootBox = new VBox();
        rootBox.setStyle("-fx-background-color: lightblue;");
        rootBox.setAlignment(Pos.CENTER);

        Region spacer1 = new Region();
        spacer1.setPrefHeight(40);
        Region spacer2 = new Region();
        spacer2.setPrefHeight(20);

        Label gameOverLabel = new Label("Game Over!");
        gameOverLabel.setTextFill(Color.GREEN);
        gameOverLabel.setStyle("-fx-font-size: 40px; -fx-padding: 0.5;");

        Label gamePoint = new Label("Your Point: " + Integer.toString(player.getPoint()));
        gamePoint.setStyle("-fx-font-size: 20px;");

        Button restartButton = new Button("Restart Game");
        restartButton.setMaxSize(200, 60);
        restartButton.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-background-color: lightgreen; -fx-font-size: 16px;");
        restartButton.setTextFill(Color.GREEN);

        restartButton.setOnMouseEntered(e -> {
            restartButton.setStyle("-fx-border-color: white; -fx-border-width: 2px; -fx-background-color: lightblue; -fx-font-size: 16px;");
            restartButton.setTextFill(Color.YELLOW);
        });

        restartButton.setOnMouseExited(e -> {
            restartButton.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-background-color: lightgreen; -fx-font-size: 16px;");
            restartButton.setTextFill(Color.GREEN);
        });

        restartButton.setOnAction(e -> {
            // restart boolean
            isHumanCollisionMeteors = false;
            isGameOver = false;
            isBulletCollisionMeteor = false;
            isEngtoViet = true;
            player.setHp(3);

            // restart method
            root.getChildren().clear();
            root = (Pane) createContent();
            gameOverScene = null;
            scene = new Scene(root, WIDTH, HEIGHT);
            setKey(scene);
            mainStage.setScene(scene);
            game.start();
        });

        Button quitButton = new Button("Exit");
        quitButton.setMaxSize(200, 60);
        quitButton.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-background-color: lightgreen; -fx-font-size: 16px;");
        quitButton.setTextFill(Color.GREEN);

        quitButton.setOnMouseEntered(e -> {
            quitButton.setStyle("-fx-border-color: white; -fx-border-width: 2px; -fx-background-color: lightblue; -fx-font-size: 16px;");
            quitButton.setTextFill(Color.YELLOW);
        });

        quitButton.setOnMouseExited(e -> {
            quitButton.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-background-color: lightgreen; -fx-font-size: 16px;");
            quitButton.setTextFill(Color.GREEN);
        });

        quitButton.setOnMouseClicked(e -> {stage.close(); mainBigStage.show();});

        rootBox.getChildren().addAll(gameOverLabel, gamePoint, spacer1, restartButton, spacer2, quitButton);
        return rootBox;
    }

    private void setKey(Scene Ascene) {
        Ascene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft(WIDTH);
                    break;
                case D:
                    player.moveRight(WIDTH);
                    break;
                case SPACE:
                    Bullets bullet = new Bullets(player, 10, 25);
                    root.getChildren().add(bullet.getRect());
                    bullets.add(bullet);
                    bullet = null;
                    laserSound.play();
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}