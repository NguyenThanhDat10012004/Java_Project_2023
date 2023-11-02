module com.example.project_main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires freetts;





    opens com.example.project_main to javafx.fxml;
    exports com.example.project_main;
}