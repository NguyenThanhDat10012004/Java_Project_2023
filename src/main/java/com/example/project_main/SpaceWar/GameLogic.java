package com.example.project_main.SpaceWar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Inside your game logic class or method
public class GameLogic {

    private Scene gamePlayScene;
    private Scene gameOverScene;

    public void setGameOverScene() {
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setAlignment(Pos.CENTER);

        Label gameOverLabel = new Label("Game Over!");
        Button restartButton = new Button("Restart Game");

        restartButton.setOnAction(e -> {
            // Handle restart logic (e.g., reset game state, switch to game scene)
        });

        gameOverLayout.getChildren().addAll(gameOverLabel, restartButton);

        gameOverScene = new Scene(gameOverLayout, Main_game.WIDTH, Main_game.HEIGHT);
    }

    public void setGamePlayScene() {

    }

    public Scene getGameOverScene() {
        return gameOverScene;
    }

    public Scene getGamePlayScene() {
        return gamePlayScene;
    }
}
