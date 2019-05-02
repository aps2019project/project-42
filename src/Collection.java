import java.util.ArrayList;
import java.util.Scanner;

class Collection extends Menu {

    Console console = Console.getInstance();

    private static Collection collection = new Collection();

    public static Collection getInstance() {
        return collection;
    }

    ArrayList<Card> cards;
    Account account;
    private Scanner scanner = Main.scanner;


    void collectionMenu(String command) {
        String[] commandArray = command.split("\\s+");
        if (command.equals("exit")) {
            exit();
        } else if (command.matches("show(\\s+)menu")){
            showMenu();
        }
        else if (command.matches("show")) {
            show();
        } else if (commandArray[0].equals("search")) {
            search(commandArray[1]);
        } else if (command.matches("save")) {
            save();
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
        } else {
            System.out.println("Invalid command.");
        }

    }

    private void showMenu() {
        console.collectionMenu();
    }

    void show() {
        for (Card card : cards) {

        }
    }

    void search(String string) {
        Card card = new Card(string);
        if (cards.contains(card)) {
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
        } else {
            console.deckExists();
        }
    }

    void deleteDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.decks.remove(deck);
        } else {
            console.deckNameNotFound();
        }
    }

    void addToDeck(String str1, String str2) {
        Card card = new Card(str1);
        Deck deck = new Deck(str2);
        if (account.decks.contains(deck) && cards.contains(card) && !deck.cards.contains(card)) {
            deck.cards.add(card);
            //card.owner=account.owner;
        } else if (!account.decks.contains(deck)) {
            console.deckNotFound();
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
        } else if (!account.decks.contains(deck)) {
            console.deckNotFound();
        } else if (!cards.contains(card)) {
            console.cardNotFound();
        } else if (!deck.cards.contains(card)) {
            console.cardNotInDeck();
        }
    }

    boolean deckValidation(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            return true;
        }
        return false;
    }

    void validateDeck(String string) {
        if (deckValidation(string)) {
            console.validDeck();
        } else {
            console.notValidDeck();
        }
    }

    void selectMainDeck(String string) {
        Deck deck = new Deck(string);
        if (account.decks.contains(deck)) {
            account.mainDeck = deck;
        } else {
            console.deckNotFound();
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
        if (deckValidation(string)) {
            System.out.println();
        }
    }

    void help() {
        console.collectionHelp();
    }

    void exit() {
        Duelyst.currentMenu=MainMenu.getInstance();
    }
}
