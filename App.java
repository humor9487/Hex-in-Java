package javahex.hex;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    private void onclick(int i, int j){
        System.out.print(i);
        System.out.print(j);
        System.out.println("clicked");
        return;
    }
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("HEX");
        final int boardSize = 11;
        Button buttons[][] = new Button[boardSize+2][boardSize+2];

        final int keySize = 10;
        Double[] points = new Double[12];
        for (int q = 0; q < 6; q++){
            double x = Math.cos(Math.PI/3.0*q+Math.PI/6) * keySize;
            double y = Math.sin(Math.PI/3.0*q+Math.PI/6) * keySize;
            points[q*2] = x ;
            points[q*2+1] = y ;
        }
        Group g = new Group();

        for(int i = 0; i < boardSize+2; i++){
            HBox hBox;
            hBox = new HBox();
            for(int j = 0; j < boardSize+2; j++) {
                buttons[i][j] = new Button();
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction((ActionEvent e) -> {
                    onclick(finalI, finalJ);
                });
                buttons[i][j].getStylesheets().add(getClass().getResource("buttonstyle.css").toExternalForm());
                Polygon keyBackground = new Polygon();
                //points

                keyBackground.getPoints().addAll(points);
                keyBackground.setFill(Color.LIGHTGRAY);
                buttons[i][j].setShape(keyBackground);
                StackPane stack = new StackPane(keyBackground, buttons[i][j]);
                hBox.getChildren().add(stack);
            }
            hBox.relocate(Math.pow(3.0, 1/2)/2*keySize*i+i*4, 3.0/2*keySize*i);
            //hBox.setLayoutX(Math.pow(3.0, 1/2)/2*keySize*i);
            //hBox.setLayoutY(3.0/2*keySize*i);
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
