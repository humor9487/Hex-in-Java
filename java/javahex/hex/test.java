package javahex.hex;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class test extends Application {

    private Line line;

    public static void main(String[] args) {
        Application.launch(args);
    }


    private Scene getScene() {
        Group group = new Group();

        //layout position is x:0 and y:0
        //painting starts at x:10 and y:10
        line = new Line(10, 10, 60, 10);
        //x position for layout
        line.setLayoutX(100);
        //y position for layout
        line.setLayoutY(100);

        group.getChildren().add(line);

        Scene scene = new Scene(group, 640, 480);

        return scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(getScene());
        stage.show();
        System.out.println("x: " + line.getLayoutX() + ", y: " + line.getLayoutY());
        System.out.println("start x: " + line.getStartX() + ", start y: " + line.getStartY());
    }
}