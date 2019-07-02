package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ShopMenu {
    public HBox heroes;
    public HBox items;
    public HBox minions;
    public TextField search;
    PrimaryStage primaryStage = PrimaryStage.getInstance();
    ShopMethods shopMethods = Duelyst.currentAccount.shopMethods;

    public void initialize() {
        for (Hero hero : Duelyst.getAllHeroes()) {
            heroes.getChildren().add(hero.makeCard());
        }
        for (Minion minion : Duelyst.getAllMinions()) {
            minions.getChildren().add(minion.makeCard());
        }
        for (SpellCard spellCard : Duelyst.getAllSpellCards()) {
            minions.getChildren().add(spellCard.makeCard());
        }
        for (Item item : Duelyst.getAllItems()) {
            items.getChildren().add(item.makeCard());
        }

    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void searchShop(MouseEvent mouseEvent) {
        shopMethods.searchShop(search.getText());
    }

    public void searchCollection(MouseEvent mouseEvent) {
        shopMethods.searchCollection(search.getText());
    }

    public void buy(MouseEvent mouseEvent) throws FileNotFoundException {
        shopMethods.buyCard(search.getText());
    }

    public void sell(MouseEvent mouseEvent) {
        shopMethods.sellCard(search.getText());
    }

    public void showCollection(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
