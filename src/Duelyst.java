import com.google.gson.Gson;

import java.io.*;
import java.util.*;

class Duelyst {
    public static Account currentAccount;


    Account costumeLevel1;
    static ArrayList<Account> accounts = new ArrayList<Account>();
    static HashMap<String, Integer> wins = new HashMap<>();
    private static ArrayList<Minion> allMinions = new ArrayList<>();
    private static ArrayList<SpellCard> allSpells = new ArrayList<>();
    private static ArrayList<Item> allItems = new ArrayList<>();
    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private static ArrayList<Item> allCollectibleItems = new ArrayList<>();
    public static Menu currentMenu = new Menu();
    static boolean finishGame = false;
    static Scanner scanner = new Scanner(System.in);
    String command;



    public static ArrayList<Item> getAllCollectibles() {
        return allCollectibleItems;
    }

    public static ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }

    public static ArrayList<Item> getAllItems() {
        return allItems;
    }

    public static ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    public static ArrayList<SpellCard> getAllSpellCards() {
        return allSpells;
    }

    public void setCurrentMenu() {
        this.currentMenu = AccountPage.getInstance();
    }

    void main() throws IOException {
        preStart();
        while (true) {
            if (finishGame) break;
            command = scanner.nextLine().trim().toLowerCase();
            handler(currentMenu, command);
        }
    }

    private void preStart() throws IOException {
        setCurrentMenu();
        final String[] names = {"Heroes", "Items", "Minions", "SpellCards", "Collectibles"
        };
        for (String name : names) {
            File source = new File(name);
            File[] sources = source.listFiles();
            if (sources != null) {
                for (File file : sources) {
                    if (name.contains("Heroes")) {
                        addHero(file, Hero.class, Duelyst.getAllHeroes());
                    } else if (name.contains("Items")) {
                        addItem(file, Item.class, Duelyst.getAllItems());
                    } else if (name.contains("Minions")) {
                        addMinion(file, Minion.class, Duelyst.getAllMinions());
                    } else if (name.contains("SpellCards")) {
                        addSpell(file, SpellCard.class, Duelyst.getAllSpellCards());
                    } else if (name.contains("CollectibleItems")) {
                        addItem(file, Item.class, Duelyst.getAllCollectibles());
                    }
                }
            }
        }
    }

    private void addSpell(File file, Class<SpellCard> cardClass, ArrayList<SpellCard> list) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        SpellCard spell = new Gson().fromJson(reader, cardClass);
        list.add(spell);
    }

    private void addMinion(File file, Class<Minion> cardClass, ArrayList<Minion> list) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Minion minion = new Gson().fromJson(reader, cardClass);
        list.add(minion);
    }

    private void addItem(File file, Class<Item> cardClass, ArrayList<Item> list) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Item item = new Gson().fromJson(reader, cardClass);
        list.add(item);
    }

    private void addHero(File file, Class<Hero> cardClass, ArrayList<Hero> list) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Hero hero = new Gson().fromJson(reader, cardClass);
        list.add(hero);
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
        } else if (currentMenu.equals(BattleFirstMenu.getInstance())){
            battleMenuHandler(string);
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
    private void battleMenuHandler(String command){
        BattleFirstMenu battleFirstMenu=BattleFirstMenu.getInstance();
        battleFirstMenu.battleMenu(command);
    }

    private void mainMenuHandler(String command) {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.mainMenu(command);
    }

    void start(Scanner scanner) {

    }

}
