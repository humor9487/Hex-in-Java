package javahex.hex;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    static final int KEY_SIZE = 10;
    static final int BOARD_SIZE = 11;
    static Stage stage;
    public class MyButton extends Button{
        MyButton(int i, int j, Polygon keyBackground, String buttonType){
            this.setOnAction((ActionEvent e) -> {
                onclick(i, j);
            });
            this.getStylesheets().add(getClass().getResource(buttonType).toExternalForm());
            this.setShape(keyBackground);
        }
        private void onclick(int i, int j){
            System.out.print(i);
            System.out.print(j);
            System.out.println("clicked");

            return;
        }

    }
    public class Hexagon extends Polygon{
        Hexagon(int keySize){
            Double[] points = new Double[12];
            for (int q = 0; q < 6; q++){
                double x = Math.cos(Math.PI/3.0*q+Math.PI/6) * keySize;
                double y = Math.sin(Math.PI/3.0*q+Math.PI/6) * keySize;
                points[q*2] = x ;
                points[q*2+1] = y ;
            }
            this.getPoints().addAll(points);
            this.setFill(Color.LIGHTGRAY);

        }
    }
    public void draw(){
        Button buttons[][];
        Group g;
        buttons = new Button[BOARD_SIZE +2][BOARD_SIZE +2];
        g = new Group();

        for(int i = 0; i < BOARD_SIZE +2; i++){
            HBox hBox;
            hBox = new HBox();
            for(int j = 0; j < BOARD_SIZE +2; j++) {
                //if (i==0&&j==0||i==keySize+1||j==keySize+1){}
                int finalI = i;
                int finalJ = j;
                Polygon keyBackground = new Hexagon(KEY_SIZE);
                buttons[i][j] = new MyButton(finalI, finalJ, keyBackground, "normalButton.css");
                StackPane stack = new StackPane(keyBackground, buttons[i][j]);
                hBox.getChildren().add(stack);
            }
            hBox.relocate(Math.pow(3.0, 1/2)/2* KEY_SIZE *i+i*4, 3.0/2* KEY_SIZE *i);
            g.getChildren().add(hBox);
        }
        Scene scene = new Scene(g,0, 0);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("HEX");
        stage.setWidth(370);
        stage.setHeight(280);
        draw();
    }
    public static void main(String[] args) {launch();}
}
