module com.example.project_main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires freetts;
    requires java.net.http;

    opens com.example.project_main to javafx.fxml;
    exports com.example.project_main;
    exports com.example.project_main.MemoryGame;
    opens com.example.project_main.MemoryGame to javafx.fxml;
    exports com.example.project_main.cmd;
    opens com.example.project_main.cmd to javafx.fxml;
    exports com.example.project_main.SpaceWar;
    opens com.example.project_main.SpaceWar to javafx.fxml;
}