import java.util.ArrayList;

class Battle {
    boolean find(ArrayList<Integer> numbers, int number) {
        for (int a : numbers)
            if (a == number)
                return true;
        return false;
    }

    void fillCollectibles(Field field) {

    }

    void fillFlags(Field field) {
        if (flagsNumber % 2 == 1)
            field.cells[(int) (Math.random() * 2)][4].flag = true;
        ArrayList<Integer> indexes = new ArrayList<>();
        while (indexes.size() < flagsNumber / 2) {
            int m = (int) (Math.random() * 20);
            if (!find(indexes, m))
                indexes.add(m);
        }
        for (int a : indexes) {
            field.cells[a / 4][a % 4].flag = true;
            field.cells[a / 4][8 - a % 4].flag = true;
        }
    }
    void endGame(){
        if(winner.equals(firstPlayer)||looser.equals(secondPlayer)){
            looser=secondPlayer;
            winner=firstPlayer;
        }
        else {
            winner=secondPlayer;
            looser=firstPlayer;
        }

    }

    GameType gameType;
    boolean lasting=true;
    boolean draw=false;
    Player winner;
    Player looser;
    int flagsNumber;
    int turn = 1;
    Player firstPlayer;
    Player secondPlayer;
    Field field;

    Battle(GameType gameType1, int flagNumber1, Account first, Account second) {
        gameType = gameType1;
        flagsNumber = flagNumber1;
        firstPlayer = new Player(first);
        secondPlayer = new Player(second);
        firstPlayer.battle = this;
        secondPlayer.battle = this;
        field = new Field();
        field.battle = this;
        field.cells[2][0].force = firstPlayer.deck.hero;
        field.cells[2][8].force = firstPlayer.deck.hero;
        if (flagsNumber == 1) {
            field.cells[2][4].flag = true;
        } else if (flagsNumber > 1) {
            fillFlags(field);
        }
    }
}
