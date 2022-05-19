package javahex.hex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.GRAY;
import static javahex.hex.Hex.*;
class Bombs {
    final static int AMOUNT = 5;
    static int[][] bombs = new int[AMOUNT][2];
    Bombs(){
        for (int i = 0; i < AMOUNT; i++){
            int ran = (int) (Math.random() * 121);
            bombs[i][0] = (ran / 11) + 1;
            bombs[i][1] = (ran % 11) + 1;
        }
        //debug用，可看炸彈位置
        //for (int i = 0; i < AMOUNT; i++){
        //    System.out.printf("%d, %d\n", bombs[i][0], bombs[i][1]);}
    }
    public static void checkBomb(int x, int y){
        for (int i =0; i < AMOUNT; i++){
            if (x == bombs[i][0] && y == bombs[i][1]){
                bombs[i][0] = -1;
                bombs[i][1] = -1;
                exploding(x, y);
            }
        }
    }
    public static void exploding(int x, int y){
        for (int i = 0; i < 6; i++) {
            int A = (i == 0 || i == 1) ? -1 : (i == 2 || i == 5) ? 0 : 1;
            int B = (i == 4 || i == 5) ? -1 : (i == 0 || i == 3) ? 0 : 1;
            if (x+A > 0 && y+B > 0 && x+A < BOARD_SIZE+1 && y+B < BOARD_SIZE+1){
                keyBackground[x+A][y+B].setFill(GRAY);
                if (buttons[x+A][y+B].isDisable()){
                    buttons[x+A][y+B].setDisable(false);
                }
            }
        }
    }
}
