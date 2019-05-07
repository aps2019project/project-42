import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends Menu {

    Console console = Console.getInstance();

    private static MainMenu mainMenu = new MainMenu();

    public static MainMenu getInstance() {
        return mainMenu;
    }

    void mainMenu(String command) {
        try {
            if (command.matches("enter(\\s+)collection")) {
                Duelyst.currentMenu = Collection.getInstance();
            } else if (command.matches("enter(\\s+)battle")) {
                if (Duelyst.currentAccount.mainDeck!=null){
                    Duelyst.currentMenu=BattleMenu.getInstance();
                } else {
                    console.invalidMainDeck();
                }
            } else if (command.matches("enter(\\s+)shop")) {
                Duelyst.currentMenu = Shop.getInstance();
            } else if (command.matches("enter(\\s+)help")) {
                help();
            } else if (command.matches("exit")) {
                exit();
            } else if (command.matches("show(\\s+)menu")) {
                showMenu();
            } else {
                console.invalidCommand();
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }

    }

    private void exit() {
        Duelyst.currentMenu = AccountPage.getInstance();
    }

    private void showMenu() {
        console.showMainMenu();
    }

    private void help() {
        console.showMainHelp();
    }
}
