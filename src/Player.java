import java.util.ArrayList;

class Player {
    Console console = Console.getInstance();
    Account account;
    Card selectedCard;
    int mana;
    int turn;
    int mood2counter = 0;
    int mood3counter = 0;
    int cooldown = this.deck.hero.coolDown;
    Hand hand = new Hand();
    GraveYard graveYard;
    ComingSoon comingSoon;
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

    boolean targetValidation(Cell cell, Spell spell, Force force) {
        if (spell.targetKind.equals(TargetKind.player) || spell.targetKind.equals(TargetKind.player))
            return true;
        if ((force instanceof Hero && spell.targetKind.equals(TargetKind.minion)) || (force instanceof Minion && spell.targetKind.equals(TargetKind.hero)))
            return false;
        if (spell.locationImportance) {
            boolean b = false;
            if (spell.targetDistance == 0) {
                for (Cell c : spell.location) {
                    if (c.equals(force.cell)) {
                        b = true;
                    }
                }
            } else {
                if (Math.abs(force.cell.getX() + force.cell.getY() - cell.getY() - cell.getX()) <= spell.targetDistance) {
                    b = true;
                }
            }
            if (!b)
                return false;
        }
        if (!spell.staticState.equals(StaticState.nothing)) {
            boolean b = false;
            if (((spell.targetStatics.equals(TargetStatics.AP)) && (((spell.staticState.equals(StaticState.less)) && (spell.staticQuantity > force.AP)) || ((spell.staticState.equals(StaticState.exact)) && (spell.staticQuantity == force.AP)) || ((spell.staticState.equals(StaticState.more)) && (spell.staticQuantity > force.AP)))) || ((spell.targetStatics.equals(TargetStatics.HP)) && (((spell.staticState.equals(StaticState.less)) && (spell.staticQuantity > force.HP)) || ((spell.staticState.equals(StaticState.exact)) && (spell.staticQuantity == force.HP)) || ((spell.staticState.equals(StaticState.more)) && (spell.staticQuantity > force.HP)))) || ((spell.targetStatics.equals(TargetStatics.MP)) && (((spell.staticState.equals(StaticState.less)) && (spell.staticQuantity > force.MP)) || ((spell.staticState.equals(StaticState.exact)) && (spell.staticQuantity == force.MP)) || ((spell.staticState.equals(StaticState.more)) && (spell.staticQuantity > force.MP))))) {//AP:less,exact,more HP:less,exact,more MP:less,exact,more
                b = true;
            }
            if (!b) {
                return false;
            }
        }
        if (!spell.side.equals(Side.both)) {
            boolean b = false;
            if (spell.side.equals(Side.friend) && force.owner.equals(this) || ((spell.side.equals(Side.foe)) && (!force.owner.equals(this)))) {
                b = true;
            }
            if (!b) {
                return false;
            }
        }
        if (!spell.targetRange.equals(TargetRange.anyThing)) {
            boolean b = false;
            if (((spell.targetRange.equals(TargetRange.melee)) && (force.rangeType.equals(RangeType.melee))) || ((spell.targetRange.equals(TargetRange.ranged)) && (force.rangeType.equals(RangeType.ranged))) || ((spell.targetRange.equals(TargetRange.hybrid)) && (force.rangeType.equals(RangeType.hybrid))) || ((spell.targetRange.equals(TargetRange.hyged)) && (!force.rangeType.equals(RangeType.melee))) || ((spell.targetRange.equals(TargetRange.hylee)) && (!force.rangeType.equals(RangeType.ranged)))) {
                b = true;
            }
            if (!b) {
                return false;
            }
        }
        return true;
    }

//    void castSpell(Battle battle, Card card, Spell spell) {
//        if (spell.targetKind.equals(TargetKind.player) &&spell.effect.equals(Effect.changeMana)) {
//            this.mana+=spell.effectQuantity;
//            return;
//        }
//        else if (spell.targetKind.equals(TargetKind.player) &&spell.effect.equals(Effect.changeMP)) {
//            this.hand.cards[spell.targetDistance].MP+=spell.effectQuantity;
//            return;
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//        else if () {
//
//        }
//    }

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

