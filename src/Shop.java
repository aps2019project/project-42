import java.util.ArrayList;
import java.util.Scanner;

class Shop extends Menu {

    Console console = Console.getInstance();

    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }

    ArrayList<Card> cards;
    Account account;
    private Scanner scanner = Main.scanner;

    void shopMenu(String command) {
        try {
            String[] commandArray = command.split("\\s+");
            if (command.matches("exit")) {
                exit();
            } else if (command.matches("show(\\s+)menu")){
                showMenu();
            }
            else if (command.matches("buy(\\s+)[0-9a-z]+")) {
                buyCard(commandArray[1]);
            } else if (command.matches("sell(\\s+)[0-9a-z]+")) {
                sellCard(commandArray[1]);
            } else if (command.matches("show(\\s+)collection")) {
                showCollection();
            } else if (command.matches("search(\\s+)[0-9a-z]+")) {
                searchShop(commandArray[1]);
            } else if (command.matches("search(\\s+)collection(\\s+)[0-9a-z]+")) {
                searchCollection(commandArray[2]);
            } else if (command.matches("show")) {
                showShop();
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
        console.shopMenu();
    }

    void buyCard(String string) {
        Card card = new Card(string);
        if (!cards.contains(card)) {
            console.cardNotInShop();
        } else if (card.price > account.money) {
            console.insufficientMoney();
        } //else if (numOfItemsCheck)
        else if (account.cards.contains(card)) {
            console.cardInCollection();
        } else {
            account.cards.add(card);
            card.owner = account.owner;
            account.money -= card.price;
            console.cardAdded();
        }
    }

    void sellCard(String string) {
        Card card = new Card(string);
        if (!account.cards.contains(card)) {
            console.cardNotFound();
        } else {
            account.cards.remove(card);
            account.money += card.price;
            console.sold();
        }
    }

    void showCollection() {
        for (Card card : account.cards) {
            System.out.println();
        }
    }

    void showShop() {
        for (Card card : cards) {
            System.out.println();
        }
    }

    void searchShop(String string) {
        Card card = new Card(string);
        if (cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            console.cardNotInShop();
        }
    }

    void searchCollection(String string) {
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            console.cardNotInShop();
        }
    }

    void help() {
        console.shopHelp();
    }

    void exit() {
        Duelyst.currentMenu=MainMenu.getInstance();
    }
}
