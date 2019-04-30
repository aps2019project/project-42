import java.util.ArrayList;
import java.util.Scanner;

class Duelyst {
    static ArrayList<Account> accounts;
    ArrayList<Card> source;
    Menu currentMenu = new Menu();
    static Scanner scanner = new Scanner(System.in);
    String command = scanner.nextLine().trim().toLowerCase();

    public void setCurrentMenu() {
        this.currentMenu = AccountPage.getInstance();
    }

    void main() {
        AccountPage accountPage = new AccountPage();
        setCurrentMenu();
        while (true) {
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
        }
    }

    private void shopHandler(String command) {
        Shop shop=Shop.getInstance();
        shop.shopMenu(command);
    }

    private void collectionHandler(String command) {
        Collection collection=Collection.getInstance();
        collection.collectionMenu(command);
    }

    private void accountPageHandler(String command) {
        AccountPage accountPage = AccountPage.getInstance();
        accountPage.accountPageMenu(command);
    }


    void mainMenu(Account account, Scanner scanner) {
        switch (scanner.nextLine()) {
            case ("collection"): {
                break;
            }
            case ("shop"): {
                break;
            }
            case ("battle"): {
                break;
            }
            case ("exit"): {
                break;
            }
            case ("help"): {
                help();
                break;
            }
        }
    }

    private void help() {
        System.out.println("to manage your cards: collection");
        System.out.println("to buy or sell cards and items: shop");
        System.out.println("to play game: battle");
        System.out.println("to exit the game: exit");
    }

    void addToSource(Card card) {

    }

    void start(Scanner scanner) {

    }
}
