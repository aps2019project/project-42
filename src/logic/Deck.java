package logic;

import java.util.ArrayList;

class Deck {
    int numOfHeroes = 0;
    int numOfCards = 0;
    int numOfItems = 0;
    String name;
    ArrayList<Card> cards=new ArrayList<>();
    Hero hero;
    Item usable;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Deck(String name) {
        this.name = name;
    }
}
