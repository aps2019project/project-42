package logic;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Card {
    Image image;
    public String name;
    String desc;//Description
    Player owner;
    int ID;//needed in shop
    int price;
    String serial;


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

    /*public void setImage(Image image) {
        for (Hero hero : Duelyst.getAllHeroes()) {
            hero.image = new Image("card_backgrounds_/unusable_spell.png");
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
    }*/

    public Card(String name) throws FileNotFoundException {
        this.name = name;
    }

    Card duplicate() throws FileNotFoundException{
        if(Duelyst.getAllMinions().contains(this)) {
            Minion duplicatedCard=new Minion(this.name);
            duplicatedCard.combo=((Minion) this).combo;
            duplicatedCard.ID=((Minion) this).ID;
            duplicatedCard.price=((Minion) this).price;
            duplicatedCard.desc=((Minion) this).desc;
            duplicatedCard.spells=((Minion) this).spells;
            duplicatedCard.typeOfMinion=((Minion) this).typeOfMinion;
            duplicatedCard.HP=((Minion) this).HP;
            duplicatedCard.AP=((Minion) this).AP;
            duplicatedCard.range=((Minion) this).range;
            duplicatedCard.rangeType=((Minion) this).rangeType;
            duplicatedCard.MP=((Minion) this).MP;
            return duplicatedCard;
        }
        else if(Duelyst.getAllItems().contains(this)) {
            Item duplicatedCard=new Item(this.name);
            duplicatedCard.ID=((Item) this).ID;
            duplicatedCard.price=((Item) this).price;
            duplicatedCard.desc=((Item) this).desc;
            duplicatedCard.spells=((Item) this).spells;
            return duplicatedCard;
        }
        else if(Duelyst.getAllHeroes().contains(this)) {
            Hero duplicatedCard=new Hero(this.name);
            duplicatedCard.ID=((Hero) this).ID;
            duplicatedCard.price=((Hero) this).price;
            duplicatedCard.desc=((Hero) this).desc;
            duplicatedCard.spells=((Hero) this).spells;
            duplicatedCard.HP=((Hero) this).HP;
            duplicatedCard.AP=((Hero) this).AP;
            duplicatedCard.range=((Hero) this).range;
            duplicatedCard.rangeType=((Hero) this).rangeType;
            duplicatedCard.MP=((Hero) this).MP;
            duplicatedCard.coolDown=((Hero) this).coolDown;
            duplicatedCard.typeOfHero=((Hero) this).typeOfHero;
            return duplicatedCard;
        }
        else if(Duelyst.getAllSpellCards().contains(this)) {
            SpellCard duplicatedCard=new SpellCard(this.name);
            duplicatedCard.ID=((Hero) this).ID;
            duplicatedCard.price=((Hero) this).price;
            duplicatedCard.desc=((Hero) this).desc;
            duplicatedCard.spells=((Hero) this).spells;
            duplicatedCard.MP=((Hero) this).MP;
            return duplicatedCard;
        }
        return null;
    }

}
