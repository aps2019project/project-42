import java.util.ArrayList;
import java.util.Scanner;

class Shop extends Menu {
    Console console = Console.getInstance();


    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }


    void shopMenu(String command) {
        try {
            ShopMethods shopMethods = Duelyst.currentAccount.shopMethods;
            String[] commandArray = command.split("\\s+");
            if (command.matches("exit")) {
                Account lol = Duelyst.currentAccount;
                exit();
            } else if (command.matches("show(\\s+)menu")) {
                showMenu();
            } else if (command.matches("buy(\\s+)[0-9a-z]+")) {
                shopMethods.buyCard(commandArray[1]);
            } else if (command.matches("sell(\\s+)[0-9a-z]+")) {
                shopMethods.sellCard(Integer.parseInt(commandArray[1]));
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
        console.shopMenu();
    }


    void help() {
        console.shopHelp();
    }

    void exit() {
        Duelyst.currentMenu = MainMenu.getInstance();
    }
}
