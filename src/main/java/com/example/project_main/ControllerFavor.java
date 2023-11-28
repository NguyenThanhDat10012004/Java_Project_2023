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

public class ControllerFavor extends ControllerMain implements Initializable {

    protected final ObservableList<String> searchList = FXCollections.observableArrayList();
    protected final ObservableList<String> FavorList = FXCollections.observableArrayList();
    @FXML
    private TextField searchword;
    @FXML
    private Label searchedword;
    @FXML
    private WebView mainingword;
    @FXML
    private Button savebutton;
    @FXML
    private HTMLEditor mainingword1;
    @FXML
    public ListView listwordFavor;
    @FXML
    private AnchorPane PaneFavor;
    Boolean check = false;
    String maining;
    Boolean ok = false;
    Boolean geted = false;
    Boolean favor = false;
    String after = "";

    public void getwordsearch(ActionEvent actionEvent) {
        display();
    }

    public void setLabel(MouseEvent mouseEvent) {
        searchedword.setText("");
        mainingword.getEngine().loadContent("");
        geted = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PaneFavor.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.ENTER) {
                    display();
                }
            }
        });
        searchword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                listwordFavor.requestFocus();
                int selectedIndex = listwordFavor.getSelectionModel().getSelectedIndex();
                if (selectedIndex < listwordFavor.getItems().size() - 1) {
                    listwordFavor.getSelectionModel().select(selectedIndex + 1);
                }
                event.consume();
            } else if (event.getCode() == KeyCode.UP) {
                listwordFavor.requestFocus();
                int selectedIndex = listwordFavor.getSelectionModel().getSelectedIndex();
                if (selectedIndex > 0) {
                    listwordFavor.getSelectionModel().select(selectedIndex - 1);
                }
                event.consume();
            }
        });
        listwordFavor.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selectedItem = (String) listwordFavor.getSelectionModel().getSelectedItem();
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
                if (searchword.getText().isEmpty()) {
                    searchList.addAll(controllerfavor.getMd().getDc().startWith_W(""));
                    listwordFavor.setItems(searchList);
                }
                if (after.length() != searchword.getText().length()) {
                    searchedword.setText("");
                    mainingword.getEngine().loadContent("");
                    maining = "";
                }
                if (!searchword.getText().isEmpty()) {
                    searchList.addAll(controllerfavor.getMd().getDc().startWith_W(searchword.getText()));
                    listwordFavor.setItems(searchList);
                }
            }
        });
    }

    public void getsearchword(MouseEvent mouseEvent) {
        if(!favor) {
            FavorList.addAll(controllerfavor.getMd().getDc().startWith_W(""));
            listwordFavor.setItems(FavorList);
            favor = true;
        }
        String text = listwordFavor.getSelectionModel().getSelectedItems().toString();
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
            controllersearch.getMd().dictionaryUpdate(searchword.getText(), newMeaning);
            controllersearch.getMd().dictionaryExportToFile();
            controllerfavor.getMd().dictionaryUpdate(searchword.getText(), newMeaning);
            controllerfavor.getMd().dictionaryExportToFileFavor();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Lưu thành công! ");
            alert.showAndWait();
        }
    }

    public void handlechange(ActionEvent actionEvent) {
        if (!ok) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Không tồn tại từ trong your favor hoặc bạn chưa nhập từ");
            alert.showAndWait();
        } else if (!check && !maining.isEmpty()) {
            check = true;
            savebutton.setVisible(check);
            System.out.println("set savebutton visible");
            mainingword1.setVisible(true);
            mainingword1.setHtmlText(maining);
        } else {
            System.out.println("where the handle do");
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
            controllerfavor.getMd().dictionaryEraser(searchword.getText());
            controllerfavor.getMd().dictionaryExportToFileFavor();
            searchword.setText("");
            searchedword.setText("");
            mainingword.getEngine().loadContent("");
            maining = "";
            listwordFavor.getItems().clear();
        }
    }


    public void handlespeak(MouseEvent mouseEvent) {
        ObservableList<String> items = listwordFavor.getItems();
        boolean tadashii = false;
        //check
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
            String meaning = controllerfavor.getMd().getDc().getWordMeaning(searchword.getText());
            if (!meaning.isEmpty()) {
                maining = meaning;
                mainingword.getEngine().loadContent(meaning, "text/html");
                ok = true;
            }

            ObservableList<String> items = listwordFavor.getItems();
            boolean tadashii = false;
            for (String i : items) {
                if (i.equals(searchword.getText())) {
                    tadashii = true;
                    break;
                }
            }
            if (!searchword.getText().isEmpty() && ok && tadashii) {
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

    public TextField getSearchword() {
        return searchword;
    }
}
