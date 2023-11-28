package com.example.project_main;


import com.example.project_main.cmd.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerInsert extends ControllerMain implements Initializable {
    @FXML
    private TextField addText;
    @FXML
    private HTMLEditor addEditor;
    @FXML
    private AnchorPane PaneInsert;

    Boolean correct = true;

    public void ClickArrow(ActionEvent actionEvent) {
        ClickArrow();
    }

    public void reset(ActionEvent actionEvent) {
        addEditor.setHtmlText("<html>" + addText.getText() + " /" + addText.getText() + "/"
                + "<ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.ENTER) {
                    ClickArrow();
                }
            }
        });
    }
    public void ClickArrow() {
        if (addText.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ muốn thêm");
            alert.showAndWait();
            return;
        }
        boolean check = !controllersearch.getMd().getDc().search(addText.getText());

        if(check) {
            addEditor.setHtmlText("<html>" + addText.getText() + " /" + addText.getText() + "/"
                    + "<ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
        }
        else {
            ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Từ này đã có trong từ điển bạn có muốn chỉnh sửa không ?", yes, no);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == yes) {
                correct = false;
                String maning = controllersearch.getMd().getDc().getWordMeaning(addText.getText());
                addEditor.setHtmlText(maning);
            }
        }

    }

    public void add(ActionEvent actionEvent) throws IOException {
        String explain = addEditor.getHtmlText().replace(" dir=\"ltr\"", "");
        if(!explain.isEmpty() && correct) {
            controllersearch.getMd().dictionaryInsert(addText.getText(), explain);
            addText.setText("");
            addEditor.setHtmlText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Lưu thành công");
            alert.showAndWait();
            controllersearch.getMd().dictionaryExportToFile();

        }
        else if(!explain.isEmpty() && !correct){

            controllersearch.getMd().dictionaryUpdate(addText.getText(), explain);
            addText.setText("");
            addEditor.setHtmlText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Sửa thành công");
            alert.showAndWait();
            controllersearch.getMd().dictionaryExportToFile();
        }
    }
}
