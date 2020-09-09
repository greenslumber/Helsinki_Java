package ticTacToe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class App {
    public static void main (String [] args){
        Application.launch(TicTacToeApplication.class);
    }
}

public class TicTacToeApplication extends Application {
    public static void main(String[] args) {
        App.main(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Player player = new Player(Mark.X);

        //borderpane styling
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Setup
        Label turnInfo = new Label("Turn: X");
        turnInfo.setFont(Font.font("Monospaced", 20));

        //Gridpane game styling and creation
        GridPane gameBoard = new GridPane();
        gameBoard.setHgap(10);
        gameBoard.setVgap(10);
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setPrefSize(150, 150);
        gameBoard.setPadding(new Insets(5, 5, 5, 5));

        //button creation with event handling logic
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Button button = createButton();
                gameBoard.add(button, x, y);
                button.setOnAction((event) -> {
                    if (player.isWin() == false) {

                        if (button.getText().equals("")){
                            button.setText(player.getMark());

                            if (checkIfWin(gameBoard, player) == true) {
                                turnInfo.setText("The end!");
                                player.setWin();
                            } else {
                                player.switchPlayer();
                                turnInfo.setText("Turn: " + player.getMark());
                            }
                        }
                    }
                });
            }
        }

        layout.setTop(turnInfo);
        layout.setCenter(gameBoard);

        Scene view = new Scene(layout);
        stage.setScene(view);
        stage.show();
    }

    private Button createButton(){
        Button button = new Button("");
        button.setFont(Font.font("Monospaced", 20));
        button.setPrefSize(50,50);
        return button;
    }

    private String getButtonText (GridPane board, int x, int y){
        return ((Button) board.getChildren().get(x * 3 + y)).getText();
    }

    private boolean checkIfWin (GridPane board, Player player){

        //check rows, columns, diagonals
        if (checkRows(board, player)){
            return true;
        } else if (checkColumns(board, player)){
             return true;
        } else if(checkDiagonals(board, player)){
            return true;
        } // else
        return false;
    }

    private boolean checkRows (GridPane board, Player player){

        for (int y =0; y<3; y++){
            int count = 0;
            for (int x = 0; x <3; x++){
                if (getButtonText(board, x, y).equals(player.getMark())){
                    count++;
                }
                if (count == 3){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns (GridPane board, Player player) {
        for (int x = 0; x < 3; x++) {
            int count = 0;
            for (int y = 0; y < 3; y++) {
                if (getButtonText(board, x, y).equals(player.getMark())) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals (GridPane board, Player player){

        int countLR = 0;
        int countRL = 0;

        //left to right
        for (int i = 0; i < 3; i++) {
            if (getButtonText(board, i, i).equals(player.getMark())) {
                countLR++;
            }
        }

        //right to left
        for (int y = 2; y >=0 ; y--)
            if (getButtonText(board, y, 2-y).equals(player.getMark())) {
                countRL++;
            }

        if (countLR == 3 || countRL == 3){
            return true;
        }

        return false;
    }


}
