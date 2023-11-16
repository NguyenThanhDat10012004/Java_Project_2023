package com.example.project_main;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class ControllerMain {

    @FXML
    protected AnchorPane MainPane;
    private AnchorPane search;
    private AnchorPane Favor;
    private AnchorPane Insert;
    private AnchorPane Translate;
    private AnchorPane Game;


    private DictionaryManagement md;
    @FXML
    private Button searchButton;
    @FXML
    private Button favorbutton;
    @FXML
    private Button insertbutton;
    @FXML
    private Button gamebutton;
    @FXML
    private Button translate;
    protected static ControllerMain controllersearch = new ControllerMain();
    protected static ControllerMain controllerfavor = new ControllerMain();
    protected static ControllerFavor favor = new ControllerFavor();
    protected VoiceRSS voice = new VoiceRSS();
    public void init() throws IOException {
        md = new DictionaryManagement();
        md.insertFromFile();
    }
    public void initFavor() throws IOException {
        md = new DictionaryManagement();
        md.insertFromFileFavor();
    }
    public void reset() {
        searchButton.getStyleClass().removeAll("active");
        favorbutton.getStyleClass().removeAll("active");
        insertbutton.getStyleClass().removeAll("active");
        gamebutton.getStyleClass().removeAll("active");
        translate.getStyleClass().removeAll("active");
    }
    public void searchbutton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Search.fxml"));
        search = loader.load();
        reset();
        searchButton.getStyleClass().add("active");
        MainPane.getChildren().setAll(search);
    }
    public DictionaryManagement getMd() {
        return md;
    }

    public void favorbutton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Favor.fxml"));
        Favor = loader.load();
        reset();
        favorbutton.getStyleClass().add("active");
        MainPane.getChildren().setAll(Favor);
    }

    public void insert(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Insert.fxml"));
        Insert = loader.load();
        reset();
        insertbutton.getStyleClass().add("active");
        MainPane.getChildren().setAll(Insert);
    }

    public void translatebutton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Translate.fxml"));
        Translate = loader.load();
        reset();
        translate.getStyleClass().add("active");
        MainPane.getChildren().setAll(Translate);
    }

    public void putGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/game_window.fxml"));
        Game = loader.load();
        reset();
        gamebutton.getStyleClass().add("active");
        MainPane.getChildren().setAll(Game);
//        Stage memoryGameStage = new Stage();
//        MemoryGameController memoryGameController = new MemoryGameController(memoryGameStage);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/memory_game.fxml"));
//        loader.setController(memoryGameController);
//        try {
//            Parent root = null;
//            root = loader.load();
//            Scene scene = new Scene(root);
//            memoryGameStage.setScene(scene);
//            memoryGameStage.showAndWait();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
