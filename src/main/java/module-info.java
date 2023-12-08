module com.example.sdacorp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.sdacorp to javafx.fxml;
    exports com.example.sdacorp;
}