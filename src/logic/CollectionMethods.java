package logic;

public class CollectionMethods {
    Console console = Console.getInstance();

    void show() {
        console.showCollection(Duelyst.currentAccount);
    }

    void search(String string) {
        Duelyst.currentAccount.shopMethods.searchCollection(string);
    }

    void save() {
        console.collectionSave();
    }

    void createDeck(String string) {
        if (getDeckByName(string) != null) {
            console.deckExists();
        } else {
            Deck deck = new Deck(string);
            Duelyst.currentAccount.getDecks().add(deck);
            console.deckAdded();
        }
    }

    void deleteDeck(String string) {
        if (getDeckByName(string) == null) {
            console.deckNameNotFound();
        } else {
            Deck deck = getDeckByName(string);
            Duelyst.currentAccount.getDecks().remove(deck);
            console.deckDeleted();
            if (Duelyst.currentAccount.mainDeck == deck) {
                Duelyst.currentAccount.mainDeck = null;
                console.deckNotValidateAnymore();
            }
        }
    }

    void addToDeck(int id, String string) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && card != null) {
            if (Duelyst.getAllHeroes().contains(card)) {
                if (deck.numOfHeroes < 1) {
                    deck.getCards().add(card);
                    deck.hero=(Hero) card;
                    deck.numOfHeroes++;
                    console.addCardToDeck();
                } else {
                    console.deckHasHero();
                }
            } else if (Duelyst.getAllItems().contains(card)) {
                if (deck.numOfItems < 1) {
                    deck.numOfItems++;
                    deck.usable=(Item) card;
                    System.out.println("dorost shod");
                    deck.getCards().add(card);
                    console.addCardToDeck();
                } else {
                    console.deckHasItem();
                }

            } else if ((Duelyst.getAllMinions().contains(card)) || Duelyst.getAllSpellCards().contains(card)) {
                deck.getCards().add(card);
                deck.numOfCards++;
                console.addCardToDeck();
            }
            if (Duelyst.currentAccount.mainDeck != null && Duelyst.currentAccount.mainDeck.cards.contains(card)) {
                Duelyst.currentAccount.mainDeck = null;
                console.deckNotValidateAnymore();
            }
        } else if (deck == null) {
            console.deckNameNotFound();
        } else {
            console.cardNotFound();
        }
    }

    void removeFromDeck(int id, String string) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && card != null && deck.cards.contains(card)) {
            if (Duelyst.getAllHeroes().contains(card)) {
                deck.cards.remove(card);
                deck.numOfHeroes--;
                deck.hero=null;
                console.deleteCardFromDeck();
            } else if (Duelyst.getAllItems().contains(card)) {
                deck.numOfItems--;
                deck.usable=null;
                deck.cards.remove(card);
                console.deleteCardFromDeck();
            } else if ((Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card))) {
                deck.numOfCards--;
                deck.cards.remove(card);
                console.deleteCardFromDeck();
            }
            if (Duelyst.currentAccount.mainDeck != null && Duelyst.currentAccount.mainDeck.cards.contains(card)) {
                Duelyst.currentAccount.mainDeck = null;
                console.deckNotValidateAnymore();
            }
        } else if (deck == null) {
            console.deckNameNotFound();
        } else if (card == null) {
            console.cardNotFound();
        } else if (!deck.cards.contains(card)) {
            console.cardNotInDeck();
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

    void validationDeck(String string) {
        if (validateDeck(string)) {
            console.validDeck();
        } else {
            console.notValidDeck();
        }
    }

    void selectMainDeck(String string) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null && validateDeck(string)) {
            Duelyst.currentAccount.mainDeck = deck;
            console.setMainDeck();
        } else if (!validateDeck(string)) {
            console.notValidDeck();
        } else {
            console.deckNameNotFound();
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

    void showDeck(String string) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck != null) {
            console.showSingleDeck(deck);
        } else {
            console.deckNameNotFound();
        }
    }

    Deck getDeckByName(String string) {
        for (Deck deck : Duelyst.currentAccount.getDecks()) {
            if (deck.name.equals(string)) {
                return deck;
            }
        }
        return null;
    }
}
