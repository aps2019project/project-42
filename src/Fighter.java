import java.util.HashMap;

class Fighter extends SpellCard{
    int AP;//Attack Power
    int HP;//Health Power
    int rangeType;
    int range;
    HashMap<Integer,Integer> nemesis;//Card ID , number of attacks

    public Fighter(String name) {
        super(name);
    }
}
