package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PracticeView {

    private Dictionary dictionary;
    private String word;

    public PracticeView (Dictionary dictionary) {
        this.dictionary = dictionary;
        this.word = "";
    }

    public Parent getView() {
        GridPane layout = new GridPane();

        this.word = this.dictionary.getRandomWord();
        Label instruction = new Label ("Translate the word " + this.word + "'");
        TextField transInput = new TextField ();
        Button check = new Button ("Check");
        Label feedback = new Label ("");

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        layout.add(instruction, 0, 0);
        layout.add(transInput, 0, 1);
        layout.add(check, 0, 2);
        layout.add(feedback, 0, 3);

        check.setOnMouseClicked((event) -> {
            String translation = transInput.getText();
            if (this.dictionary.get(this.word).equals(translation)) {
                feedback.setText("Correct!");
            } else {
                feedback.setText("Incorrect, the correct translation for the word" + this.word + " is " + this.dictionary.get(word) + ".");
                return;
            }
            this.word = this.dictionary.getRandomWord();
            instruction.setText("Translate the word " + this.word);
            transInput.clear();
        });

        return layout;
    }
}