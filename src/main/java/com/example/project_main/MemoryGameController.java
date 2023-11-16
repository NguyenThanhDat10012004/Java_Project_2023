package com.example.project_main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class MemoryGameController implements Initializable {
    String musicFile = "C:/Users/X1 gen 9/Downloads/Java_Project_2023/src/main/resources/corect.mp3";
    // For example
    private Stage memoryGameStage;

    public MemoryGameController(Stage memoryGameStage) {
        this.memoryGameStage = memoryGameStage;
    }

    @FXML
    private ImageView soundButton ;
    private Image firstImage = new Image(getClass().getResource("/Image/turnOn.png").toExternalForm());
    private Image secondImage = new Image(getClass().getResource("/Image/turnOff.png").toExternalForm());
    ;
    private boolean isFirstImage = true;
    private MediaPlayer mediaPlayer;

    private boolean sound = true;

    @FXML
    private Label correctLabel;

    @FXML
    private Label guesesLabel;

    @FXML
    private FlowPane labelFlowPane;

    private MemoryCard card1, card2;

    private ArrayList<MemoryCard> cardInGame;

    private int numOfGuess;

    private int numOfMatched;


    @FXML
    void playAgain(ActionEvent event) {
        playAgain();
    }

    void playAgain() {

        card1 = null;
        card2 = null;

        numOfGuess = 0;
        numOfMatched = 0;

        updateLabels();

        CardBank cardBank = new CardBank();

        cardBank.tronBai();

        cardInGame = new ArrayList<>();

        for (int i=0; i<5; i++) {

            Card card = cardBank.deck.get(i);

            cardInGame.add(new MemoryCard(card.getKeyCard(), card.getWordCard(), false));
            cardInGame.add(new MemoryCard(card.getWordCard(), card.getKeyCard(), false));

        }

        Collections.shuffle(cardInGame);


        for (int i = 0; i < labelFlowPane.getChildren().size(); i++) {

            Label label = (Label) labelFlowPane.getChildren().get(i);

            label.setVisible(true);

            label.setText(cardInGame.get(i).getKeyCard());
        }

        flipAllCard();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeLabel();

    }

    private void initializeLabel() {

        card1 = null;
        card2 = null;

        soundButton.setImage(firstImage);

        CardBank cardBank = new CardBank();

        cardBank.tronBai();

        cardInGame = new ArrayList<>();

        for (int i=0; i<5; i++) {

            Card card = cardBank.deck.get(i);

            cardInGame.add(new MemoryCard(card.getKeyCard(), card.getWordCard(), false));
            cardInGame.add(new MemoryCard(card.getWordCard(), card.getKeyCard(), false));

        }

        Collections.shuffle(cardInGame);

        for (int i = 0; i < labelFlowPane.getChildren().size(); i++) {

            Label label = (Label) labelFlowPane.getChildren().get(i);

            label.setText(cardInGame.get(i).getKeyCard());

            label.setUserData(i);

            label.setOnMouseClicked(evevt -> {

                flipCard((int) label.getUserData());

            });
        }
    }

    private void flipAllCard() {

        for (int i = 0; i < cardInGame.size(); i++) {

            Label label = (Label) labelFlowPane.getChildren().get(i);
            MemoryCard memoryCard = cardInGame.get(i);

//            label.setText(memoryCard.getKeyCard());

            if (memoryCard.isCheck()) {
                label.setText("");
                label.setVisible(false);
                label.setStyle("-fx-border-color: #1a237e; -fx-background-color: #1a237e");
            } else {
                label.setStyle("-fx-background-color: #283593");
            }
        }
    }

    private void flipCard(int indexOfCard) {

        if (card1 == null && card2 == null) {
            flipAllCard();
        }

        Label label = (Label) labelFlowPane.getChildren().get(indexOfCard);


        if (card1 == null) {

            card1 = cardInGame.get(indexOfCard);

            label.setStyle("-fx-background-color: #9fa8da");

        } else if (card2 == null) {

            numOfGuess++;

            card2 = cardInGame.get(indexOfCard);

            label.setStyle("-fx-background-color: #9fa8da");

            checkForMatch();

            updateLabels();

            flipAllCard();
        }

    }

    private void checkForMatch() {

        if (card1.isSameCard(card2)) {

            numOfMatched++;

            card1.setCheck(true);
            card2.setCheck(true);

            musicFile = "C:/Users/X1 gen 9/Downloads/Java_Project_2023/src/main/resources/corect.mp3";

            if (isFirstImage) music();

            if (checkEndGame()) {

                Stage dialogStage = new Stage();

                DialogController dialogController = new DialogController(50 - numOfGuess, dialogStage, memoryGameStage);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/dialog.fxml"));

                loader.setController(dialogController);

                try {
                    Parent root = null;
                    root = loader.load();
                    Scene scene = new Scene(root);
                    dialogStage.setScene(scene);
                    dialogStage.showAndWait();
                    playAgain();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            musicFile = "C:/Users/X1 gen 9/Downloads/Java_Project_2023/src/main/resources/false.mp3";
            if (isFirstImage) music();
        }
        card1 = null;
        card2 = null;
    }

    private void updateLabels() {
        correctLabel.setText(Integer.toString(numOfMatched));
        guesesLabel.setText(Integer.toString(numOfGuess));
    }

    boolean checkEndGame () {
        for (MemoryCard memoryCard : cardInGame) {
            if (!memoryCard.isCheck()) {
                return false;
            }
        }
        return true;
    }
    public void music() {
//        String musicFile = "C:/Users/X1 gen 9/Downloads/OOP_GameProject/src/main/resources/lofi-study-112191.mp3";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.setOnEndOfMedia(new Runnable() {
//            @Override
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//            }
//        });
        mediaPlayer.play();
    }
    public void musicHandle(MouseEvent event) {
        if (isFirstImage) {
            soundButton.setImage(secondImage); // Đặt hình ảnh thứ hai
            isFirstImage = false;
            mediaPlayer.pause();
        } else {
            soundButton.setImage(firstImage); // Đặt hình ảnh ban đầu
            isFirstImage = true;
            mediaPlayer.play();
        }
    }
}
