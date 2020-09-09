package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



// END SOLUTION
public class VocabularyPracticeApplication extends Application {


    private Dictionary dictionary;

    public static void main(String[] args) {
        App.main(args);
    }

    @Override
    public void init () throws Exception {
        this.dictionary = new Dictionary();
    }

    @Override
    public void start(Stage stage) throws Exception {

        PracticeView pv = new PracticeView(dictionary);
        InputView iv = new InputView(dictionary);

        BorderPane layout = new BorderPane();

        HBox menu = new HBox();
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setSpacing(10);

        Button enter = new Button("Enter new words");
        Button practice = new Button ("Practice");

        menu.getChildren().addAll(enter, practice);
        layout.setTop(menu);

        enter.setOnAction((event) -> {
            layout.setCenter(iv.getView());
        });

        practice.setOnAction((event) ->{
            layout.setCenter(pv.getView());
        });

        Scene view = new Scene (layout, 300, 300);
        stage.setScene(view);
        stage.show();
    }
}

class App {
    //Hack to make it work in IntelliJ
    public static void main(String[] args) {
        Application.launch(VocabularyPracticeApplication.class);
    }
}