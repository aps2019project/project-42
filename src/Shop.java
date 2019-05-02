import java.util.ArrayList;
import java.util.Scanner;

class Shop extends Menu {
    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }

    ArrayList<Card> cards;
    Account account;

    void shopMenu(String command) {
        try {
            ShopMethods shopMethods = Duelyst.currentAccount.shopMethods;
            String[] commandArray = command.split("\\s+");
            if (command.matches("exit")) {
                exit();
            } else if (command.matches("show(\\s+)menu")) {
                showMenu();
            } else if (command.matches("buy(\\s+)[0-9a-z]+")) {
                shopMethods.buyCard(commandArray[1]);
            } else if (command.matches("sell(\\s+)[0-9a-z]+")) {
                shopMethods.sellCard(commandArray[1]);
            } else if (command.matches("show(\\s+)collection")) {
                shopMethods.showCollection();
            } else if (command.matches("search(\\s+)[0-9a-z]+")) {
                shopMethods.searchShop(commandArray[1]);
            } else if (command.matches("search(\\s+)collection(\\s+)[0-9a-z]+")) {
                shopMethods.searchCollection(commandArray[2]);
            } else if (command.matches("show")) {
                shopMethods.showShop();
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
        System.out.println("show collection\nserach in collection\nshow shop\nsearch in shop\nbuy\nsell\nhelp\nexit");
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
        Duelyst.currentMenu = MainMenu.getInstance();
    }
}
