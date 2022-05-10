package javahex.hex;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import static javahex.hex.Hex.KEY_SIZE;

public class Hexagon extends Polygon {
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
        switch (newColor) {
            case "gray" -> this.setFill(Color.LIGHTGRAY);
            case "blue" -> this.setFill(Color.BLUE);
            case "red" -> this.setFill(Color.RED);
            default -> this.setFill(Color.WHITE);
        }
    }
}
