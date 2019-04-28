import java.util.ArrayList;
import java.util.Scanner;

class Shop {
    ArrayList<Card> cards;
    Account account;

    Shop(Scanner scanner) {
        while (true) {
            String cm = scanner.nextLine();
            String cmd = cm.trim();
            String command = cmd.toLowerCase();
            String[] commandArray = command.split("\\s+");
            if (command.matches("exit")) {
                exit();
                break;
            } else if (command.matches("buy(\\s+)[0-9a-z]+")) {
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
            }
        }
    }

    void buyCard(String string) {
        Card card = new Card(string);
        if (!cards.contains(card)) {
            System.out.println("This card doesn't exist in shop.");
        } else if (card.price > account.money) {
            System.out.println("You don't have enough money.");
        } //else if (numOfItemsCheck)
        else if (account.cards.contains(card)) {
            System.out.println("This card already exists in your collection");
        } else {
            account.cards.add(card);
            card.owner=account.owner;
            account.money -= card.price;
            System.out.println("This card added to your collection successfully.");
        }
    }

    void sellCard(String string) {
        Card card = new Card(string);
        if (!account.cards.contains(card)) {
            System.out.println("This card doesn't exist in your collection.");
        } else {
            account.cards.remove(card);
            account.money += card.price;
            System.out.println("You sold this cart successfully.");
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
            System.out.println("This card doesn't exist in shop.");
        }
    }

    void searchCollection(String string) {
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            System.out.println("This card doesn't exist in shop.");
        }
    }

    void help() {
        System.out.println("to show your cards: show collection");
        System.out.println("to find a card id in shop: search [card name]");
        System.out.println("to find an item id in shop: search [item name]");
        System.out.println("to find a card id in your collection: search collection [card name]");
        System.out.println("to find an item id in your collection: search collection [item name]");
        System.out.println("to buy a card: buy [card name]");
        System.out.println("to buy an item: buy [item name]");
        System.out.println("to sell your card: sell [card name]");
        System.out.println("to sell your item: sell [item name]");
        System.out.println("to see shop: show");
        System.out.println("to return to mainMenu: exit");
    }

    void exit() {

    }
}
