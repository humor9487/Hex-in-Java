package javahex.hex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static javahex.hex.Hex.bombMode;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Menu{

    @FXML
    public void startBtnClick(){
        App.hexScene.getRoot().requestFocus();
        App.stage.setScene(App.hexScene);
    }
    @FXML
    public void tutorial() throws URISyntaxException, IOException {

        Desktop desktop = Desktop.getDesktop();
        URI uri = new URI("https://zh.boardgamearena.com/tutorial?game=hex");
        desktop.browse(uri);
    }

    @FXML
    private Label mymode;
    @FXML
    private RadioButton mode1,mode2,mode3;
    @FXML
    public void modeonAction(ActionEvent event){
        if (mode1.isSelected()){
            mymode.setText("一般模式");
            bombMode = false;
        }
        else if(mode2.isSelected()){
            mymode.setText("炸彈模式");
            bombMode = true;

        }
        else if(mode3.isSelected()){
            mymode.setText("其它模式");
        }
    }

    @FXML
    public void voiceonAction(){

    }
    @FXML
    public void exitBtnClick(){
        App.stage.close();
    }
}

