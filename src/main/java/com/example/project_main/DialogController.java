package com.example.project_main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogController {
    @FXML
    private Label bestScoreLabel;

    @FXML
    private Button endGameButton;

    @FXML
    private Button playAgainButton;

    @FXML
    private Label scoreLabel;

    @FXML
    void endGame(ActionEvent event) {
        dialogStage.close();
        memoryGameStage.close();
    }

    @FXML
    void playAgain(ActionEvent event) {
        dialogStage.close();
    }

    private int score;

    private int bestScore;

    private Stage dialogStage;

    private Stage memoryGameStage;

    public DialogController(int score, Stage dialogStage) {
        this.score = score;
        this.dialogStage = dialogStage;
        if (score > bestScore) {
            bestScore = score;
        }
    }

    public DialogController(int score, Stage dialogStage, Stage memoryGameStage) {
        this.score = score;
        this.dialogStage = dialogStage;
        this.memoryGameStage = memoryGameStage;
        if (score > bestScore) {
            bestScore = score;
        }
    }
    public void initialize() {
        scoreLabel.setText(Integer.toString(score));
        bestScoreLabel.setText(Integer.toString(bestScore));
        endGameButton.setOnAction(this::endGame);
        playAgainButton.setOnAction(this::playAgain);
    }

}
