import java.util.ArrayList;
import java.util.Scanner;

class Duelyst {
    static ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<Card> source;
    public static Menu currentMenu = new Menu();
    public static boolean finishGame=false;

    static Scanner scanner = new Scanner(System.in);
    String command;

    public void setCurrentMenu() {
        this.currentMenu = AccountPage.getInstance();
    }

    void main() {
        AccountPage accountPage = new AccountPage();
        setCurrentMenu();
        while (true) {
            command = scanner.nextLine().trim().toLowerCase();
            handler(currentMenu, command);
        }
    }

    void handler(Menu currentMenu, String string) {
        if (currentMenu.equals(AccountPage.getInstance())) {
            accountPageHandler(string);
        } else if (currentMenu.equals(Collection.getInstance())) {
            collectionHandler(string);
        } else if (currentMenu.equals(Shop.getInstance())) {
            shopHandler(string);
        } else if (currentMenu.equals(MainMenu.getInstance())) {
            mainMenuHandler(string);
        }
    }

    private void shopHandler(String command) {
        Shop shop = Shop.getInstance();
        shop.shopMenu(command);
    }

    private void collectionHandler(String command) {
        Collection collection = Collection.getInstance();
        collection.collectionMenu(command);
    }

    private void accountPageHandler(String command) {
        AccountPage accountPage = AccountPage.getInstance();
        accountPage.accountPageMenu(command);
    }

    private void mainMenuHandler(String command) {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.mainMenu(command);
    }

    /*public ArrayList<Account> getAccounts(){
        return accounts;
    }*/

    void addToSource(Card card) {

    }

    void start(Scanner scanner) {

    }
}
