package logic;

class SpellCard extends Item {

    int MP;

    public SpellCard(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return " : type : spell - : name : " + this.name + " - MP : " + this.MP + " - desc : " + this.desc + " - price : " + this.price;

    }
}
