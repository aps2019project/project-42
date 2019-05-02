import java.util.ArrayList;

class Card {
    int AP;
    int HP;
    String typeOfHero;
    String specialPower;
    String name;
    String desc;//Description
    Player owner;
    int ID;//needed in shop
    int price;
    static ArrayList<Card> allHeroes = new ArrayList<>();
    static ArrayList<Card> allMinions = new ArrayList<>();
    static ArrayList<Card> allSpells = new ArrayList<>();
    static ArrayList<Card> allItems = new ArrayList<>();

    public static ArrayList<Card> getAllHeroes() {
        return allHeroes;
    }

    public static ArrayList<Card> getAllItems() {
        return allItems;
    }

    public static ArrayList<Card> getAllMinions() {
        return allMinions;
    }

    public static ArrayList<Card> getAllSpells() {
        return allSpells;
    }

    public Card(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        String string = "";
        if (Card.getAllHeroes().contains(this)) {
            string = this.name + "\n" + this.AP + "\n" + this.HP + "\n" + this.price + "\n" + this.typeOfHero + "\n" + this.specialPower;
        }
        return string;
    }
}
