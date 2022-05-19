package javahex.hex;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Objects;
import static javahex.hex.Hex.*;

public class MyButton extends Button {
    MyButton(Polygon keyBackground){
        //按鈕風格設定
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("normalButton.css")).toExternalForm());
        //按鈕形狀設定成六邊形
        this.setShape(keyBackground);
    }

    //觸發事件
    public void enableOnClick(int i, int j) {
        this.setOnAction((ActionEvent e) -> {
            int I = i;
            int J = j;
            Bombs.checkBomb(I, J);
            swapButton.setDisable(round != 0);
            System.out.printf("[%d, %d], %s\n", i, j, (round % 2 == 0)? "RED" : "BLUE");
            records.add(new int[]{i, j});
            switch (round % 2) {
                case 0 -> {
                    keyBackground[i][j].setFill(Color.RED);
                    board[i][j] = 1;
                    if (winner(0)) {
                        System.out.println("Red is the winner");
                    }
                }
                case 1 -> {
                    keyBackground[i][j].setFill(Color.BLUE);
                    board[i][j] = -1;
                    if (winner(1)) {
                        System.out.println("Blue is the winner");
                    }
                }
            }
            buttons[i][j].setDisable(false);
            round++;
            App.stage.show();
        });
    }
}