module javahex.hex {
    requires javafx.controls;
    requires javafx.fxml;


    opens javahex.hex to javafx.fxml;
    exports javahex.hex;
}