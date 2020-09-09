package asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class App {
    public static void main(String[] args) {
        Application.launch(AsteroidsApplication.class);
    }
}

public class AsteroidsApplication extends Application {

    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    public static void main(String[] args) {
        App.main(args);
    }

    public static int partsCompleted() {
        // State how many parts you have completed using the return value of this method
        return 4;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //creating the game window
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.setStyle("-fx-background-color: #8b0000");

        //points label
        Text text = new Text(10,20,"Points: 0");
        pane.getChildren().add(text);
        AtomicInteger points = new AtomicInteger();

        //creating the ship and asteroids
        Ship ship = new Ship(WIDTH/2, HEIGHT/2);

        List <Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i<5 ; i++){
            Random random = new Random();
            Asteroid asteroid = new Asteroid(random.nextInt(WIDTH/3), random.nextInt(HEIGHT));
            asteroid.setAcceleration(5);
            asteroid.accelerate();
            asteroids.add(asteroid);
        }

        List <Projectile> projectiles = new ArrayList<>();
        AudioClip gunFire = new AudioClip("file:laser.mp3");
        AudioClip explosion = new AudioClip("file:explosion.mp3");


        //adding objects to game window
        pane.getChildren().add(ship.getCharacter());
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

        //displaying the scene
        Scene scene = new Scene (pane);

        //functionality to register continued pressing of key inputs
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

        scene.setOnKeyPressed(event ->{
           pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        //implement the action - main game logic
        new AnimationTimer(){
            @Override
            public void handle(long l) {
                //handling the ship
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                //handling the movement
                if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }

                //projectiles
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3) {
                    Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    gunFire.play();

                    projectile.setAcceleration(3);
                    projectile.accelerate();

                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    pane.getChildren().add(projectile.getCharacter());
                }

                //initiate movement
                ship.move();
                projectiles.forEach(projectile -> projectile.move());

                //asteroid movement
                asteroids.forEach(asteroid -> asteroid.move());
                asteroids.forEach(asteroid -> {
                    if (ship.collide(asteroid)) {
                        stop();
                    }
                });

                projectiles.forEach(projectile -> {
                    asteroids.forEach(asteroid -> {
                        if (projectile.collide(asteroid)) {
                            projectile.setAlive(false);
                            asteroid.setAlive(false);

                        }
                    });
                    if (!projectile.isAlive()){
                        text.setText("Points: " + points.addAndGet(1000));
                        explosion.play();
                    }
                });

                projectiles.stream()
                        .filter(projectile -> !projectile.isAlive())
                        .forEach(projectile -> pane.getChildren().remove(projectile.getCharacter()));

                projectiles.removeAll(projectiles.stream()
                    .filter(projectile -> !projectile.isAlive())
                        .collect(Collectors.toList()));

                asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .forEach(asteroid -> pane.getChildren().remove(asteroid.getCharacter()));

                asteroids.removeAll(asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .collect(Collectors.toList()));

                if (Math.random()< 0.005){
                    Asteroid asteroid = new Asteroid(WIDTH, HEIGHT);
                    if (!asteroid.collide(ship)){
                        asteroids.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }
            }
        }.start();

        stage.setScene(scene);
        stage.setTitle("Asteroids!");
        stage.show();
    }
}