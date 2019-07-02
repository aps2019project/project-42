package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import logic.AccountPage;

import java.io.IOException;

public class LeaderBoardPage {
    public StackPane leaderBoard;
    PrimaryStage primaryStage = PrimaryStage.getInstance();

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
    public void initialize(){
        leaderBoard.getChildren().add(AccountPage.getInstance().showLeaderBoard());
    }
}
