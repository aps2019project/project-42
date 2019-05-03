import java.util.ArrayList;

class Player {
    Console console = Console.getInstance();
    Account account;
    int mana;
    int turn;
    Hand hand;
    GraveYard graveYard;
    CommingSoon commingSoon;
    Deck deck;
    Item usable;
    Battle battle;
    ArrayList<Item> items;

    void goinOrNotGoin(Cell cell) {
        if (cell.force.HP == 0) {
            this.graveYard.cards.add(cell.force);
            cell.force = null;
        }
    }

    boolean rangeValidation(Cell originCell, Cell destinationCell) {
        if (((originCell.force.rangeType.equals(RangeType.melee)) && ((Math.abs(destinationCell.getX() - originCell.getX()) > 1) || (Math.abs(destinationCell.getY() - originCell.getY()) > 1))) || ((originCell.force.rangeType.equals(RangeType.ranged)) && ((Math.abs(destinationCell.getX() - originCell.getX()) + Math.abs(destinationCell.getY() - originCell.getY()) > originCell.force.range) || ((Math.abs(destinationCell.getX() - originCell.getX()) > 1) || (Math.abs(destinationCell.getY() - originCell.getY()) < 2)))) || ((originCell.force.rangeType.equals(RangeType.hybrid)) && (Math.abs(destinationCell.getX() - originCell.getX()) + Math.abs(destinationCell.getY() - originCell.getY()) > originCell.force.range)))
            return false;
        else
            return true;
    }

    Deck shuffleDeck(Deck deck) {
        ArrayList<Card> cards = new ArrayList<>();
        Deck shuffledDeck = new Deck(deck.name);
        while (deck.cards.size() != 0) {
            int m = (int) (Math.random() * deck.cards.size());
            shuffledDeck.cards.add(deck.cards.get(m));
            deck.cards.remove(m);
        }
        return shuffledDeck;
    }

    void fillingHand(Deck deck, Hand hand, CommingSoon commingSoon) {
        int sum = 0;
        for (int j = 0; j < 5; j+=)
            if (hand.cards[j].equals(null))
                break;
            else{
                sum++;}
        if (sum == 5)
            return;

        int i = 0;
        while (!hand.cards[i].equals(null)) { i++;
            }
            hand.cards[i] = commingSoon.card;
            commingSoon.card = deck.cards.get(0);
            deck.cards.remove(0);

    }

    Player(Account account) {
        this.turn = 1;
        this.account = account;
        this.mana = 2;
        this.deck = account.mainDeck;
        this.usable = account.mainDeck.usable;
        this.deck = shuffleDeck(this.deck);
        for (int i = 0; i < 5; i++) {
            fillingHand(this.deck, this.hand, this.commingSoon);
        }
    }

    void deploy(Minion minion, Cell cell) {
        if (this.mana < minion.MP) {
            console.notEnoughMana();
        } else {
            this.mana -= minion.MP;
            cell.force = minion;
            // minion.x=cell.getX();
            // minion.y=cell.getY();
        }
    }

    void move(Cell originCell, Cell destinationCell) {
        if (originCell.force.equals(null)) {
            console.noOrigin();
        } else if (Math.abs(destinationCell.getX() - originCell.getX()) + Math.abs(destinationCell.getY() - originCell.getY()) > 2) {
            console.tooFar();
        } else if (!destinationCell.force.equals(null)) {
            console.filledHouse();
        } else {
            destinationCell.force = originCell.force;
            originCell.force = null;
            //    destinationCell.force.x=destinationCell.getX();
            //  destinationCell.force.y=destinationCell.getY();
        }
    }

    void attack(Cell originCell, Cell destinationCell) {
        if (destinationCell.force.equals(null)) {
            console.emptyDestination();
        } else if (originCell.force.equals(null)) {
            console.noOrigin();
        } else if (!rangeValidation(originCell, destinationCell)) {
            console.tooFar();
        } else {
            destinationCell.force.HP -= originCell.force.AP;
            if (destinationCell.force.HP < 0)
                destinationCell.force.HP = 0;
            if (rangeValidation(destinationCell, originCell)) {
                originCell.force.HP -= destinationCell.force.AP;
                if (originCell.force.HP < 0)
                    originCell.force.HP = 0;
            }
            goinOrNotGoin(destinationCell);
            goinOrNotGoin(originCell);
        }
    }

    void comboAttack(Cell destinationCell, Cell... originCells) {
        boolean b = true;
        for (int i = 0; i < originCells.length; i++) {
            if (!rangeValidation(originCells[i], destinationCell))
                b = false;
        }
        if (!b) {
            console.tooFar();
        } else {
            // b=true;// unneeded
            for (int i = 0; i < originCells.length; i++) {
                destinationCell.force.HP -= originCells[i].force.AP;
                if (destinationCell.force.HP < 0)
                    destinationCell.force.HP = 0;
                if (rangeValidation(destinationCell, originCells[i]) && b) {
                    originCells[i].force.HP -= destinationCell.force.AP;
                    if (originCells[i].force.HP < 0)
                        originCells[i].force.HP = 0;
                    goinOrNotGoin(originCells[i]);
                }
                goinOrNotGoin(destinationCell);
            }
        }
    }

    void specialPower(Cell cell) {
        //cast spell this.deck.hero.specialPower
    }

    void endTurn() {
        this.battle.turn++;
    }

    void gameInfo() {

    }

    void showMyMinions() {

    }

    void showOppenontMinions() {

    }

    void showCardInfo(Card card) {

    }

    void showHand() {

    }

    void select() {

    }

    void showCollectibles() {

    }

    void showItemInfo() {

    }

    void useItem(Item item) { //use collectible
        for (Item a : this.items) {
            //cast spell a
        }
    }

    void showNextCard() {

    }

    void enterGraveYard() {

    }

    void showInfoInGraveYard(Card card) {

    }

    void showCardsInGraveYard() {

    }

    void help() {

    }

    void exit() {

    }

    void showMenu() {

    }
}
