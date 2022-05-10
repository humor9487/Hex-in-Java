package javahex.hex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    static Stage stage;
    static Scene menuScene, hexScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("hex.fxml"));

        stage = primaryStage;
        menuScene = new Scene(fxmlLoader.load());
        hexScene = new Scene(fxmlLoader2.load());
        stage.setTitle("HEX");
        stage.setScene(menuScene);
        stage.setWidth(370);
        stage.setHeight(300);
        stage.show();
    }
    public static void main(String[] args) {launch();}
}
