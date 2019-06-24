package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    public VBox decks;
    public VBox deck1;
    PrimaryStage primaryStage = PrimaryStage.getInstance();
    CollectionMethods collectionMethods = Duelyst.currentAccount.collectionMethods;

    public void initialize() {
        decks.setVisible(false);
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
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
        clearVboxs();
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(showDeck.getText());
        deckShowing(deck);
    }

    private void deckShowing(Deck deck) {
        clearVboxs();
        for (Card card : deck.getCards()) {
            if (Duelyst.getAllHeroes().contains(card)) {
                Hero hero = (Hero) card;
                heroes.getChildren().add(hero.makeCard());
            }
            if (Duelyst.getAllItems().contains(card)) {
                Item item = (Item) card;
                items.getChildren().add(item.makeCard());
            }
            if (Duelyst.getAllMinions().contains(card)) {
                Minion minion = (Minion) card;
                minions.getChildren().add(minion.makeCard());
            }
            if (Duelyst.getAllSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                minions.getChildren().add(spellCard.makeCard());
            }
        }
    }

    private void clearVboxs() {
        heroes.getChildren().clear();
        items.getChildren().clear();
        minions.getChildren().clear();
    }

    public void showAllDecks() {
        decks.setVisible(true);
        clearVboxs();
        decks.getChildren().clear();
        for (Deck deck : Duelyst.currentAccount.getDecks()) {
            deck1.getChildren().add(deck.showDeck());
        }
        /*for (Deck deck : Duelyst.currentAccount.getDecks()) {
            deck.showDeck().setCursor(Cursor.CLOSED_HAND);
            deck.showDeck().setOnMouseClicked(mouseEvent -> deckShowing(deck));
            System.out.println("heroes minions decks");
        }*/
    }

    public void show(MouseEvent mouseEvent) {
        clearVboxs();
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

    public void invisible(MouseEvent mouseEvent) {
        decks.setVisible(false);
    }
}
