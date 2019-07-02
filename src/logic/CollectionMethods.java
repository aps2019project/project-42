package logic;

import javafx.scene.control.Alert;

public class CollectionMethods {
    Console console = Console.getInstance();


    public void search(String string) {
        Duelyst.currentAccount.shopMethods.searchCollection(string);
    }

    void save() {
        console.collectionSave();
    }

    public void createDeck(String string) {
        if (getDeckByName(string) != null) {
            Alert exist = new Alert(Alert.AlertType.ERROR);
            exist.setContentText("A deck with this name already exists.");
            exist.show();
        } else {
            Deck deck = new Deck(string);
            Duelyst.currentAccount.getDecks().add(deck);
            Alert deckAdded = new Alert(Alert.AlertType.INFORMATION);
            deckAdded.setContentText("Deck added successfully.");
            deckAdded.show();
        }
    }

    public void deleteDeck(String string) {
        if (getDeckByName(string) == null) {
            Alert deckNotFound = new Alert(Alert.AlertType.ERROR);
            deckNotFound.setContentText("This deck doesn't exist in your collection.");
            deckNotFound.show();
        } else {
            Deck deck = getDeckByName(string);
            Duelyst.currentAccount.getDecks().remove(deck);
            Alert deleteDeck = new Alert(Alert.AlertType.INFORMATION);
            deleteDeck.setContentText("Deck deleted successfully.");
            if (Duelyst.currentAccount.mainDeck == deck) {
                Duelyst.currentAccount.mainDeck = null;
                deleteDeck.setContentText("Deck deleted successfully.\nYour main deck is not valid anymore.");
            }
            deleteDeck.show();
        }
    }

    public void addToDeck(String id, String string) {
        Card card = Duelyst.currentAccount.shopMethods.getCardBySerialInCollection(id);
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && card != null) {
            if (Duelyst.getAllHeroes().contains(card)) {
                if (deck.numOfHeroes < 1) {
                    deck.getCards().add(card);
                    deck.hero = (Hero) card;
                    deck.numOfHeroes++;
                    Alert add = new Alert(Alert.AlertType.INFORMATION);
                    add.setContentText("Card added to deck.");
                    add.show();
                } else {
                    Alert add = new Alert(Alert.AlertType.ERROR);
                    add.setContentText("Your deck has hero, you can't add another hero to it.");
                    add.show();
                }
            } else if (Duelyst.getAllItems().contains(card)) {
                if (deck.numOfItems < 1) {
                    deck.numOfItems++;
                    deck.usable = (Item) card;
                    deck.getCards().add(card);
                    Alert add = new Alert(Alert.AlertType.INFORMATION);
                    add.setContentText("Card added to deck.");
                    add.show();
                } else {
                    Alert add = new Alert(Alert.AlertType.ERROR);
                    add.setContentText("Your deck has item, you can't add another item to it.");
                    add.show();
                }

            } else if ((Duelyst.getAllMinions().contains(card)) || Duelyst.getAllSpellCards().contains(card)) {
                deck.getCards().add(card);
                deck.numOfCards++;
                Alert add = new Alert(Alert.AlertType.INFORMATION);
                add.setContentText("Card added to deck.");
                add.show();
            }
            if (Duelyst.currentAccount.mainDeck != null && Duelyst.currentAccount.mainDeck.cards.contains(card)) {
                Duelyst.currentAccount.mainDeck = null;
                Alert add = new Alert(Alert.AlertType.INFORMATION);
                add.setContentText("Your main deck is not valid anymore.");
                add.show();
            }
        } else if (deck == null) {
            Alert add = new Alert(Alert.AlertType.INFORMATION);
            add.setContentText("Invalid deck.");
            add.show();
        } else {
            Alert add = new Alert(Alert.AlertType.INFORMATION);
            add.setContentText("Card not found.");
            add.show();
        }
    }

    public void removeFromDeck(String id, String string) {
        Card card = Duelyst.currentAccount.shopMethods.getCardBySerialInCollection(id);
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && card != null && deck.cards.contains(card)) {
            if (Duelyst.getAllHeroes().contains(card)) {
                deck.cards.remove(card);
                deck.numOfHeroes--;
                deck.hero = null;
            } else if (Duelyst.getAllItems().contains(card)) {
                deck.numOfItems--;
                deck.usable = null;
                deck.cards.remove(card);
            } else if ((Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card))) {
                deck.numOfCards--;
                deck.cards.remove(card);
            }
            if (Duelyst.currentAccount.mainDeck != null && Duelyst.currentAccount.mainDeck.cards.contains(card)) {
                Duelyst.currentAccount.mainDeck = null;
                Alert remove = new Alert(Alert.AlertType.INFORMATION);
                remove.setContentText("Your main deck isn't valid anymore.");
                remove.show();
            }
            Alert remove = new Alert(Alert.AlertType.INFORMATION);
            remove.setContentText("Card removed from your deck.");
            remove.show();
        } else if (deck == null) {
            Alert remove = new Alert(Alert.AlertType.INFORMATION);
            remove.setContentText("Deck not found.");
            remove.show();
        } else if (card == null) {
            Alert remove = new Alert(Alert.AlertType.INFORMATION);
            remove.setContentText("Card not found.");
            remove.show();
        } else if (!deck.cards.contains(card)) {
            Alert remove = new Alert(Alert.AlertType.INFORMATION);
            remove.setContentText("This card ins't in this deck.");
            remove.show();
        }
    }


    boolean validateDeck(String string) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && deck.numOfHeroes == 1 && deck.numOfCards == 20) {
            return true;
        } else {
            return false;
        }
    }

    public void validationDeck(String string) {
        if (validateDeck(string)) {
            Alert valid = new Alert(Alert.AlertType.ERROR);
            valid.setContentText("Invalid deck.");
            valid.show();
        } else {
            Alert notValid = new Alert(Alert.AlertType.ERROR);
            notValid.setContentText("Invalid deck.");
            notValid.show();
        }
    }

    public void selectMainDeck(String string) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && validateDeck(string)) {
            Duelyst.currentAccount.mainDeck = deck;
            Alert mainDeck = new Alert(Alert.AlertType.INFORMATION);
            mainDeck.setContentText("You set your main deck.");
            mainDeck.show();
        } else if (!validateDeck(string)) {
            Alert notValid = new Alert(Alert.AlertType.ERROR);
            notValid.setContentText("Invalid deck.");
            notValid.show();
        } else {
            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setContentText("This deck doesn't exist in your collection.");
            notFound.show();
        }
    }

    void showAllDecks() {
        if (Duelyst.currentAccount.getDecks().isEmpty()) {
            console.noDeck();
        } else {
            if (Duelyst.currentAccount.mainDeck != null) {
                console.showDeckWithMainDeck(Duelyst.currentAccount);
            } else {
                console.showDeckWithoutMainDeck(Duelyst.currentAccount);
            }
        }
    }


    public Deck getDeckByName(String string) {
        for (Deck deck : Duelyst.currentAccount.getDecks()) {
            if (deck.name.equals(string)) {
                return deck;
            }
        }
        return null;
    }
}
