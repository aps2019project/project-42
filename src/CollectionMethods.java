import java.util.ArrayList;

public class CollectionMethods {
    Console console = Console.getInstance();

    Account account = Duelyst.currentAccount;
    ArrayList<Card> cards;

    void show() {
        console.showCollection();
    }

    void search(String string) {
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            console.print(card.ID);
            System.out.println(card.ID);
        } else {
            console.cardNotFound();
        }
    }

    void save() {

    }

    void createDeck(String string) {
        Deck deck = new Deck(string);
        if (!account.decks.contains(deck)) {
            account.decks.add(deck);
            console.deckAdded();
        } else {
            console.deckExists();
        }
    }

    void deleteDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.decks.remove(deck);
            console.deckDeleted();
        } else {
            console.deckNameNotFound();
        }
    }

    void addToDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.decks.contains(deck) && cards.contains(card) && !deck.cards.contains(card)) {
            deck.cards.add(card);
            console.addCardToDeck();
            //card.owner=account.owner;
        } else if (!account.decks.contains(deck)) {
            console.deckNameNotFound();
        } else if (!cards.contains(card)) {
            console.cardNotFound();
        } else if (deck.cards.contains(card)) {
            console.cardExists();
        }
    }

    void removeFromDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.decks.contains(deck) && cards.contains(card) && deck.cards.contains(card)) {
            deck.cards.add(card);
            console.deleteCardFromDeck();
        } else if (!account.decks.contains(deck)) {
            console.deckNameNotFound();
        } else if (!cards.contains(card)) {
            console.cardNotFound();
        } else if (!deck.cards.contains(card)) {
            console.cardNotInDeck();
        }
    }

    void validateDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            console.validDeck();
        } else {
            console.notValidDeck();
        }
    }

    void selectMainDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.mainDeck = deck;
            console.setMainDeck();
        } else {
            console.deckNameNotFound();
        }
    }

    void showAllDecks() {
        if (account.mainDeck != null) {
            console.showDeckWithMainDeck();
            /*System.out.println(account.mainDeck.name);
            for (Deck deck : account.decks) {
                if (deck.equals(account.mainDeck)) continue;
                System.out.println();
            }*/
        } else {
            console.showDeckWithoutMainDeck();
        }
    }

    void showDeck(String string) {
        if (account.decks.contains(string)) {
            console.showSingleDeck();
        }
    }
}
