package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainMenu {
    PrimaryStage primaryStage = PrimaryStage.getInstance();

    public void enterBattleFirstMenu(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(BattleFirstMenu.class.getResource("graphic/BattleFirstMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void enterCollection(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(CollectionMenu.class.getResource("CollectionMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void enterShop(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(ShopMenu.class.getResource("ShopMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void enterMainMenuHelp(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenuHelp.class.getResource("MainMenuHelp.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
