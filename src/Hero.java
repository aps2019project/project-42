import java.util.ArrayList;
import java.util.HashMap;

class Hero extends Force {
    int coolDown;
    String typeOfHero;

    public Hero(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return " : name : " + this.name + " - AP : " + this.AP + " - HP : " + this.HP + " - class : " + this.typeOfHero + " - cool down : " + this.coolDown + " - special power : " + this.desc + " - price : " + this.price;
    }
}
