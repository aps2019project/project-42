package logic;

public class MainMenu extends Menu {

    Console console = Console.getInstance();

    private static MainMenu mainMenu = new MainMenu();

    public static MainMenu getInstance() {
        return mainMenu;
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
