import java.util.ArrayList;
import java.util.Scanner;

class Collection extends Menu {

    Console console = Console.getInstance();

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
                collectionMethods.save();
            } else if (commandArray[0].equals("create") && commandArray[1].equals("deck")) {
                collectionMethods.createDeck(commandArray[2]);
            } else if (commandArray[0].equals("delete") && commandArray[1].equals("deck")) {
                collectionMethods.deleteDeck(commandArray[2]);
            } else if (command.matches("add(\\s+)[0-9]+(\\s+)to(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.addToDeck(Integer.parseInt(commandArray[1]), commandArray[4]);
            } else if (command.matches("remove(\\s+)[0-9]+(\\s+)from(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.removeFromDeck(Integer.parseInt(commandArray[1]), commandArray[4]);
            } else if (command.matches("validate(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.validationDeck(commandArray[2]);
            } else if (command.matches("select(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.selectMainDeck(commandArray[2]);
            } else if (command.matches("show(\\s+)all(\\s+)decks")) {
                collectionMethods.showAllDecks();
            } else if (command.matches("show(\\s+)deck(\\s+)[a-z0-9]+")) {
                collectionMethods.showDeck(commandArray[2]);
            } else if (command.matches("help")) {
                help();
            } else {
                console.invalidCommand();
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }

    }

    private void showMenu() {
        console.collectionMenu();
    }


    void help() {
        console.collectionHelp();
    }

    void exit() {
        Duelyst.currentMenu = MainMenu.getInstance();
    }
}
