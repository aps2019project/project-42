import java.util.ArrayList;

class Card {
    String name;
    String desc;//Description
    Player owner;
    int ID;//needed in shop
    int price;
    private static ArrayList<Minion> allMinions = new ArrayList<>();
    private static ArrayList<SpellCard> allSpells = new ArrayList<>();
    private static ArrayList<Item> allItems = new ArrayList<>();
    private static ArrayList<Hero> allHeroes = new ArrayList<>();

    public static ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }

    public static ArrayList<Item> getAllItems() {
        return allItems;
    }

    public static ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    public static ArrayList<SpellCard> getAllSpells() {
        return allSpells;
    }

    public Card(String name) {
        this.name = name;
    }

}
