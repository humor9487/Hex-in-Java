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
    public class MyButton extends Button{
        MyButton(int finalI, int finalJ, Polygon keyBackground, String buttonType){
            this.setOnAction((ActionEvent e) -> {
                onclick(finalI, finalJ);
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
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("HEX");
        final int boardSize = 11;
        Button buttons[][] = new Button[boardSize+2][boardSize+2];
        final int keySize = 10;
        Group g = new Group();
        for(int i = 0; i < boardSize+2; i++){
            HBox hBox;
            hBox = new HBox();
            for(int j = 0; j < boardSize+2; j++) {
                //if (i==0&&j==0||i==keySize+1||j==keySize+1){}
                int finalI = i;
                int finalJ = j;
                Polygon keyBackground = new Hexagon(keySize);
                buttons[i][j] = new MyButton(finalI, finalJ, keyBackground, "normalButton.css");
                StackPane stack = new StackPane(keyBackground, buttons[i][j]);
                hBox.getChildren().add(stack);
            }
            hBox.relocate(Math.pow(3.0, 1/2)/2*keySize*i+i*4, 3.0/2*keySize*i);
            g.getChildren().add(hBox);
        }
        Scene scene = new Scene(g,0, 0);
        stage.setWidth(370); // 設定視窗的寬
        stage.setHeight(280); // 設定視窗的高
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {launch();}
}
