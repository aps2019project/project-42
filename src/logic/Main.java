package logic;

import graphic.AccountPageMenu;
import graphic.Battle;
import graphic.PrimaryStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    static Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryStage.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        //String musicFile = "resources/music/mainmenu.mp3";
        /*Media sound = new Media(new File("resources/music/mainmenu.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();*/
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Duelyst duelyst = new Duelyst();
        duelyst.preStart();
        launch(args);
    }
}
