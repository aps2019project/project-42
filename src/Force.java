import java.util.ArrayList;

public class Force extends SpellCard {
    int AP;//Attack Power
    int HP;//Health Power
    Cell cell;
    RangeType rangeType;
    int range;
    boolean exhaustion;
    boolean armed = true;
    boolean stunned = false;
    int holiness = 0;
    boolean disarmImmune = false;
    boolean apImmune = false;
    boolean hpImmune = false;
    boolean stunImmune = false;
    boolean holyImmune = false;
    boolean fieryImmune = false;
    boolean poisonImmune = false;
    ArrayList<Spell> castedSpells;
    public Force(String name) {
        super(name);
    }
}
