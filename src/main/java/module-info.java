module mco {
    requires javafx.controls;
    requires javafx.fxml;

    opens mco to javafx.fxml;
    exports mco;
}
