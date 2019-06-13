package logic;

import graphic.AccountPageMenu;
import graphic.PrimaryStage;
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
        PrimaryStage.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Duelyst duelyst = new Duelyst();
        duelyst.preStart();
        launch(args);
        //duelyst.main();
    }
}
