package com.example.project_main.MemoryGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DialogController {
    @FXML
    private Label bestScoreLabel;

    @FXML
    private Button endGameButton;

    @FXML
    private Button playAgainButton;

    @FXML
    private Label scoreLabel;

    private Stage mainStage;

    @FXML
    void endGame(ActionEvent event) {
        dialogStage.close();
        memoryGameStage.close();
        mainStage.show();
    }

    @FXML
    void playAgain(ActionEvent event) {
        dialogStage.close();
    }

    private int score;

    private int bestScore;

    private Stage dialogStage;

    private Stage memoryGameStage;


    public DialogController(int score, int bestScore, Stage dialogStage, Stage memoryGameStage, Stage mainStage) {
        this.score = score;
        this.dialogStage = dialogStage;
        this.mainStage = mainStage;
        this.memoryGameStage = memoryGameStage;
        this.bestScore = bestScore;
    }
    public void initialize() {
        scoreLabel.setText(Integer.toString(score));
        bestScoreLabel.setText(Integer.toString(bestScore));
        endGameButton.setOnAction(this::endGame);
        playAgainButton.setOnAction(this::playAgain);
    }

}
