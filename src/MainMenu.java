import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends Menu {
    private static MainMenu mainMenu = new MainMenu();

    public static MainMenu getInstance() {
        return mainMenu;
    }

    private Scanner scanner = Main.scanner;

    void mainMenu(String command) {
        try {
            if (command.matches("enter(\\s+)collection")) {
                Duelyst.currentMenu = Collection.getInstance();
            } else if (command.matches("enter(\\s+)battle")) {

            } else if (command.matches("enter(\\s+)shop")) {
                Duelyst.currentMenu = Shop.getInstance();
            } else if (command.matches("enter(\\s+)help")) {
                help();
            } else if (command.matches("exit")) {
                exit();
            } else if (command.matches("show(\\s+)menu")) {
                showMenu();
            } else {
                System.out.println("Invalid command.");
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }

    }

    private void exit() {
        Duelyst.currentMenu=AccountPage.getInstance();
    }

    private void showMenu() {
        System.out.println("battle\ncollection\nshop\nhelp\nexit");
    }

    private void help() {
        System.out.println("to manage your cards: enter collection");
        System.out.println("to buy or sell cards and items: enter shop");
        System.out.println("to play game: enter battle");
        System.out.println("to exit the game: exit");
    }
}
