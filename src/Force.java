import java.util.ArrayList;

public class Force extends SpellCard {
    int AP;//Attack Power
    int HP;//Health Power
    RangeType rangeType;
    int range;
    boolean exhaustion;
    ArrayList<Spell> castedSpells;
    //push sho dg :///
    public Force(String name) {
        super(name);
    }
}
