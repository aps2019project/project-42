package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.*;

import java.io.IOException;

public class CollectionMenu {
    public HBox heroes;
    public HBox items;
    public HBox minions;
    public TextField id;
    public TextField deck;
    public TextField search;
    public TextField showDeck;
    PrimaryStage primaryStage = PrimaryStage.getInstance();
    CollectionMethods collectionMethods = Duelyst.currentAccount.collectionMethods;


    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void searchCollection(MouseEvent mouseEvent) {
        collectionMethods.search(search.getText());
    }

    public void addToDeck(MouseEvent mouseEvent) {
        collectionMethods.addToDeck(Integer.parseInt(id.getText()), deck.getText());
    }

    public void removeFromDeck(MouseEvent mouseEvent) {
        collectionMethods.removeFromDeck(Integer.parseInt(id.getText()), deck.getText());
    }

    public void showDeck(MouseEvent mouseEvent) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(showDeck.getText());
        for (Card card : deck.getCards()) {
            if (Duelyst.currentAccount.getAccountHeroes().contains(card)) {
                Hero hero = (Hero) card;
                heroes.getChildren().add(hero.makeCard());
            }
            if (Duelyst.currentAccount.getAccountItems().contains(card)) {
                Item item = (Item) card;
                items.getChildren().add(item.makeCard());
            }
            if (Duelyst.currentAccount.getAccountMinions().contains(card)) {
                Minion minion = (Minion) card;
                minions.getChildren().add(minion.makeCard());
            }
            if (Duelyst.currentAccount.getAccountSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                heroes.getChildren().add(spellCard.makeCard());
            }
        }
    }

    public void showAllDecks(MouseEvent mouseEvent) {

    }

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

    public void createDeck(MouseEvent mouseEvent) {
        collectionMethods.createDeck(showDeck.getText());
    }
    public void deleteDeck(MouseEvent mouseEvent) {
        collectionMethods.deleteDeck(showDeck.getText());
    }

    public void selectMainDeck(MouseEvent mouseEvent) {
        collectionMethods.selectMainDeck(showDeck.getText());
    }

    public void deckValidation(MouseEvent mouseEvent) {
        collectionMethods.validationDeck(showDeck.getText());
    }
}
