package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {

    private Polygon character;
    private Point2D movement;
    private double acceleration;
    private boolean isAlive;

    public Character (Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Point2D(0,0);
        this.acceleration = 0.05;
        this.isAlive = true;

    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
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

        if (this.character.getTranslateX() < 0){
            this.character.setTranslateX(this.character.getTranslateX() + AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateX() > AsteroidsApplication.WIDTH){
            this.character.setTranslateX(this.character.getTranslateX() % AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateY() < 0){
            this.character.setTranslateY(this.character.getTranslateY() + AsteroidsApplication.HEIGHT);
        }
        if (this.character.getTranslateY() > AsteroidsApplication.HEIGHT){
            this.character.setTranslateY(this.character.getTranslateY() % AsteroidsApplication.HEIGHT);
        }


    }

    public boolean collide (Character other){
        Shape collisionArea = Shape.intersect(this.character, other.getCharacter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D point2D){
        this.movement = point2D;
    }

}
