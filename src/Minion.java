import java.util.ArrayList;
import java.util.HashMap;

class Minion extends Force{
    boolean combo;
    ArrayList<HashMap<Integer,Integer>> nemesis;//Card ID , number of attacks
    public Minion(String name) {
        super(name);
    }
}
