import java.util.ArrayList;

public class CollectionMethods {
    Console console = Console.getInstance();
    Account account = Duelyst.currentAccount;

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
        if (getDeckByName(string)!=null){
            console.deckExists();
        } else {
            Deck deck=new Deck(string);
            Duelyst.currentAccount.getDecks().add(deck);
            console.deckAdded();
        }
    }

    void deleteDeck(String string) {
        if (getDeckByName(string)==null){
            console.deckNameNotFound();
        } else {
            Deck deck=getDeckByName(string);
            Duelyst.currentAccount.getDecks().remove(deck);
            console.deckDeleted();
        }
        /*Deck deck = new Deck(string);
        if (account.getDecks().contains(deck)) {
            account.getDecks().remove(deck);
            console.deckDeleted();
        } else {
            console.deckNameNotFound();
        }*/
    }

    void addToDeck(int id, String string) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck!=null && card!=null) {
            if (Duelyst.getAllHeroes().contains(card)) {
                if (deck.numOfHeroes < 1) {
                    deck.cards.add(card);
                    deck.numOfHeroes++;
                    console.addCardToDeck();
                } else {
                    console.deckHasHero();
                }
            } else if (Duelyst.getAllItems().contains(card)) {
                if (deck.numOfItems < 1) {
                    deck.numOfItems++;
                    deck.cards.add(card);
                    console.addCardToDeck();
                } else {
                    console.deckHasItem();
                }

            } else if ((Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card))) {
                //if (deck.numOfCards < 20) {
                    deck.numOfCards++;
                    deck.cards.add(card);
                    console.addCardToDeck();
                //} else {
                    //console.deckCardFull();
                //}

            }
        } else if (deck==null) {
            console.deckNameNotFound();
        } else {
            console.cardNotFound();
        }
    }

    void removeFromDeck(int id, String string) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck!=null && card!=null && deck.cards.contains(card)) {
            if (Duelyst.getAllHeroes().contains(card)) {
                deck.cards.remove(card);
                deck.numOfHeroes--;
                console.deleteCardFromDeck();
            } else if (Duelyst.getAllItems().contains(card)) {
                deck.numOfItems--;
                deck.cards.remove(card);
                console.deleteCardFromDeck();
            } else if ((Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card))) {
                deck.numOfCards--;
                deck.cards.remove(card);
                console.deleteCardFromDeck();
            }

        } else if (deck==null) {
            console.deckNameNotFound();
        } else if (card==null) {
            console.cardNotFound();
        } else if (!deck.cards.contains(card)) {
            console.cardNotInDeck();
        }
    }


    boolean validateDeck(String string) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck!=null && deck.numOfHeroes == 1 && deck.numOfCards == 20) {
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
        if (deck!=null && validateDeck(string)) {
            Duelyst.currentAccount.mainDeck = deck;
            console.setMainDeck();
        } else if (!validateDeck(string)) {
            console.notValidDeck();
        } else {
            console.deckNameNotFound();
        }
    }

    void showAllDecks() {
        if (account.mainDeck != null) {
            console.showDeckWithMainDeck(account);
        } else {
            console.showDeckWithoutMainDeck(account);
        }
    }

    void showDeck(String string) {
        Deck deck = Duelyst.currentAccount.collectionMethods.getDeckByName(string);
        if (deck!=null) {
            console.showSingleDeck(deck);
        } else {
            console.deckNameNotFound();
        }
    }
    Deck getDeckByName(String string){
        for (Deck deck:Duelyst.currentAccount.getDecks()){
            if (deck.name.equals(string)){
                return deck;
            }
        }
        return null;
    }
}
