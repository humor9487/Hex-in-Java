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
import java.util.Objects;


public class App extends Application {
    static final int KEY_SIZE = 10;
    static final int BOARD_SIZE = 11;
    static Stage stage;
    static MyButton buttons[][] = new MyButton[BOARD_SIZE + 2][BOARD_SIZE + 3];
    static String gridColor[][] = new String[BOARD_SIZE + 2][BOARD_SIZE + 3];
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
            gridColor[i][j] = "blue";
            draw();
        }

    }
    public class Hexagon extends Polygon{
        Hexagon(int keySize, String newColor){
            Double[] points = new Double[12];
            for (int q = 0; q < 6; q++){
                double x = Math.cos(Math.PI/3.0*q+Math.PI/6) * keySize;
                double y = Math.sin(Math.PI/3.0*q+Math.PI/6) * keySize;
                points[q*2] = x ;
                points[q*2+1] = y ;
            }
            this.getPoints().addAll(points);
            if (newColor == "gray") {
                this.setFill(Color.LIGHTGRAY);
            }
            else if (newColor == "blue"){
                this.setFill(Color.BLUE);
            }
            else if (newColor == "red"){
                this.setFill(Color.RED);
            }
            else{
                this.setFill(Color.WHITE);
            }

        }
    }
    public void draw(){
        Group g = new Group();
        for(int i = 0; i < BOARD_SIZE + 2; i++){
            HBox hBox;
            hBox = new HBox();
            for(int j = 0; j < BOARD_SIZE + 3; j++) {
                if (i==0&&j==0||i==BOARD_SIZE+1&&j==BOARD_SIZE+1||j==BOARD_SIZE+2){
                    gridColor[i][j] = "white";
                }
                else if (j==0||j==BOARD_SIZE+1){
                    gridColor[i][j] = "blue";
                }
                else if(i==0||i==BOARD_SIZE+1){
                    gridColor[i][j] = "red";
                }
                int finalI = i;
                int finalJ = j;
                Polygon keyBackground;
                StackPane stack = new StackPane();
                if (gridColor[i][j] == null){
                    keyBackground = new Hexagon(KEY_SIZE, "gray");
                    buttons[i][j] = new MyButton(finalI, finalJ, keyBackground, "normalButton.css");
                    stack.getChildren().addAll(keyBackground, buttons[i][j]);
                }
                else if (gridColor[i][j] == "blue"){
                    keyBackground = new Hexagon(KEY_SIZE, "blue");
                    stack.getChildren().add(keyBackground);
                }
                else if (gridColor[i][j] == "red"){
                    keyBackground = new Hexagon(KEY_SIZE, "red");
                    stack.getChildren().add(keyBackground);
                }
                else{
                    keyBackground = new Hexagon(KEY_SIZE, "white");
                    buttons[i][j] = new MyButton(finalI, finalJ, keyBackground, "normalButton.css");
                    stack.getChildren().addAll(keyBackground, buttons[i][j]);
                }
                hBox.getChildren().add(stack);
            }
            hBox.relocate(Math.pow(3.0, 1/2)/2* KEY_SIZE *i+i*4, 3.0/2* KEY_SIZE *i);
            g.getChildren().add(hBox);
        }
        Button swapButton = new Button("SWAP");
        swapButton.relocate(170, 210);
        g.getChildren().add(swapButton);
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
