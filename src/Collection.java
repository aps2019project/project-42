import java.util.ArrayList;
import java.util.Scanner;

class Collection {
    ArrayList<Card> cards;
    Account account;
    private Scanner scanner = Main.scanner;


    private void collectionMenu() {
        while (true) {
            String cm = scanner.nextLine();
            String cmd = cm.trim();
            String command = cmd.toLowerCase();
            String[] commandArray = command.split("\\s+");
            if (command.equals("end")) {
                exit();
                break;
            } else if (command.matches("show")) {
                show();
            } else if (commandArray[0].equals("search")) {
                search(commandArray[1]);
            } else if (command.matches("save")) {

            } else if (commandArray[0].equals("create") && commandArray[1].equals("deck")) {
                createDeck(commandArray[2]);
            } else if (commandArray[0].equals("delete") && commandArray[1].equals("deck")) {
                deleteDeck(commandArray[2]);
            } else if (command.matches("add(\\s+)(\\d+)to(\\s+)deck[a-z0-9]+")) {
                addToDeck(commandArray[1], commandArray[4]);
            } else if (command.matches("remove(\\s+)(\\d+)from(\\s+)deck[a-z0-9]+")) {
                removeFromDeck(commandArray[1], commandArray[4]);
            } else if (command.matches("validate(\\s+)deck(\\s+)[a-z0-9]+")) {
                validateDeck(commandArray[2]);
            } else if (command.matches("select(\\s+)deck(\\s+)[a-z0-9]+")) {
                selectMainDeck(commandArray[2]);
            } else if (command.matches("show(\\s+)all(\\s+)decks")) {
                showAllDecks();
            } else if (command.matches("show(\\s+)deck(\\s+)[a-z][0-9]+")) {
                showDeck(commandArray[2]);
            } else if (command.matches("help")) {
                help();
            }
        }
    }

    void show() {
        for (Card card : cards) {

        }
    }

    void search(String string) {
        Card card=new Card(string);
        if (cards.contains(card)){
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

    boolean deckValidation(String string){
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)){
            return true;
        } return false;
    }

    void validateDeck(String string) {
        if (deckValidation(string)){
            System.out.println("Valid deck.");
        } else {
            System.out.println("Invalid deck.");
        }
    }

    void selectMainDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.mainDeck=deck;
        } else {
            System.out.println("This deck doesn't exist in your collection.");
        }
    }

    void showAllDecks() {
        if (account.mainDeck!=null){
            System.out.println(account.mainDeck.name);
            for (Deck deck:account.decks) {
                if (deck.equals(account.mainDeck)) continue;
                System.out.println();
            }
        } else {
            for (Deck deck:account.decks) {
                System.out.println();
            }
        }
    }

    void showDeck(String string) {
        if (deckValidation(string)){
            System.out.println();
        }
    }

    void help() {
        System.out.println("to show your collection: show");
        System.out.println("to find a card id in your cards: search [card name]");
        System.out.println("to find an item id in your items: search [item name]");
        System.out.println("to create a deck: create deck [your selective name for deck]");
        System.out.println("to delete a deck: delete deck [the name of the deck you want to delete]");
        System.out.println("to add a card to a deck: add [card id] to deck [deck name]");
        System.out.println("to add an item to a deck: add [item id] to deck [deck name]");
        System.out.println("to remove a card from a deck: remove [card id] from deck [deck name]");
        System.out.println("to remove an item from a deck: remove [item id] from deck [deck name]");
        System.out.println("to check validity of a deck (have exactly 20 cards and 1 hero): validate deck [deck name]");
        System.out.println("to select a deck to be main deck: select deck [deck name]");
        System.out.println("to show cards and items in a deck: show deck [deck name]");
        System.out.println("to save changes: save");
        System.out.println("to return to main menu: exit");

    }

    void exit() {

    }
}
