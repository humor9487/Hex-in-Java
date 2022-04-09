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
    //按鍵大小、棋盤大小
    static final int KEY_SIZE = 10;
    static final int BOARD_SIZE = 11;
    static Stage stage;
    static Button buttons[][] = new Button[BOARD_SIZE + 2][BOARD_SIZE + 3];
    static String gridColor[][] = new String[BOARD_SIZE + 2][BOARD_SIZE + 3];
    static int round = 0;
    static int[] pos = new int[2];
    public static void reinitialize(){
        buttons = new Button[BOARD_SIZE + 2][BOARD_SIZE + 3];
        gridColor = new String[BOARD_SIZE + 2][BOARD_SIZE + 3];
        round = 0;
        pos = new int[2];
    }
    public class MyButton extends Button{
        MyButton(int i, int j, Polygon keyBackground, String buttonType, boolean action){
            //觸發事件
            if (action) {
                this.setOnAction((ActionEvent e) -> {
                    onclick(i, j);
                });
            }
            //按鈕風格設定
            this.getStylesheets().add(getClass().getResource(buttonType).toExternalForm());
            //按鈕形狀設定成六邊形
            this.setShape(keyBackground);
        }
        private void onclick(int i, int j){
            System.out.print(i);
            System.out.print(j);
            System.out.println("clicked");
            pos[0] = i;
            pos[1] = j;
            if (round%2==1) {
                gridColor[i][j] = "blue";
            }
            else{
                gridColor[i][j] = "red";
            }
            draw();
        }

    }
    public class Hexagon extends Polygon{
        Hexagon(String newColor){
            //按鈕背景的多邊形
            Double[] points = new Double[12];
            for (int q = 0; q < 6; q++){
                //使用極座標參數式畫多邊形
                double x = Math.cos(Math.PI/3.0*q+Math.PI/6) * KEY_SIZE;
                double y = Math.sin(Math.PI/3.0*q+Math.PI/6) * KEY_SIZE;
                points[q*2] = x ;
                points[q*2+1] = y ;
            }
            this.getPoints().addAll(points);
            //填色
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
        round++;
        Group g = new Group();
        for(int i = 0; i < BOARD_SIZE + 2; i++){
            HBox hBox;
            //每一列是一個hbox
            hBox = new HBox();
            for(int j = 0; j < BOARD_SIZE + 3; j++) {
                //填入邊界六邊形
                if (i==0&&j==0||i==BOARD_SIZE+1&&j==BOARD_SIZE+1||j==BOARD_SIZE+2){
                    //在棋盤外填入白色無用按鈕確保格式
                    gridColor[i][j] = "white";
                }
                else if (j==0||j==BOARD_SIZE+1){
                    gridColor[i][j] = "blue";
                }
                else if(i==0||i==BOARD_SIZE+1){
                    gridColor[i][j] = "red";
                }
                Polygon keyBackground;
                StackPane stack = new StackPane();
                if (gridColor[i][j] == null){
                    keyBackground = new Hexagon("gray");
                    buttons[i][j] = new MyButton(i, j, keyBackground, "normalButton.css", true);
                    stack.getChildren().addAll(keyBackground, buttons[i][j]);
                }
                else if (gridColor[i][j] == "blue"){
                    keyBackground = new Hexagon("blue");
                    stack.getChildren().add(keyBackground);
                }
                else if (gridColor[i][j] == "red"){
                    keyBackground = new Hexagon("red");
                    stack.getChildren().add(keyBackground);
                }
                else{
                    keyBackground = new Hexagon("white");
                    buttons[i][j] = new MyButton(i, j, keyBackground, "transparentButton.css", false);
                    stack.getChildren().addAll(keyBackground, buttons[i][j]);
                }
                hBox.getChildren().add(stack);
            }
            hBox.relocate(Math.pow(3.0, 1/2)/2* KEY_SIZE *i+i*4, 3.0/2* KEY_SIZE *i);
            g.getChildren().add(hBox);
        }
        if (round==2){
            //交換按鈕
            Button swapButton = new Button("SWAP");
            swapButton.relocate(BOARD_SIZE*KEY_SIZE*1.5, BOARD_SIZE*KEY_SIZE*1.9);
            swapButton.setOnAction((ActionEvent e) -> {
                System.out.println("swap");
                gridColor[pos[0]][pos[1]] = null;
                gridColor[pos[1]][pos[0]] = "red";
                draw();
            });
            g.getChildren().add(swapButton);
        }
        //投降按鈕
        Button concedeButton = new Button("CONCEDE");
        concedeButton.relocate(BOARD_SIZE*KEY_SIZE*1.5, BOARD_SIZE*KEY_SIZE*2.1);
        concedeButton.setOnAction((ActionEvent e) -> {
            System.out.println("concede");
            if (round%2==0){
                System.out.println("Blue Win");
            }
            else{
                System.out.println("Red Win");
            }
            reinitialize();
            draw();
        });
        //勝負判斷
        //
        g.getChildren().add(concedeButton);

        Scene scene = new Scene(g,3, 3);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("HEX");
        stage.setWidth(370);
        stage.setHeight(300);
        draw();
    }
    public static void main(String[] args) {launch();}
}
