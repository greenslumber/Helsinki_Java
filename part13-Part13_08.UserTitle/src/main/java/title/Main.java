package title;

import javafx.application.Application;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Title");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        Application.launch(UserTitle.class, "--title=" + input);
    }
}