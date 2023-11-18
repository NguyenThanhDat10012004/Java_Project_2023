package com.example.project_main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static com.example.project_main.ControllerMain.controllersearch;
import static com.example.project_main.ControllerMain.controllerfavor;
public class Main extends Application   {



    public static void main(String[] args) throws IOException {
        controllersearch.init();
        controllerfavor.initFavor();
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Main.fxml"));
        Parent root = fxmlLoader.load();

        ControllerMain controllerMain = fxmlLoader.getController();
        controllerMain.setMainStage(stage);

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Dictionary");
        stage.setScene(scene);

        stage.show();
    }
}
