package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.*;

import java.io.IOException;

public class CollectionMenu {
    public HBox heroes;
    public HBox items;
    public HBox minions;
    PrimaryStage primaryStage = PrimaryStage.getInstance();


    public void show(MouseEvent mouseEvent) {
        for (Hero hero : Duelyst.currentAccount.getAccountHeroes()) {
            heroes.getChildren().add(hero.makeCard());
        }
        for (Minion minion : Duelyst.currentAccount.getAccountMinions()) {
            minions.getChildren().add(minion.makeCard());
        }
        for (SpellCard spellCard : Duelyst.currentAccount.getAccountSpellCards()) {
            minions.getChildren().add(spellCard.makeCard());
        }
        for (Item item : Duelyst.currentAccount.getAccountItems()) {
            items.getChildren().add(item.makeCard());
        }
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
