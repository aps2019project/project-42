import com.google.gson.Gson;

import java.io.*;
import java.util.*;

class Duelyst {
    public static Account currentAccount;

    static ArrayList<Account> accounts = new ArrayList<Account>();
    static HashMap<String,Integer> wins=new HashMap<>();
    ArrayList<Card> source;
    public static Menu currentMenu = new Menu();
    static boolean finishGame=false;
    static Scanner scanner = new Scanner(System.in);
    String command;

    public void setCurrentMenu() {
        this.currentMenu = AccountPage.getInstance();
    }

    void main() throws IOException {
        preStart();
        /*for (Card card : Card.getAllHeroes()){
            System.out.println(card);
        }*/
        while (true) {
            if (finishGame) break;
            command = scanner.nextLine().trim().toLowerCase();
            handler(currentMenu, command);
        }
    }

    private void preStart() throws IOException {
        setCurrentMenu();
        final String[] names = {
                "Heroes", "Items",
        };
        for (String name : names) {
            File source = new File(name);
            File[] sources = source.listFiles();
            if (sources != null) {
                for (File file : sources) {
                    if (name.contains("Hero")) {
                        addHero(file , Card.class , Card.getAllHeroes());
                        //addCard(file, Card.class, Card.getAllHeroes());
                    } /*else if (name.contains("Item")) {
                        addCard(file, Card.class, Card.getAllItems());
                    } else if (name.contains("Minion")) {
                        addCard(file, Card.class, Card.getAllMinions());
                    } else if (name.contains("Spell")) {
                        addCard(file, Card.class, Card.getAllSpells());
                    }*/
                }
            }
        }
    }

    private <T> void addCard(File file, Class<T> classOfT, ArrayList<T> list) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        T card = new Gson().fromJson(reader, classOfT);
        list.add(card);
    }

    private void addHero(File file , Class<Card> cardClass , ArrayList<Card> list ) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Card card = new Gson().fromJson(reader, cardClass);
        list.add(card);
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
