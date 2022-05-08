package javahex.hex;

import javafx.fxml.FXML;

public class Menu {
    @FXML
    public void startBtnClick(){
        App.hexScene.getRoot().requestFocus();
        App.stage.setScene(App.hexScene);
    }

    @FXML
    public void exitBtnClick(){
        App.stage.close();
    }
}
