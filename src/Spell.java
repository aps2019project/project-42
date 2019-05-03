import java.util.ArrayList;

class Spell{
    boolean massacare;//true when u kill every body, false when u kill
    int targetsNumber;
    Time time;
    boolean immunity;
    Side side;
    boolean choice;//true means its choice base false means its random
    boolean goodness;//true means its good false means its bad
    ArrayList<Integer> Turns;
    boolean eternity;//true means its eternal false means its just for some turns
    Effect effect;
    StaticState staticState;
    TargetRange targetRange;
    TargetStatics targetStatics;
    int TargetDistance;// zero by default, natural number when it needs to be
    int effectQuantity;
    TargetKind targetKind;
    ArrayList<Cell> location;
    int duration;
}
