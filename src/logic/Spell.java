package logic;

import java.util.ArrayList;

class Spell {
    boolean massacre;//true when u kill every body, false when u kill
    boolean choice;//true means its choice base false means its random
    boolean immunity;
    boolean continuous;
    boolean locationImportance;
    int targetNumber;
    int staticQuantity;
    Time time;
    Side side;
    boolean goodness;//true means its good false means its bad
    ArrayList<Integer> turns;
    boolean eternity;//true means its eternal false means its just for some turns
    Effect effect;
    StaticState staticState;
    TargetRange targetRange;
    TargetStatics targetStatic;
    int targetDistance;// zero by default, natural number when it needs to be, index for change MP effect
    int effectQuantity;
    TargetKind targetKind;
    ArrayList<Integer> locations;
}