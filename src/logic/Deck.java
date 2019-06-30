package logic;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Deck {
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

    public Label showDeck(){
        Label info=new Label(this.name+" Num of cards:"+this.cards.size()+"\nHero:"+this.hero.name);
        info.setStyle("-fx-font: 24 Nazli");
        return info;
    }

}
