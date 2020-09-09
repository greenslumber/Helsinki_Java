package asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship extends Character{

    public Ship (int x, int y) {
        super (new Polygon(-5,-5,10,0,-5,5), x, y);
        Color shipPaint = Color.DARKBLUE;
        this.getCharacter().setFill(shipPaint);
    }
}
