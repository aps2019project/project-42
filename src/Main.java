import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    static Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(graphic.Sample.class.getResource("sample.fxml"));
        primaryStage.setTitle("Duelyst");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        /*Duelyst duelyst = new Duelyst();
        duelyst.main();*/
    }
}
