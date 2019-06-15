package logic;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;

class Card {
    String name;
    String desc;//Description
    Player owner;
    int ID;//needed in shop
    int price;
    Image image;


    public static Card getCardByName(String name) {
        for (Hero hero : Duelyst.getAllHeroes()) {
            if (hero.name.equals(name)) {
                return hero;
            }
        }
        for (SpellCard spell : Duelyst.getAllSpellCards()) {
            if (spell.name.equals(name)) {
                return spell;
            }
        }
        for (Minion minion : Duelyst.getAllMinions()) {
            if (minion.name.equals(name)) {
                return minion;
            }
        }
        for (Item item : Duelyst.getAllItems()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }


    public Card(String name) throws FileNotFoundException {
        this.name = name;
    }

}
