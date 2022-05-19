package javahex.hex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;
import java.util.Stack;

public class Hex {
    static final int KEY_SIZE = 10, BOARD_SIZE = 11;
    static Group group;
    static Scene scene;
    static MyButton[][] buttons = new MyButton[BOARD_SIZE + 2][BOARD_SIZE + 2];
    static int[][] board = new int[BOARD_SIZE + 2][BOARD_SIZE + 2];
    static Button swapButton;
    static int round = 0;
    static ArrayList<int[]> records = new ArrayList<>();
    static Polygon[][] keyBackground = new Polygon[BOARD_SIZE + 2][BOARD_SIZE + 2];
    //此變數之後可能要置於主畫面方便使用者開關
    static boolean bombMode = true;

    // 按下空白才開始畫圖
    @FXML
    public void startHex(){
        if (round == 0) {
            if(bombMode){new Bombs();}
            group = new Group();
            for (int i = 0; i < BOARD_SIZE + 2; i++) {
                HBox hBox;
                //每一列是一個hBox
                hBox = new HBox();

                for (int j = 0; j < BOARD_SIZE + 2; j++) {
                    //填入邊界六邊形
                    StackPane stack = new StackPane();
                    boolean hasAction = false;
                    if (i == 0 && j == 0 || i == BOARD_SIZE + 1 && j == BOARD_SIZE + 1) {
                        keyBackground[i][j] = new Hexagon("white");
                    } else if (j == 0 || j == BOARD_SIZE + 1) {
                        keyBackground[i][j] = new Hexagon("blue");
                    } else if (i == 0 || i == BOARD_SIZE + 1) {
                        keyBackground[i][j] = new Hexagon("red");
                    } else {
                        keyBackground[i][j] = new Hexagon("gray");
                        hasAction = true;
                    }
                    buttons[i][j] = new MyButton(keyBackground[i][j]);
                    if (hasAction) buttons[i][j].enableOnClick(i, j);

                    stack.getChildren().addAll(keyBackground[i][j], buttons[i][j]);
                    hBox.getChildren().add(stack);
                }
                hBox.relocate(Math.pow(3.0, 1/2.0) / 2 * KEY_SIZE * i, 3.0 / 2 * KEY_SIZE * i);
                group.getChildren().add(hBox);
            }
            scene = new Scene(group,3, 3);
            App.stage.setScene(scene);
        }

        swapButton = new Button("SWAP");
        swapButton.relocate(BOARD_SIZE*KEY_SIZE*1.5, BOARD_SIZE*KEY_SIZE*1.9);
        swapButton.setOnAction((ActionEvent e) -> {
            System.out.println("swapped");
            int[] r = records.get(records.size() - 1);
            keyBackground[r[0]][r[1]].setFill(Color.LIGHTGRAY);
            keyBackground[r[1]][r[0]].setFill(Color.BLUE);
            buttons[r[0]][r[1]].enableOnClick(r[0], r[1]);
            buttons[r[1]][r[0]].setOnAction(null);
            swapButton.setDisable(true);
            round++;
        });
        swapButton.setDisable(true);
        group.getChildren().add(swapButton);

        //投降按鈕
        Button concedeButton = new Button("CONCEDE");
        concedeButton.relocate(BOARD_SIZE*KEY_SIZE*1.5, BOARD_SIZE*KEY_SIZE*2.1);
        concedeButton.setOnAction((ActionEvent e) -> {
            switch (round % 2) {
                case 0 -> System.out.println("Red concede\nBlue Win");
                case 1 -> System.out.println("Blue concede\nRed Win");
            }
            reinitialize();
            startHex(); // 可能有問題
        });
        group.getChildren().add(concedeButton);
        App.stage.show();
    }

    public static void reinitialize(){
        buttons = new MyButton[BOARD_SIZE + 2][BOARD_SIZE + 2];
        keyBackground = new Polygon[BOARD_SIZE + 2][BOARD_SIZE + 2];
        round = 0;
        records = new ArrayList<>();
    }

    public static boolean winner(int n) {
        boolean[][] visited = new boolean[BOARD_SIZE + 2][BOARD_SIZE + 2];
        Stack<int[]> stack;
        stack = new Stack<>();
        int x, y, t;
        // 將n的處理一般化
        t = (n==0)?1:-1;
        for (int z = 1; z <= BOARD_SIZE; z++) {
            x = (n==0)?1:z;
            y = (n==0)?z:1;
            if (board[x][y] == t) {
                visited[x][y] = true;
                stack.push(new int[]{x, y});
            }
        }
        if (stack.empty()) return false;
        int[] curr = new int[]{stack.peek()[0], stack.peek()[1]};
        // check the presence of neighbors and DFS
        do {
            // return true if having reached the bottom row
            if (curr[n] == BOARD_SIZE) return true; // 0, 1替換成n
            boolean hasNeighbor = false;

            for (int i = 0; i < 6; i++) {
                int A = (i == 0 || i == 1) ? -1 : (i == 2 || i == 5) ? 0 : 1;
                int B = (i == 4 || i == 5) ? -1 : (i == 0 || i == 3) ? 0 : 1;
                if (!visited[curr[0] + A][curr[1] + B] && board[curr[0] + A][curr[1] + B] == t) {
                    hasNeighbor = true;
                    visited[curr[0] + A][curr[1] + B] = true;
                    stack.push(new int[]{curr[0] + A, curr[1] + B});
                    break;
                }
            }
            if (!hasNeighbor) {
                if (stack.size() <= 1) return false;
                stack.pop();
            }
            curr = new int[]{stack.peek()[0], stack.peek()[1]};
        } while (!stack.empty());
        return false;
    }
}
