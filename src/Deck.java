import java.util.ArrayList;

class Deck {
    int numOfHeroes = 0;
    int numOfCards = 0;
    int numOfItems = 0;
    String name;
    Account account;
    ArrayList<Card> cards;
    Hero hero;
    Item usable;

    public Deck(String name) {
        this.name = name;
    }
}
