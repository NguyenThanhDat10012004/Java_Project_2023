package com.example.project_main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerSearch extends ControllerMain implements Initializable {


    protected final ObservableList<String> searchList = FXCollections.observableArrayList();

    @FXML
    private TextField searchword;
    @FXML
    private Label searchedword;
    @FXML
    private ListView listword;
    @FXML
    private WebView mainingword;
    @FXML
    private Button savebutton;
    @FXML
    private HTMLEditor mainingword1;
    @FXML
    private Button save;
    @FXML
    private AnchorPane PaneSearch;
    Boolean check = false;
    String maining;
    Boolean ok = false;
    Boolean geted = false;
    Boolean favor = true;
    String after = "";


    public void getwordsearch(ActionEvent actionEvent) {
        display();
    }

    public void setLabel(MouseEvent mouseEvent) {
        save.getStyleClass().removeAll("change-image");
        searchedword.setText("");
        mainingword.getEngine().loadContent("");
        geted = false;
    }

    public boolean check(String target, String Word) {
        if (target.length() > Word.length()) return false;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != Word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PaneSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.ENTER) {
                    display();
                }
            }
        });
        searchword.setOnKeyPressed(event -> {
            save.getStyleClass().removeAll("change-image");
            if (event.getCode() == KeyCode.DOWN) {
                listword.requestFocus();
                int selectedIndex = listword.getSelectionModel().getSelectedIndex();
                if (selectedIndex < listword.getItems().size() - 1) {
                    listword.getSelectionModel().select(selectedIndex + 1);
                }
                event.consume();
            } else if (event.getCode() == KeyCode.UP) {
                listword.requestFocus();
                int selectedIndex = listword.getSelectionModel().getSelectedIndex();
                if (selectedIndex > 0) {
                    listword.getSelectionModel().select(selectedIndex - 1);
                }
                event.consume();
            }
        });
        listword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selectedItem = (String) listword.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    Platform.runLater(() -> {
                        searchword.setText(selectedItem);
                        searchword.requestFocus();
                        display();
                    });
                }

                event.consume();
            }
        });

        searchword.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                searchList.clear();
                if (after.length() != searchword.getText().length()) {
                    searchedword.setText("");
                    mainingword.getEngine().loadContent("");
                    maining = "";
                }
                if (!searchword.getText().equals("")) {
                    for (Word i : controllersearch.getMd().getDc().getWords()) {
                        if (check(searchword.getText(), i.getWord_target())) {
                            searchList.add(i.getWord_target());
                        }
                    }
                    listword.setItems(searchList);
                }
            }

        });
    }

    public void getsearchword(MouseEvent mouseEvent) {
        String text = listword.getSelectionModel().getSelectedItems().toString();
        text = text.substring(1, text.length() - 1);
        searchword.setText(text);
        searchedword.setText("");
        mainingword.getEngine().loadContent("");
        maining = "";
        if (text != "") display();
    }

    public void handlesave(ActionEvent actionEvent) throws IOException {
        if (check) {
            check = false;
            savebutton.setVisible(check);
            mainingword1.setVisible(false);
            mainingword.getEngine().loadContent(mainingword1.getHtmlText());
            String newMeaning = mainingword1.getHtmlText().replace(" dir=\"ltr\"", "");
            controllersearch.getMd().dictionaryUpdate(searchword.getText(), newMeaning);
            controllersearch.getMd().dictionaryExportToFile();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Lưu thành công! ");
            alert.showAndWait();
        }
    }

    public void handlefavor(ActionEvent actionEvent) throws IOException {
        if(!searchword.getText().equals("") && !maining.equals("") && favor) {
            save.getStyleClass().add("change-image");
            favor = false;
            String word_target = searchword.getText();
            String word_explain = maining;
            Word s = new Word(word_target, word_explain);
            controllerfavor.getMd().getDc().words.add(s);
            controllerfavor.getMd().dictionaryExportToFileFavor();
        }
        else if(!favor) {
            save.getStyleClass().removeAll("change-image");
            favor = true;
            controllerfavor.getMd().dictionaryEraser(searchword.getText());
            controllerfavor.getMd().dictionaryExportToFileFavor();
        }
    }

    public void handlechange(ActionEvent actionEvent) {

        if (!ok) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Không tồn tại từ trong từ điển hoặc bạn chưa nhập từ");
            alert.showAndWait();
        } else if (!check && maining != "") {
            check = true;
            savebutton.setVisible(check);
            mainingword1.setVisible(true);
            mainingword1.setHtmlText(maining);

        }
    }

    public void handleerase(ActionEvent actionEvent) throws IOException {
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();
        if (alert.getResult() == yes) {
            controllersearch.getMd().dictionaryEraser(searchword.getText());
            controllersearch.getMd().dictionaryExportToFile();
            searchword.setText("");
            searchedword.setText("");
            maining = "";
            mainingword.getEngine().loadContent("");
            listword.getItems().clear();
        }
    }


    public void handlespeak(MouseEvent mouseEvent) {
        ObservableList<String> items = listword.getItems();
        boolean tadashii = false;
        for (String i : items) {
            if (i.equals(searchword.getText())) {
                tadashii = true;
                break;
            }
        }
        if (!searchword.getText().equals("") && tadashii == true && geted == true) {
            voice.speak(searchword.getText());
        }
    }

    public void display() {
        if (searchword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ cần tra");
            alert.showAndWait();
        } else {
            geted = true;
            for (Word i : controllersearch.getMd().getDc().getWords()) {
                if (i.getWord_target().equals(searchword.getText())) {
                    maining = i.getWord_explain();
                    mainingword.getEngine().loadContent(i.getWord_explain(), "text/html");
                    ok = true;
                    break;
                }
            }
            for(Word i : controllerfavor.getMd().getDc().getWords()) {
                if(i.getWord_target().equals(searchword.getText())) {
                    save.getStyleClass().add("change-image");
                }
            }
            ObservableList<String> items = listword.getItems();
            boolean tadashii = false;
            for (String i : items) {
                if (i.equals(searchword.getText())) {
                    tadashii = true;
                    break;
                }
            }
            if (!searchword.getText().equals("") && ok == true && tadashii == true) {
                after = searchword.getText();
                searchedword.setText("Nghĩa của từ " + searchword.getText() + " là : ");
            }
            if (!ok || !tadashii) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Không tồn tại từ trong từ điển");
                alert.showAndWait();
            }
        }
    }
}
