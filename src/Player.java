import java.util.ArrayList;
import java.util.Scanner;

class Player {
    Console console = Console.getInstance();
    Account account;
    int mana;
    int turn;
    int mood2counter = 0;
    int mood3counter = 0;
    int coolDown;

    Hand hand = new Hand();
    GraveYard graveYard;
    ComingSoon comingSoon;
    Deck deck;
    Battle battle;
    ArrayList<Item> items;
    Card selectedCard;

    void goinOrNotGoin(Cell cell) {
        if (cell.force.HP == 0) {
            this.graveYard.cards.add(cell.force);
            cell.force = null;
        }
    }

    boolean targetValidation(Cell activationCell, Cell targetCell, Spell spell) {
        if (spell.targetKind.equals(TargetKind.player))
            return true;
        if (spell.locationImportance) {
            boolean b = false;
            if (spell.targetDistance == 0) {
                for (Cell c : spell.location) {
                    if (c.equals(targetCell)) {
                        b = true;
                    }
                }
            } else {
                if (Math.abs(activationCell.getX() + activationCell.getY() - targetCell.getY() - targetCell.getX()) <= spell.targetDistance) {
                    b = true;
                }
            }
            if (!b)
                return false;
        }
        if (spell.targetKind.equals(TargetKind.cell))
            return true;
        if (targetCell.force == null)
            return false;
        if ((targetCell.force instanceof Hero && spell.targetKind.equals(TargetKind.minion)) || (targetCell.force instanceof Minion && spell.targetKind.equals(TargetKind.hero)))
            return false;

        if (!spell.staticState.equals(StaticState.nothing)) {
            boolean b = false;
            if (((spell.targetStatics.equals(TargetStatics.AP)) && (((spell.staticState.equals(StaticState.less)) && (spell.staticQuantity > targetCell.force.AP)) || ((spell.staticState.equals(StaticState.exact)) && (spell.staticQuantity == targetCell.force.AP)) || ((spell.staticState.equals(StaticState.more)) && (spell.staticQuantity > targetCell.force.AP)))) || ((spell.targetStatics.equals(TargetStatics.HP)) && (((spell.staticState.equals(StaticState.less)) && (spell.staticQuantity > targetCell.force.HP)) || ((spell.staticState.equals(StaticState.exact)) && (spell.staticQuantity == targetCell.force.HP)) || ((spell.staticState.equals(StaticState.more)) && (spell.staticQuantity > targetCell.force.HP)))) || ((spell.targetStatics.equals(TargetStatics.MP)) && (((spell.staticState.equals(StaticState.less)) && (spell.staticQuantity > targetCell.force.MP)) || ((spell.staticState.equals(StaticState.exact)) && (spell.staticQuantity == targetCell.force.MP)) || ((spell.staticState.equals(StaticState.more)) && (spell.staticQuantity > targetCell.force.MP))))) {//AP:less,exact,more HP:less,exact,more MP:less,exact,more
                b = true;
            }
            if (!b) {
                return false;
            }
        }
        if (!spell.side.equals(Side.both)) {
            boolean b = false;
            if (spell.side.equals(Side.friend) && targetCell.force.owner.equals(this) || ((spell.side.equals(Side.foe)) && (!targetCell.force.owner.equals(this)))) {
                b = true;
            }
            if (!b) {
                return false;
            }
        }
        if (!spell.targetRange.equals(TargetRange.anyThing)) {
            boolean b = false;
            if (((spell.targetRange.equals(TargetRange.melee)) && (targetCell.force.rangeType.equals(RangeType.melee))) || ((spell.targetRange.equals(TargetRange.ranged)) && (targetCell.force.rangeType.equals(RangeType.ranged))) || ((spell.targetRange.equals(TargetRange.hybrid)) && (targetCell.force.rangeType.equals(RangeType.hybrid))) || ((spell.targetRange.equals(TargetRange.hyged)) && (!targetCell.force.rangeType.equals(RangeType.melee))) || ((spell.targetRange.equals(TargetRange.hylee)) && (!targetCell.force.rangeType.equals(RangeType.ranged)))) {
                b = true;
            }
            if (!b) {
                return false;
            }
        }
        return true;
    }

    void eatCollectible(Cell cell) {
        if (cell.collectible != null) {
            this.items.add(cell.collectible);
            cell.collectible = null;
        }
    }

    Cell getCell() {
        System.out.println("tell me where do u want it, give me x and enter and y and another enter :)");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return this.battle.field.cells[x - 1][y - 1];
    }

