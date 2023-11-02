package com.example.project_main;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class ControllerTranslate implements Initializable {
    public static void main(String[] args) throws IOException {
        String text = "にほんご";
        //Translated text: Hallo Welt!
        System.out.println("Translated text: " + translate("ja", "vi", text));
    }

    @FXML
    private TextArea word;
    @FXML
    private TextArea maining;
    @FXML
    private ComboBox select1;
    @FXML
    private ComboBox select2;

    String change_to = "";
    String change_from = "";
    private static String translate(String langFrom, String langTo, String text) throws IOException, UnsupportedEncodingException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbzIOeAutlWtcZEUQBVRCoeasN-aoZ-eLgHyGK72ekZPoTni9nKpS2aaZcCRC4h8OHRi/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void translateAPI(ActionEvent actionEvent) throws IOException {
        if(change_to.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Hãy chọn ngôn ngữ cần dịch");
            alert.showAndWait();
        }
        else {
            String select_to = "", select_from = "";
            if(change_to.equals("Vietnamese")) {
                select_to = "vi";
            } else if (change_to.equals("Japanese")) {
                select_to = "ja";
            } else {
                select_to = "en";
            }

            if(change_from.equals("Vietnamese")) {
                select_from = "vi";
            } else if (change_from.equals("Japanese")) {
                select_from = "ja";
            } else {
                select_from = "en";
            }
            String word_target = word.getText();
            maining.setText(translate(select_from, select_to, word_target));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        select2.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            if (newValue != null) {
                change_to = newValue;
            }
        });
        select1.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            if (newValue != null) {
                change_from = newValue;
            }
        });
    }

    public void ClickWord(MouseEvent mouseEvent) {
        maining.setText("");
    }
}
