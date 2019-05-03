import java.util.ArrayList;

class Card {
    int AP;
    int HP;
    int MP;
    String typeOfHero;
    String typeOfCard;
    String specialPower;
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


    @Override
    public String toString() {
        String string = "";
        if (Hero.getAllHeroes().contains(this)) {
            string = " : name : " + this.name + " - AP : " + this.AP + " - HP : " + this.HP + " - class : " + this.typeOfHero + " - special power : " + this.specialPower + " - price : " + this.price;
        } else if (Card.getAllItems().contains(this)) {
            if (!(this.price==0)) {
                string = " : name : " + this.name + " - desc : " + this.desc + " - price : " + this.price;
            }
        } else if (Card.getAllMinions().contains(this)) {
            string = " type : " + this.typeOfCard + " name : " + this.name + " class : " + this.typeOfHero + " - AP : " + this.AP + " - HP : " + this.HP + " MP : " + this.MP + " - special power : " + this.specialPower + " - price : " + this.price;
        } else if (Card.getAllSpells().contains(this)) {
            string = " : type : " + this.typeOfCard + " : name : " + this.name + " MP : " + this.MP + " desc : " + this.desc + " price : " + this.price;
        }
        return string;
    }
}