    ArrayList<Cell> validCellsForSpellCasting(Cell activationCell, Spell spell) {
        ArrayList<Cell> output = new ArrayList<>();
        if (!spell.choice) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (targetValidation(activationCell, this.battle.field.cells[i][j], spell) && ((spell.massacre) || (output.size() <= spell.targetsNumber)))
                        output.add(this.battle.field.cells[i][j]);
                }
            }
        } else {
            for (int i = 0; i < spell.targetsNumber; i++) {
                Cell cell = getCell();
                if (targetValidation(activationCell, cell, spell))
                    output.add(cell);
            }
        }
        return output;
    }

    void castSpell(Cell activationCell, Force force, Spell spell) {
        if (force != null) {
            if (spell.effect.equals(Effect.fiery))
                force.HP -= 2;
            else
                force.castedSpells.add(spell);
            return;
        }
        if (spell.targetKind.equals(TargetKind.player) && spell.effect.equals(Effect.changeMana)) {
            this.mana += spell.effectQuantity;
            return;
        } else if (spell.targetKind.equals(TargetKind.cell)) {
            getCell().cellEffects.add(spell);
            return;
        }
        if (spell.immunity) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                cells.get(i).force.castedSpells.add(spell);
                if (spell.effect.equals(Effect.poison))
                    cells.get(i).force.poisonImmune = true;
                else if (spell.effect.equals(Effect.disarm))
                    cells.get(i).force.disarmImmune = true;
                else if (spell.effect.equals(Effect.changeAP))
                    cells.get(i).force.apImmune = true;
                else if (spell.effect.equals(Effect.changeHP))
                    cells.get(i).force.hpImmune = true;
                else if (spell.effect.equals(Effect.stun))
                    cells.get(i).force.stunImmune = true;
                else if (spell.effect.equals(Effect.holy))
                    cells.get(i).force.holyImmune = true;
                else if (spell.effect.equals(Effect.fiery))
                    cells.get(i).force.fieryImmune = true;
            }
            return;
        }
        if (spell.effect.equals(Effect.disarm)) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                if (!cells.get(i).force.disarmImmune) {
                    cells.get(i).force.armed = false;
                    cells.get(i).force.castedSpells.add(spell);
                }
            }
        } else if (spell.effect.equals(Effect.stun)) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                if (!cells.get(i).force.stunImmune) {
                    cells.get(i).force.stunned = true;
                    cells.get(i).force.castedSpells.add(spell);
                }
            }
        } else if (spell.effect.equals(Effect.holy)) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                if (!cells.get(i).force.holyImmune) {
                    cells.get(i).force.holiness = spell.effectQuantity;
                    cells.get(i).force.castedSpells.add(spell);
                }
            }
        } else if (spell.effect.equals(Effect.changeAP)) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                if (!cells.get(i).force.apImmune) {
                    cells.get(i).force.AP += spell.effectQuantity;
                    cells.get(i).force.castedSpells.add(spell);
                }
            }
        } else if (spell.effect.equals(Effect.changeHP)) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                if (!cells.get(i).force.hpImmune) {
                    cells.get(i).force.HP += spell.effectQuantity;
                    cells.get(i).force.castedSpells.add(spell);
                }
            }
        } else if (spell.effect.equals(Effect.dispel)) {
            ArrayList<Cell> cells = validCellsForSpellCasting(activationCell, spell);
            for (int i = 0; i < cells.size(); i++) {
                for (int j = 0; j < cells.get(i).force.castedSpells.size(); j++) {
                    if ((cells.get(i).force.castedSpells.get(j).goodness && !cells.get(i).force.owner.equals(this)) || (!cells.get(i).force.castedSpells.get(j).goodness && cells.get(i).force.owner.equals(this))) {
                        if (cells.get(i).force.castedSpells.get(j).effect.equals(Effect.disarm)) {
                            if (cells.get(i).force.castedSpells.get(j).immunity) {
                                cells.get(i).force.disarmImmune = false;
                                cells.get(i).force.castedSpells.remove(j);
                                j--;
                            } else {
                                cells.get(i).force.armed = true;
                                if (!cells.get(i).force.castedSpells.get(j).continuous) {
                                    cells.get(i).force.castedSpells.remove(j);
                                    j--;
                                } else {
                                    cells.get(i).force.castedSpells.get(j).eternity = true;
                                }
                            }
                        } else if (cells.get(i).force.castedSpells.get(j).effect.equals(Effect.changeAP)) {
                            if (cells.get(i).force.castedSpells.get(j).immunity) {
                                cells.get(i).force.apImmune = false;
                                cells.get(i).force.castedSpells.remove(j);
                                j--;
                            } else {
                                cells.get(i).force.AP -= cells.get(i).force.castedSpells.get(j).effectQuantity;
                                if (!cells.get(i).force.castedSpells.get(j).continuous) {
                                    cells.get(i).force.castedSpells.remove(j);
                                    j--;
                                } else {
                                    cells.get(i).force.castedSpells.get(j).eternity = true;
                                }
                            }
                        } else if (cells.get(i).force.castedSpells.get(j).effect.equals(Effect.changeHP)) {
                            if (cells.get(i).force.castedSpells.get(j).immunity) {
                                cells.get(i).force.hpImmune = false;
                                cells.get(i).force.castedSpells.remove(j);
                                j--;
                            } else {
                                cells.get(i).force.HP -= cells.get(i).force.castedSpells.get(j).effectQuantity;
                                if (!cells.get(i).force.castedSpells.get(j).continuous) {
                                    cells.get(i).force.castedSpells.remove(j);
                                    j--;
                                } else {
                                    cells.get(i).force.castedSpells.get(j).eternity = true;
                                }
                            }
                        } else if (cells.get(i).force.castedSpells.get(j).effect.equals(Effect.stun)) {
                            if (cells.get(i).force.castedSpells.get(j).immunity) {
                                cells.get(i).force.stunImmune = false;
                                cells.get(i).force.castedSpells.remove(j);
                                j--;
                            } else {
                                cells.get(i).force.stunned = false;
                                if (!cells.get(i).force.castedSpells.get(j).continuous) {
                                    cells.get(i).force.castedSpells.remove(j);
                                    j--;
                                } else {
                                    cells.get(i).force.castedSpells.get(j).eternity = true;
                                }
                            }
                        } else if (cells.get(i).force.castedSpells.get(j).effect.equals(Effect.holy)) {
                            if (cells.get(i).force.castedSpells.get(j).immunity) {
                                cells.get(i).force.holyImmune = false;
                                cells.get(i).force.castedSpells.remove(j);
                                j--;
                            } else {
                                cells.get(i).force.holiness = 0;
                                if (!cells.get(i).force.castedSpells.get(j).continuous) {
                                    cells.get(i).force.castedSpells.remove(j);
                                    j--;
                                } else {
                                    cells.get(i).force.castedSpells.get(j).eternity = true;
                                }
                            }
                        } else if (cells.get(i).force.castedSpells.get(j).effect.equals(Effect.poison)) {
                            if (cells.get(i).force.castedSpells.get(j).immunity) {
                                cells.get(i).force.poisonImmune = false;
                                cells.get(i).force.castedSpells.remove(j);
                                j--;
                            } else {
                                if (!cells.get(i).force.castedSpells.get(j).continuous) {
                                    cells.get(i).force.castedSpells.remove(j);
                                    j--;
                                } else {
                                    cells.get(i).force.castedSpells.get(j).eternity = true;
                                }
                            }
                        }
                    }
                }
            }
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
        this.deck = shuffleDeck(this.deck);
        this.coolDown = this.deck.hero.coolDown;
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
            System.out.println("there is no such thing");
        } else if (this.mana < force.MP) {
            console.notEnoughMana();
        } else {
            this.mana -= force.MP;
            cell.force = force;
            force.cell = cell;
            hand.cards[index] = null;
            eatCollectible(cell);
            for (int i = 0; i < force.spells.size(); i++) {
                if (force.spells.get(i).time.equals(Time.spawn)) {
                    castSpell(cell, null, force.spells.get(i));
                }
            }
        }
    }

    void deploy(SpellCard spellCard, Cell cell) {
        if (this.mana < spellCard.MP) {
            console.notEnoughMana();
        } else {
            for (int i = 0; i < spellCard.spells.size(); i++) {
                castSpell(cell, null, spellCard.spells.get(i));
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
            eatCollectible(destinationCell);
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
            if (rangeValidation(destinationCell, originCell) && destinationCell.force.armed) {
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
            for (int i = 0; i < originCells.length; i++) {
                destinationCell.force.HP -= originCells[i].force.AP;
                originCells[i].force.exhaustion = true;
                if (destinationCell.force.HP < 0)
                    destinationCell.force.HP = 0;
                if (rangeValidation(destinationCell, originCells[i]) && i == originCells.length - 1 && destinationCell.force.armed) {
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
            castSpell(cell, null, s);
        coolDown = this.deck.hero.coolDown;
    }

    void endTurn() {
        if (this.battle.field.cells[2][4].force != null) {
            int i = this.battle.field.cells[2][4].force.owner.mood2counter;
            this.battle.firstPlayer.mood2counter = 0;
            this.battle.secondPlayer.mood2counter = 0;
            this.battle.field.cells[2][4].force.owner.mood2counter = i + 1;
        }
        gameEnding();
        this.battle.firstPlayer.coolDown--;
        if (this.battle.firstPlayer.coolDown < 0)
            this.battle.firstPlayer.coolDown = 0;
        this.battle.secondPlayer.coolDown--;
        if (this.battle.secondPlayer.coolDown < 0)
            this.battle.secondPlayer.coolDown = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force != null && battle.field.cells[i][j].force.owner.equals(this) && !battle.field.cells[i][j].force.stunned)
                    battle.field.cells[i][j].force.exhaustion = false;
                for (Spell spell : battle.field.cells[i][j].cellEffects)
                    castSpell(battle.field.cells[i][j], battle.field.cells[i][j].force, spell);
                if (battle.field.cells[i][j].force != null)
                    for (Spell spell : battle.field.cells[i][j].force.castedSpells) {
                        if (spell.continuous && spell.eternity) {
                            if (spell.effect.equals(Effect.disarm)) {
                                battle.field.cells[i][j].force.armed = false;
                            } else if (spell.effect.equals(Effect.stun)) {
                                battle.field.cells[i][j].force.stunned = true;
                            } else if (spell.effect.equals(Effect.holy)) {
                                battle.field.cells[i][j].force.holiness = spell.effectQuantity;
                            } else if (spell.effect.equals(Effect.changeAP)) {
                                battle.field.cells[i][j].force.AP += spell.effectQuantity;
                            } else if (spell.effect.equals(Effect.changeHP)) {
                                battle.field.cells[i][j].force.HP += spell.effectQuantity;
                            }
                        }
                    }
            }
            if (battle.lasting)
                this.battle.turn++;
            else {
                MatchHistory a = new MatchHistory();
                MatchHistory b = new MatchHistory();
                if (battle.draw) {
                    System.out.println("u both sucked equally");
                    a.result = 0;
                    b.result = 0;
                } else if (battle.winner.equals(battle.firstPlayer)) {
                    System.out.println("first player won");
                    a.result = 1;
                    b.result = -1;
                } else {
                    System.out.println("second player won");
                    b.result = 1;
                    a.result = -1;
                }
                a.enemy = this.battle.secondPlayer.account;
                b.enemy = this.battle.firstPlayer.account;
                this.battle.firstPlayer.account.matchList.add(a);
                this.battle.firstPlayer.account.matchList.add(b);
                try {
                    battle.finalize();
                } catch (Throwable throwable) {
                }
            }
            if (turn % 2 == 1)
                battle.player = battle.firstPlayer;
            else
                battle.player = battle.secondPlayer;
            battle.player.mana = (int) (turn / 2) + 2;
            fillingHand(battle.player.deck, battle.player.hand, battle.player.comingSoon);
        }
    }

    void conceit() {
        System.out.println("u lost piece of shit");
        battle.looser = this;
        battle.endGame();
        battle.lasting = false;
    }

    void useItem(int id, Cell cell) {
        Item item = (Item) battle.firstPlayer.account.shopMethods.getCardByIdInCollection(id);
        for (Spell spell : item.spells) {
            castSpell(cell, null, spell);
        }
    }

    void gameInfo1() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force == account.mainDeck.hero) {
                    System.out.println(account.mainDeck.hero.name + " " + account.mainDeck.hero.HP);
                }
            }
        }
    }

    void gameInfo2() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].flag) {
                    if (battle.field.cells[i][j].force != null)
                        System.out.println(battle.field.cells[i][j].force);
                    if (battle.field.cells[i][j].force == null)
                        System.out.println(battle.field.cells[i][j].getX() + " , " + battle.field.cells[i][j].getY());
                }
            }
        }
    }

    void gameInfo3() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].flag) {
                    if (battle.field.cells[i][j].force != null) {
                        System.out.println(battle.field.cells[i][j].force.owner + " : " + battle.field.cells[i][j].force);
                    }
                }
            }
        }
    }

    void showMyMinions() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force != null && battle.field.cells[i][j].force.owner.equals(this)) {
                    System.out.println(battle.field.cells[i][j].force.name + " : HP: " + battle.field.cells[i][j].force.HP + " AP: " + battle.field.cells[i][j].force.AP + " row: " + (i + 1) + " column: " + (j + 1));
                }
            }
        }
    }

    void showOpponentMinions() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force != null) {
                    if (battle.field.cells[i][j].force != null && !battle.field.cells[i][j].force.owner.equals(this)) {
                        System.out.println(battle.field.cells[i][j].force.name + " : HP: " + battle.field.cells[i][j].force.HP + " AP: " + battle.field.cells[i][j].force.AP + " row: " + (i + 1) + " column: " + (j + 1));
                    }
                }
            }
        }
    }

    void showCardInfo(int id) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        if (card != null) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (battle.field.cells[i][j].force.equals(card)) {
                        if (Duelyst.getAllMinions().contains(card)) {
                            Minion minion = (Minion) card;
                            System.out.println(minion + " row: " + (minion.cell.getX() + 1) + " column: " + (minion.cell.getY() + 1));
                        } else if (Duelyst.getAllHeroes().contains(card)) {
                            Hero hero = (Hero) card;
                            System.out.println(hero + " row: " + (hero.cell.getX() + 1) + " column: " + (hero.cell.getY() + 1));
                        } else if (Duelyst.getAllSpellCards().contains(card)) {
                            SpellCard spellCard = (SpellCard) card;
                            System.out.println(spellCard);
                        }
                    }
                }
            }
        }
    }

    void showHand() {
        for (int i = 0; i < 5; i++) {
            Card card = hand.cards[i];
            if (Duelyst.getAllMinions().contains(card)) {
                Minion minion = (Minion) card;
                System.out.println(minion);
            } else if (Duelyst.getAllHeroes().contains(card)) {
                Hero hero = (Hero) card;
                System.out.println(hero);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                System.out.println(spellCard);
            } else if (Duelyst.getAllItems().contains(card)) {
                Item item = (Item) card;
                System.out.println(item);
            }
        }
    }

    void select(int id) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (battle.field.cells[i][j].force == account.shopMethods.getCardByIdInCollection(id))
                    selectedCard = account.shopMethods.getCardByIdInCollection(id);
                else if (battle.field.cells[i][j].collectible == account.shopMethods.getCardByIdInCollection(id))
                    selectedCard = account.shopMethods.getCardByIdInCollection(id);
            }
        }
    }

    void showCollectibles() {
        for (Item item : Duelyst.getAllCollectibles()) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (battle.field.cells[i][j].force == item) {
                        System.out.println(item);
                    }
                }
            }
        }
    }

    void showItemInfo(int id) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        if (Duelyst.getAllCollectibles().contains(card) && items.contains(card)) {
            Item item = (Item) card;
            System.out.println(item);
        }
    }

    void showNextCard() {
        Card card = comingSoon.card;
        if (Duelyst.getAllMinions().contains(card)) {
            Minion minion = (Minion) card;
            System.out.println(minion);
        } else if (Duelyst.getAllHeroes().contains(card)) {
            Hero hero = (Hero) card;
            System.out.println(hero);
        } else if (Duelyst.getAllSpellCards().contains(card)) {
            SpellCard spellCard = (SpellCard) card;
            System.out.println(spellCard);
        }
    }

    void enterGraveYard() {

    }

    void showInfoInGraveYard(int id) {
        Card card = Duelyst.currentAccount.shopMethods.getCardByIdInCollection(id);
        if (graveYard.cards.contains(card)) {
            if (Duelyst.getAllMinions().contains(card)) {
                Minion minion = (Minion) card;
                System.out.println(minion);
            } else if (Duelyst.getAllHeroes().contains(card)) {
                Hero hero = (Hero) card;
                System.out.println(hero);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                System.out.println(spellCard);
            }
        }
    }

    void showCardsInGraveYard() {
        for (Card card : graveYard.cards) {
            if (Duelyst.getAllMinions().contains(card)) {
                Minion minion = (Minion) card;
                System.out.println(minion);
            } else if (Duelyst.getAllHeroes().contains(card)) {
                Hero hero = (Hero) card;
                System.out.println(hero);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                System.out.println(spellCard);
            }
        }
    }

    void help() {

    }

    void exit() {

    }

    void showMenu() {

    }

    void showField() {
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                if (battle.field.cells[i][j].force != null){
                    System.out.println(i + "," + j + ": " + battle.field.cells[i][j].force.HP + " " + battle.field.cells[i][j].force.HP + "\n");
                }
            }
        }
    }
}
