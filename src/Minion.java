import java.util.ArrayList;
import java.util.HashMap;

class Minion extends Force {
    boolean combo;
    String typeOfMinion;
    ArrayList<HashMap<Integer, Integer>> nemesis;//Card ID , number of attacks

    public Minion(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return  " type : minion - name : " + this.name + " - class : " + this.typeOfMinion + " - AP : " + this.AP + " - HP : " + this.HP + " - MP : " + this.MP + " - special power : " + this.desc + " - price : " + this.price;
    }
}
