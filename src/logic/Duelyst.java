package logic;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duelyst {
    public static Account currentAccount;


    Account costumeLevel1;
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    static HashMap<String, Integer> wins = new HashMap<>();
    private static ArrayList<Minion> allMinions = new ArrayList<>();
    private static ArrayList<SpellCard> allSpells = new ArrayList<>();
    private static ArrayList<Item> allItems = new ArrayList<>();
    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private static ArrayList<Item> allCollectibleItems = new ArrayList<>();
    public static Menu currentMenu = new Menu();
    static boolean finishGame = false;
    static Scanner scanner = new Scanner(System.in);


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

    public void preStart() throws IOException {
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
}
