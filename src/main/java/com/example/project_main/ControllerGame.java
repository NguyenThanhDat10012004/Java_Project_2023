package com.example.project_main;

import com.example.project_main.MemoryGame.MemoryGameController;
import com.example.project_main.SpaceWar.Main_game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ControllerGame extends ControllerMain {

    private Stage mainStage;

    @Override
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private AnchorPane Game;
    protected Stage dialogStage = new Stage();
    protected Scene scene ;
    public void PlayGame1(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/View/memory_game.fxml"));
//        Parent root = fxmlLoader.load();
//        scene = new Scene(root);
//        dialogStage.setTitle("Dictionary");
//        dialogStage.setScene(scene);
//        dialogStage.show();
        Stage memoryGameStage = new Stage();
        MemoryGameController memoryGameController = new MemoryGameController(memoryGameStage, mainStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/memory_game.fxml"));
        loader.setController(memoryGameController);
        try {
            Parent root = null;
            root = loader.load();
            Scene scene = new Scene(root);
            memoryGameStage.setScene(scene);
            mainStage.hide();
            memoryGameStage.show();
//            memoryGameStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void PlayGame2(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Main_game x = new Main_game(stage, mainStage);
        mainStage.hide();
        x.start(stage);
    }
}
