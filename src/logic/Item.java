package logic;

import java.util.ArrayList;

class Item extends Card{

    ArrayList<Spell> spells;
    public Item(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return " : name : " + this.name + " - desc : " + this.desc + " - price : " + this.price;
    }
}
