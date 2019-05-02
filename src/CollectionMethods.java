import java.util.ArrayList;

public class CollectionMethods {
    Account account = Duelyst.currentAccount;
    ArrayList<Card> cards;

    void show() {
        for (Card card : cards) {

        }
    }

    void search(String string) {
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            System.out.println("This cart doesn't exist in your collection");
        }
    }

    void save() {

    }

    void createDeck(String string) {
        Deck deck = new Deck(string);
        if (!account.decks.contains(deck)) {
            account.decks.add(deck);
        } else {
            System.out.println("A deck with this name already exists.");
        }
    }

    void deleteDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.decks.remove(deck);
        } else {
            System.out.println("This deck name doesn't exist");
        }
    }

    void addToDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.decks.contains(deck) && cards.contains(card) && !deck.cards.contains(card)) {
            deck.cards.add(card);
            //card.owner=account.owner;
        } else if (!account.decks.contains(deck)) {
            System.out.println("This deck doesn't exist in your collection.");
        } else if (!cards.contains(card)) {
            System.out.println("This card isn't in your collection.");
        } else if (deck.cards.contains(card)) {
            System.out.println("This card already exists in this deck.");
        }
    }

    void removeFromDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.decks.contains(deck) && cards.contains(card) && deck.cards.contains(card)) {
            deck.cards.add(card);
        } else if (!account.decks.contains(deck)) {
            System.out.println("This deck doesn't exist in your collection.");
        } else if (!cards.contains(card)) {
            System.out.println("This card isn't in your collection.");
        } else if (!deck.cards.contains(card)) {
            System.out.println("This card doesn't exists in this deck.");
        }
    }

    void validateDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            System.out.println("Valid deck.");
        } else {
            System.out.println("Invalid deck.");
        }
    }

    void selectMainDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.mainDeck = deck;
        } else {
            System.out.println("This deck doesn't exist in your collection.");
        }
    }

    void showAllDecks() {
        if (account.mainDeck != null) {
            System.out.println(account.mainDeck.name);
            for (Deck deck : account.decks) {
                if (deck.equals(account.mainDeck)) continue;
                System.out.println();
            }
        } else {
            for (Deck deck : account.decks) {
                System.out.println();
            }
        }
    }

    void showDeck(String string) {
        if (account.decks.contains(string)) {
            System.out.println();
        }
    }
}
