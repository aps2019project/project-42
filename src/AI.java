import java.util.Random;

public class AI extends Player{
    private AI ai;

    AI(Account account) {
        super(account);
    }

    void start(){
        int a = (int) (Math.random() * 8);
        if (a == 0) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 7; j++) {
                    if (this.battle.field.cells[i][j + 2].force != null) {
                        if (this.battle.field.cells[i][j + 2].force == null)
                            move(this.battle.field.cells[i][j], this.battle.field.cells[i][j + 2]);
                        else if (this.battle.field.cells[i][j + 1].force == null)
                            move(this.battle.field.cells[i][j], this.battle.field.cells[i][j + 1]);
                    }
                }
            }
        } else if (a == 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; i < 5; i++) {
                    for (int k = 0; j < 9; j++) {
                        if (this.battle.field.cells[j][k].force == null) {
                            if (this.hand.cards[i] instanceof Minion)
                                deploy((Minion) this.hand.cards[i], this.battle.field.cells[j][k]);
                            else
                                deploy((SpellCard) this.hand.cards[i], this.battle.field.cells[j][k]);
                        }
                    }
                }
            }
        } else if (a == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; i < 5; i++) {
                    for (int k = 0; i < 5; i++) {
                        for (int l = 0; i < 5; i++) {
                            if (this.battle.field.cells[i][j].force != null && this.battle.field.cells[k][l].force != null && rangeValidation(this.battle.field.cells[i][j], this.battle.field.cells[k][l]))
                                attack(this.battle.field.cells[i][j], this.battle.field.cells[k][l]);
                        }
                    }
                }
            }
        } else if (a == 3) {
            int r = (int) (Math.random() * 4);
            if (r == 5)
                conceit();
        } else if (a == 4) {
            endTurn();
        }
    }
}
