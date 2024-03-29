package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class backupShip {

    private Polygon character;
    private Point2D movement;
    private double acceleration;

    public backupShip(int x, int y) {
        this.character = new Polygon(-5, -5, 10, 0, -5, 5);
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);

        this.movement = new Point2D(0,0);
        this.acceleration = 0.05;
    }
    public Polygon getCharacter() {
        return character;
    }

    public void turnLeft(){
        this.character.setRotate(this.character.getRotate()-5);
    }

    public void turnRight(){
        this.character.setRotate(this.character.getRotate()+5);
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        changeX *= this.acceleration;
        changeY *= this.acceleration;

        this.movement = this.movement.add(changeX, changeY);
    }

    public void setAcceleration (int speed) {
        this.acceleration = 1.0 * speed / 100;
    }

    public void move(){
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
    }
}
