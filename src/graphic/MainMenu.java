package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import logic.Duelyst;

import java.io.IOException;

public class MainMenu {
    PrimaryStage primaryStage = PrimaryStage.getInstance();

    public void enterBattleFirstMenu(MouseEvent mouseEvent) throws IOException {
        //if (Duelyst.currentAccount.mainDeck!=null) {
            Parent root = FXMLLoader.load(BattleFirstMenu.class.getResource("BattleFirstMenu.fxml"));
            Image image = new Image("ui/mouse_attack@2x.png");
            root.setCursor(new ImageCursor(image));
            primaryStage.stage.setTitle("Duelyst");
            primaryStage.stage.setScene(new Scene(root));
            primaryStage.stage.setMaximized(true);
            primaryStage.stage.show();
        //}
        /*else {
            Alert noDeck=new Alert(Alert.AlertType.ERROR);
            noDeck.setContentText("You don't have main deck");
            noDeck.show();
        }*/
    }

    public void enterCollection(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
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
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void enterCostume(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(CostumeMenu.class.getResource("CostumeMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
