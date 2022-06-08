module javahex.hex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens javahex.hex to javafx.fxml;
    exports javahex.hex;
}