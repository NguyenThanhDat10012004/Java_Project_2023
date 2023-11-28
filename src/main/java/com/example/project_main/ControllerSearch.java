package com.example.project_main;

import com.example.project_main.cmd.Word;
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
    //check
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

            //check
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                searchList.clear();
                if (after.length() != searchword.getText().trim().length()) {
                    searchedword.setText("");
                    mainingword.getEngine().loadContent("");
                    maining = "";
                }
                if (!searchword.getText().isEmpty()) {
                    searchList.addAll(controllersearch.getMd().getDc().startWith_W(searchword.getText().trim()));
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
        if (!text.isEmpty()) display();
    }

    public void handlesave(ActionEvent actionEvent) throws IOException {
        if (check) {
            check = false;
            savebutton.setVisible(check);
            mainingword1.setVisible(false);
            mainingword.getEngine().loadContent(mainingword1.getHtmlText());
            String newMeaning = mainingword1.getHtmlText().replace(" dir=\"ltr\"", "");
            controllersearch.getMd().dictionaryUpdate(searchword.getText().trim(), newMeaning);
            controllersearch.getMd().dictionaryExportToFile();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Lưu thành công! ");
            alert.showAndWait();
        }
    }

    public void handlefavor(ActionEvent actionEvent) throws IOException {
        if(!searchword.getText().isEmpty() && !maining.isEmpty() && favor) {
            save.getStyleClass().add("change-image");
            favor = false;
            String word_target = searchword.getText().trim();
            String word_explain = maining;
            controllerfavor.getMd().getDc().insert(word_target, word_explain);
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
        } else if (!check && !maining.isEmpty()) {
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
        if (!searchword.getText().isEmpty() && tadashii && geted) {
            voice.speak(searchword.getText());
        }
    }

    public void display() {
        if (searchword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ cần tra");
            alert.showAndWait();
        } else {
            geted = true;
            //check
            String meaning = controllersearch.getMd().getDc().getWordMeaning(searchword.getText());
            if (!meaning.isEmpty()) {
                maining = meaning;
                mainingword.getEngine().loadContent(meaning, "text/html");
                ok = true;

            }
            //check
            if (controllerfavor.getMd().getDc().search(searchword.getText())) {
                save.getStyleClass().add("change-image");
            }
            //check
            ObservableList<String> items = listword.getItems();
            boolean tadashii = false;
            //check
            for (String i : items) {
                if (i.equals(searchword.getText())) {
                    tadashii = true;
                    break;
                }
            }
            //check
            if (!searchword.getText().isEmpty() && ok && tadashii) {
                after = searchword.getText().trim();
                searchedword.setText("Nghĩa của từ " + searchword.getText().trim() + " là : ");
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
