package com.example.project_main;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox ngonngu;

    String change_to = "";
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
            String select = "";
            if(change_to.equals("Vietnamese")) {
                select = "vi";
            } else if (change_to.equals("Japanese")) {
                select = "ja";
            } else {
                select = "en";
            }
            String word_target = word.getText();
            if(!translate("ja", select, word_target).contains("<!DOCTYPE")) {
                maining.setText(translate("ja", select, word_target));
                System.out.println("ja" + " " + select);
            }
            else if(!translate("vi", select, word_target).contains("<!DOCTYPE")) {
                maining.setText(translate("vi", select, word_target));
                System.out.println("vi" + " " + select);
            }
            else if(!translate("en", select, word_target).contains("<!DOCTYPE")){
                maining.setText(translate("en", select, word_target));
                System.out.println("en" + " " + select);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Từ của bạn không đúng ngôn ngữ trên");
                alert.showAndWait();
                word.setText("");
                maining.setText("");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ngonngu.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            if (newValue != null) {
                change_to = newValue;
            }
        });
    }

    public void ClickWord(MouseEvent mouseEvent) {
        maining.setText("");
    }
}