    void fillingHand(Deck deck, Hand hand, ComingSoon comingSoon) {
        int sum = 0;
        for (int j = 0; j < 5; j++) {
            if (hand.cards[j] == null)
                break;
            else {
                sum++;
            }
            if (sum == 5)
                return;
        }
        int i = 0;
        while (!(hand.cards[i] == null)) {
            i++;
        }
        hand.cards[i] = comingSoon.card;
        comingSoon.card = (SpellCard) deck.cards.get(0);
        deck.cards.remove(0);
    }

    int findInHand(Hand hand, Card card) {
        for (int i = 0; i < 5; i++)
            if (card.ID == hand.cards[i].ID)
                return i;
        return -1;
    }

    Player(Account account) {
        this.turn = 1;
        this.account = account;
        this.mana = 2;
        this.deck = account.mainDeck;
        this.usable = account.mainDeck.usable;
        this.deck = shuffleDeck(this.deck);
        for (int i = 0; i < 5; i++) {
            fillingHand(this.deck, this.hand, this.comingSoon);
        }
    }

    void gameEnding() {
        if (battle.flagsNumber == 0) {
            if (battle.secondPlayer.deck.hero.HP == 0 && battle.firstPlayer.deck.hero.HP == 0) {
                battle.draw = true;
                battle.lasting = false;
            } else if (battle.firstPlayer.deck.hero.HP == 0) {
                battle.lasting = false;
                battle.looser = battle.firstPlayer;
                battle.winner = battle.secondPlayer;
            } else if (battle.secondPlayer.deck.hero.HP == 0) {
                battle.lasting = false;
                battle.winner = battle.firstPlayer;
                battle.looser = battle.secondPlayer;
            }
        } else if (battle.flagsNumber == 1) {
            if (this.battle.field.cells[2][4].force.owner.mood2counter == 8) {
                System.out.println("u lost piece of shit");
                battle.looser = this;
                battle.endGame();
                battle.lasting = false;
            }
        } else {
            battle.firstPlayer.mood3counter = 0;
            battle.secondPlayer.mood3counter = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (battle.field.cells[i][j].flag == true && battle.field.cells[i][j].force != null) {
                        battle.field.cells[i][j].force.owner.mood3counter++;
                    }
                }
            }
            if (battle.firstPlayer.mood3counter > battle.flagsNumber / 2) {
                battle.winner = battle.firstPlayer;
                battle.looser = battle.secondPlayer;
                battle.lasting = false;
            } else if (battle.secondPlayer.mood3counter > battle.flagsNumber / 2) {
                battle.looser = battle.firstPlayer;
                battle.winner = battle.secondPlayer;
                battle.lasting = false;
            }
        }
    }

    void deploy(Force force, Cell cell) {
        int index = findInHand(hand, force);
        if (index == -1) {
        } else if (this.mana < force.MP) {
            console.notEnoughMana();
        } else {
            this.mana -= force.MP;
            cell.force = force;
            force.cell = cell;
            hand.cards[index] = null;
            for (int i = 0; i < force.spells.size(); i++) {
                if (force.spells.get(i).time.equals(Time.spawn)) {
                    //castSpell(battle, force, force.spells.get(i));
                }
            }
        }
    }

    void deploy(SpellCard spellCard, Cell cell) {
        if (this.mana < spellCard.MP) {
            console.notEnoughMana();
        } else {
            for (int i = 0; i < spellCard.spells.size(); i++) {
                //castSpell(battle, spellCard, spellCard.spells.get(i));
            }
        }
    }

    void move(Cell originCell, Cell destinationCell) {
        if (originCell.force == null) {
            console.noOrigin();
        } else if (Math.abs(destinationCell.getX() - originCell.getX()) + Math.abs(destinationCell.getY() - originCell.getY()) > 2) {
            console.tooFar();
        } else if (!(destinationCell.force == null)) {
            console.filledHouse();
        } else {
            destinationCell.force = originCell.force;
            originCell.force = null;
            //    destinationCell.force.x=destinationCell.getX();
            //  destinationCell.force.y=destinationCell.getY();
        }
    }

    void attack(Cell originCell, Cell destinationCell) {
        if (destinationCell.force == null) {
            console.emptyDestination();
        } else if (originCell.force == null) {
            console.noOrigin();
        } else if (!rangeValidation(originCell, destinationCell)) {
            console.tooFar();
        } else if (originCell.force.exhaustion) {
            System.out.println("im tired bitch");
        } else {
            destinationCell.force.HP -= originCell.force.AP;
            originCell.force.exhaustion = true;
            if (destinationCell.force.HP < 0)
                destinationCell.force.HP = 0;
            if (rangeValidation(destinationCell, originCell)) {
                originCell.force.HP -= destinationCell.force.AP;
                if (originCell.force.HP < 0)
                    originCell.force.HP = 0;
            }
            goinOrNotGoin(destinationCell);
            goinOrNotGoin(originCell);
            gameEnding();
        }
    }

    void comboAttack(Cell destinationCell, Cell... originCells) {
        boolean b = true;
        for (int i = 0; i < originCells.length; i++) {
            if (!rangeValidation(originCells[i], destinationCell) || originCells[i].force.exhaustion)
                b = false;
        }
        if (!b) {
            System.out.println("cant do that shit");
        } else {
            // b=true;// unneeded
            for (int i = 0; i < originCells.length; i++) {
                destinationCell.force.HP -= originCells[i].force.AP;
                originCells[i].force.exhaustion = true;
                if (destinationCell.force.HP < 0)
                    destinationCell.force.HP = 0;
                if (rangeValidation(destinationCell, originCells[i]) && b) {
                    originCells[i].force.HP -= destinationCell.force.AP;
                    if (originCells[i].force.HP < 0)
                        originCells[i].force.HP = 0;
                    goinOrNotGoin(originCells[i]);
                }
                goinOrNotGoin(destinationCell);
                gameEnding();
            }
        }
    }

    void specialPower(Cell cell) {
        for (Spell s : this.deck.hero.spells)
            //castSpell(battle, this.deck.hero, s);
        cooldown = this.deck.hero.coolDown;
    }

    void endTurn() {
        if (this.battle.field.cells[2][4].force != null) {
            int i = this.battle.field.cells[2][4].force.owner.mood2counter;
            this.battle.firstPlayer.mood2counter = 0;
            this.battle.secondPlayer.mood2counter = 0;
            this.battle.field.cells[2][4].force.owner.mood2counter = i + 1;
        }
        gameEnding();
        this.battle.firstPlayer.cooldown--;
        if (this.battle.firstPlayer.cooldown < 0)
            this.battle.firstPlayer.cooldown = 0;
        this.battle.secondPlayer.cooldown--;
        if (this.battle.secondPlayer.cooldown < 0)
            this.battle.secondPlayer.cooldown = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force.owner.equals(this))
                    battle.field.cells[i][j].force.exhaustion = false;
            }
        }
        this.battle.turn++;
    }

    void conceit() {
        System.out.println("u lost piece of shit");
        battle.looser = this;
        battle.endGame();
        battle.lasting = false;
    }

    void gameInfo1() {
        //System.out.println(account.mainDeck.hero.name + account.mainDeck.hero.HP);
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                if (battle.field.cells[i][j].force == account.mainDeck.hero){
                    System.out.println(account.mainDeck.hero.name + account.mainDeck.hero.HP);
                }
            }
        }
    }

    void gameInfo2(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                if (battle.field.cells[i][j].flag){
                    if (battle.field.cells[i][j].force != null)
                        System.out.println(battle.field.cells[i][j].force);
                    if (battle.field.cells[i][j].force == null)
                        System.out.println(battle.field.cells[i][j].getX() + " , " + battle.field.cells[i][j].getY());
                }
            }
        }
    }

    void gameInfo3(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                if (battle.field.cells[i][j].flag){
                    if (battle.field.cells[i][j].force != null){
                        System.out.println(battle.field.cells[i][j].force.owner + " : " + battle.field.cells[i][j].force);
                    }
                }
            }
        }
    }

    void showMyMinions() {

    }

    void showOppenontMinions() {

    }

    void showCardInfo(Card card) {

    }

    void showHand() {

    }

    void select(int id) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force == account.shopMethods.getCardByIdInCollection(id))
                    selectedCard = account.shopMethods.getCardByIdInCollection(id);
            }
        }
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
// intelj asghal push kon
    void showMenu() {

    }
}
