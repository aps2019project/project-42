import java.util.ArrayList;

class Deck {
    int numOfHeroes = 0;
    int numOfCards = 0;
    int numOfItems = 0;
    String name;
    Account account;
    ArrayList<SpellCard> cards=new ArrayList<>();
    Hero hero;
    Item item;
    Item usable;

    public ArrayList<SpellCard> getCards() {
        return cards;
    }

    public Deck(String name) {
        this.name = name;
    }
}
