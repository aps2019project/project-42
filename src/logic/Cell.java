package logic;

import java.util.ArrayList;

class Cell{
    Item collectible;
    boolean flag;
    Force force;
    ArrayList<Spell> cellEffects;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void setX(int x) {

        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }
}
