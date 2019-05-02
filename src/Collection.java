import java.util.ArrayList;
import java.util.Scanner;

class Collection extends Menu {
    private static Collection collection = new Collection();

    public static Collection getInstance() {
        return collection;
    }


    void collectionMenu(String command) {
        CollectionMethods collectionMethods = Duelyst.currentAccount.collectionMethods;
        try {
            String[] commandArray = command.split("\\s+");
            if (command.equals("exit")) {
                exit();
            } else if (command.matches("show(\\s+)menu")) {
                showMenu();
            } else if (command.matches("show")) {
                collectionMethods.show();
            } else if (commandArray[0].equals("search")) {

                collectionMethods.search(commandArray[1]);
            } else if (command.matches("save")) {

            } else if (commandArray[0].equals("create") && commandArray[1].equals("deck")) {
                collectionMethods.createDeck(commandArray[2]);
            } else if (commandArray[0].equals("delete") && commandArray[1].equals("deck")) {
                collectionMethods.deleteDeck(commandArray[2]);
            } else if (command.matches("add(\\s+)(\\d+)to(\\s+)deck[a-z0-9]+")) {
                collectionMethods.addToDeck(commandArray[1], commandArray[4]);
            } else if (command.matches("remove(\\s+)(\\d+)from(\\s+)deck[a-z0-9]+")) {
                collectionMethods.removeFromDeck(commandArray[1], commandArray[4]);
            } else if (command.matches("validate(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.validateDeck(commandArray[2]);
            } else if (command.matches("select(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.selectMainDeck(commandArray[2]);
            } else if (command.matches("show(\\s+)all(\\s+)decks")) {
                collectionMethods.showAllDecks();
            } else if (command.matches("show(\\s+)deck(\\s+)[a-z][0-9]+")) {
                collectionMethods.showDeck(commandArray[2]);
            } else if (command.matches("help")) {
                help();
            } else {
                System.out.println("Invalid command.");
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }

    }

    private void showMenu() {
        System.out.println("show collection\nsearch in collection\ncreate deck\ndelete deck\nremove from a deck\ndeck validation\nchoose main deck\nshow decks\nshow a single deck\nsave\nhelp\nexit");
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
        Duelyst.currentMenu = MainMenu.getInstance();
    }
}
