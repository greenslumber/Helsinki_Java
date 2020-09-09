package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InputView {

    private Dictionary dictionary;

    public InputView (Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView () {
        GridPane layout = new GridPane();

        Label wordInstruction = new Label ("Word");
        Label transInstruction = new Label ("Translation");
        TextField wordInput = new TextField();
        TextField transInput = new TextField();
        Button add = new Button ("Add translation");

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));

        layout.add(wordInstruction, 0, 0);
        layout.add(transInstruction, 0, 2);
        layout.add(wordInput, 0, 1);
        layout.add(transInput, 0, 3);
        layout.add(add, 0, 4);

        add.setOnMouseClicked((event) -> {
            dictionary.add(wordInput.getText(), transInput.getText());
            wordInput.clear();
            transInput.clear();
        });

        return layout;
    }
}
