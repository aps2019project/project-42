import java.util.ArrayList;

public class CollectionMethods {
    Console console = Console.getInstance();

    Account account = Duelyst.currentAccount;

    void show() {
        console.showCollection(account);
    }

    void search(String string) {
        Card card = new Card(string);
        if (containingInCollection(card)) {
            console.print(card.ID);
        }  else {
            console.cardNotFound();
        }
    }

    void save() {

    }

    void createDeck(String string) {
        Deck deck = new Deck(string);
        if (!account.getDecks().contains(deck)) {
            account.getDecks().add(deck);
            console.deckAdded();
        } else {
            console.deckExists();
        }
    }

    void deleteDeck(String string) {
        Deck deck = new Deck(string);
        if (account.getDecks().contains(deck)) {
            account.getDecks().remove(deck);
            console.deckDeleted();
        } else {
            console.deckNameNotFound();
        }
    }

    void addToDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.getDecks().contains(deck) && containingInCollection(card) && !deck.cards.contains(card)) {
            if (Card.getAllHeroes().contains(card)) {
                if (deck.numOfHeroes < 1) {
                    deck.cards.add(card);
                    deck.numOfHeroes++;
                    console.addCardToDeck();
                } else {
                    console.deckHasHero();
                }
            } else if (Card.getAllItems().contains(card)) {
                if (deck.numOfItems < 1) {
                    deck.numOfItems++;
                    deck.cards.add(card);
                    console.addCardToDeck();
                } else {
                    console.deckHasItem();
                }

            } else if ((Card.getAllMinions().contains(card) || Card.getAllSpells().contains(card))) {
                if (deck.numOfCards < 20) {
                    deck.numOfCards++;
                    deck.cards.add(card);
                    console.addCardToDeck();
                } else {
                    console.deckCardFull();
                }

            }
        } else if (!account.getDecks().contains(deck)) {
            console.deckNameNotFound();
        } else if (!containingInCollection(card)) {
            console.cardNotFound();
        } else if (deck.cards.contains(card)) {
            console.cardExists();
        }
    }

    void removeFromDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.getDecks().contains(deck) && containingInCollection(card) && deck.cards.contains(card)) {
            if (Card.getAllHeroes().contains(card)) {
                deck.cards.remove(card);
                deck.numOfHeroes--;
                console.deleteCardFromDeck();
            } else if (Card.getAllItems().contains(card)) {
                deck.numOfItems--;
                deck.cards.remove(card);
                console.deleteCardFromDeck();
            } else if ((Card.getAllMinions().contains(card) || Card.getAllSpells().contains(card))) {
                deck.numOfCards--;
                deck.cards.remove(card);
                console.deleteCardFromDeck();
            }

        } else if (!account.getDecks().contains(deck)) {
            console.deckNameNotFound();
        } else if (!containingInCollection(card)) {
            console.cardNotFound();
        } else if (!deck.cards.contains(card)) {
            console.cardNotInDeck();
        }
    }


    boolean validateDeck(String string) {
        Deck deck = new Deck(string);
        if (account.getDecks().contains(deck) && deck.numOfHeroes == 1 && deck.numOfCards == 20) {
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
        Deck deck = new Deck(string);
        if (account.getDecks().contains(deck) && validateDeck(string)) {
            account.mainDeck = deck;
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
            /*System.out.println(account.mainDeck.name);
            for (Deck deck : account.decks) {
                if (deck.equals(account.mainDeck)) continue;
                System.out.println();
            }*/
        } else {
            console.showDeckWithoutMainDeck(account);
        }
    }

    void showDeck(String string) {
        Deck deck=new Deck(string);
        if (account.getDecks().contains(deck)) {
            console.showSingleDeck(deck);
        }
    }

    private boolean containingInCollection(Card card) {
        return account.getAccountHeroes().contains(card) || account.getAccountItems().contains(card) || account.getAccountMinions().contains(card) || account.getAccountSpells().contains(card);
    }
}
