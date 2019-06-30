package logic;

import java.util.Scanner;

public class BattleFirstMenu extends Menu {
    //Scanner scanner=new Scanner(System.in);
    Scanner scanner = Duelyst.scanner;
    Console console = Console.getInstance();
    private static BattleFirstMenu battleFirstMenu = new BattleFirstMenu();

    public static BattleFirstMenu getInstance() {
        return battleFirstMenu;
    }

    void battleMenu(String command) {
        //logic.Battle battle = logic.Duelyst.currentAccount.battle;
        //        String[] commandArray = command.split("\\s+");
        try {
            if (command.equals("single player")) {
                System.out.println("u entered single player");
                costumeGameSingle();
            } else if (command.equals("multi player")) {
                System.out.println("u entered multi player");
                multiPlayer();
            } else if (command.equals("exit")) {
                Duelyst.currentMenu = MainMenu.getInstance();
            } else {
                console.invalidCommand();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void multiPlayer() {
        console.chooseOpponent();
        for (Account account : Duelyst.accounts) {
            console.showUser(account);
        }
        String c1 = scanner.nextLine();
        for (Account account : Duelyst.accounts) {
            if (account.getUser().equals(c1) && !Duelyst.currentAccount.getUser().equals(c1)) {
                if (account.mainDeck == null) {
                    console.invalidMainDeck();
                    break;
                }
                console.chooseMood();
                String c2 = scanner.nextLine();
                if (c2.matches("start multiplayer game [123] (\\d)+")) {
                    String[] c2Array = c2.split("\\s");
                    Battle battle = new Battle(GameType.multi, Integer.parseInt(c2Array[4]), Duelyst.currentAccount, account);
                   /* if (Integer.parseInt(c2Array[3]) == 1) {
                        boolean endGame = false;
                        while (!endGame) {
                            String command = scanner.nextLine().trim().toLowerCase();

                            battleGameMenu(command);

                        }
                    } else if (Integer.parseInt(c2Array[3]) == 2) {
                        boolean endGame = false;
                        while (!endGame) {
                            String command = scanner.nextLine().trim().toLowerCase();

                            battleGameMenu(command);

                        }
                    } else if (Integer.parseInt(c2Array[3]) == 3) {
                        if (Integer.parseInt(c2Array[4]) > 1) {
                            boolean endGame = false;
                            while (!endGame) {
                                String command = scanner.nextLine().trim().toLowerCase();

                                battleGameMenu(command);

                            }
                        } else {
                            console.invalidNumOfFlags();
                        }
                    }*/
                } else {
                    console.invalidCommand();
                }
            } else if (Duelyst.currentAccount.getUser().equals(c1)) {
                console.notValidAcc();
            }
        }
    }

    private void costumeGameSingle() {
        String c1 = scanner.nextLine();

        Account levelOneAccount = new Account("username1", "password1");
        Deck levelOneDeck = new Deck("one");
        levelOneDeck.hero = Duelyst.getAllHeroes().get(8);          //add white damon
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(12));     //1 persian archer
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(25));     //2 tooranian spear
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(27));     //3
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(3));     //black demon
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(4));     //5
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(5));     //6
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(6));     //7
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(7));     //8
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(8));     //9
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(9));     //10
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(10));    //11
        levelOneDeck.cards.add(Duelyst.getAllMinions().get(11));    //12
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(0));  //1
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(1));  //2
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(2));  //3
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(3));  //4
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(4));  //5
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(5));  //6
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(6));  //7
        levelOneDeck.cards.add(Duelyst.getAllSpellCards().get(7));  //8
        levelOneDeck.usable = Duelyst.getAllItems().get(9);
        levelOneAccount.decks.add(levelOneDeck);
        levelOneAccount.mainDeck = levelOneDeck;
        AI levelOne = new AI(levelOneAccount);
        levelOneAccount.owner = levelOne;
        levelOne.account = levelOneAccount;


        //        logic.Account levelTwoAccount = new logic.Account("username2", "password2");
        //        logic.AI levelTwo = new logic.AI(levelTwoAccount);
        //        levelTwoAccount.owner = levelTwo;
        //        levelTwo.account = levelTwoAccount;
        //        logic.Deck levelTwoDeck = new logic.Deck("two");
        //        levelTwoDeck.hero = logic.Duelyst.getAllHeroes().get(0);          //add a hero
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(0));     //1 minion
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(1));     //2
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(2));     //3
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(3));     //4
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(4));     //5
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(5));     //6
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(6));     //7
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(7));     //8
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(8));     //9
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(9));     //10
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(10));    //11
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllMinions().get(11));    //12
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(0));  //1
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(1));  //2
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(2));  //3
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(3));  //4
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(4));  //5
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(5));  //6
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(6));  //7
        //        levelTwoDeck.cards.add(logic.Duelyst.getAllSpellCards().get(7));  //8
        //        levelTwoDeck.usable = logic.Duelyst.getAllItems().get(0);
        //        levelOne.deck = levelTwoDeck;
        //        levelOneAccount.decks.add(levelTwoDeck);
        //        levelOneAccount.mainDeck = levelTwoDeck;
        //
        //
        //        logic.Account levelThreeAccount = new logic.Account("username3", "password3");
        //        logic.AI levelThree = new logic.AI(levelThreeAccount);
        //        levelThreeAccount.owner = levelThree;
        //        levelThree.account = levelThreeAccount;
        //        logic.Deck levelThreeDeck = new logic.Deck("three");
        //        levelThreeDeck.hero = logic.Duelyst.getAllHeroes().get(0);          //add a hero
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(0));     //1 minion
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(1));     //2
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(2));     //3
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(3));     //4
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(4));     //5
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(5));     //6
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(6));     //7
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(7));     //8
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(8));     //9
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(9));     //10
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(10));    //11
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllMinions().get(11));    //12
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(0));  //1
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(1));  //2
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(2));  //3
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(3));  //4
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(4));  //5
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(5));  //6
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(6));  //7
        //        levelThreeDeck.cards.add(logic.Duelyst.getAllSpellCards().get(7));  //8
        //        levelThreeDeck.usable = logic.Duelyst.getAllItems().get(0);
        //        levelThree.deck = levelThreeDeck;
        //        levelThreeAccount.decks.add(levelThreeDeck);
        //        levelThreeAccount.mainDeck = levelThreeDeck;

        if (c1.equals("story")) {
            console.storyGame();
            int t = scanner.nextInt();
            Battle battle = new Battle(GameType.multi, t, Duelyst.currentAccount, levelOneAccount);
        } else if (c1.equals("costume game")) {
            console.singleCostumePlayer();
            String string = scanner.nextLine();
/*
            if (string.matches("start game [0-9a-z]+ mode[123] (\\d+)")) {
                String[] stringArray = string.split("\\s");
                if (logic.Duelyst.currentAccount.collectionMethods.getDeckByName(stringArray[2]) != null) {
                    if (Integer.parseInt(stringArray[3]) == 1) {
                        boolean endGame = false;
                        while (!endGame) {
                            String command = scanner.nextLine().trim().toLowerCase();
                            battleGameMenu(command);

                        }

                    } else if (Integer.parseInt(stringArray[3]) == 2) {
                        boolean endGame = false;
                        while (!endGame) {
                            String command = scanner.nextLine().trim().toLowerCase();
                            battleGameMenu(command);

                        }
                    } else if (Integer.parseInt(stringArray[3]) == 3) {
                        if (Integer.parseInt(stringArray[4]) > 1) {
                            boolean endGame = false;
                            while (!endGame) {
                                String command = scanner.nextLine().trim().toLowerCase();

                                battleGameMenu(command);

                            }
                        } else {
                            console.invalidNumOfFlags();
                        }

                    } else {
                        console.notValidMode();
                    }

                } else if (logic.Duelyst.currentAccount.collectionMethods.getDeckByName(stringArray[2]) == null) {
                    console.deckNameNotFound();
                }
            } else if (string.equals("exit")) {
                logic.Duelyst.currentMenu = logic.MainMenu.getInstance();
            } else {
                console.invalidCommand();
            }
*/
        } else if (c1.equals("exit")) {
            Duelyst.currentMenu = MainMenu.getInstance();
        } else {
            console.invalidCommand();
        }
    }

    static void battleGameMenu(Battle battle, Scanner scanner) {
        boolean flag = true;
        while (flag) {
            String command = scanner.nextLine().trim().toLowerCase();
            String[] commandArray = command.split("\\s+");
            if (command.matches("game(\\s+)info")) {
                if (battle.flagsNumber == 0) {
                    battle.player.gameInfo1();
                } else if (battle.flagsNumber == 1) {
                    battle.player.gameInfo2();
                } else {
                    battle.player.gameInfo3();
                }
            } else if (command.matches("show(\\s+)my(\\s+)minions")) {
                battle.player.showMyMinions();
                //console.showMyMinions(logic.Duelyst.currentAccount);
            } else if (command.matches("show(\\s+)opponent(\\s+)minions")) {
                battle.player.showOpponentMinions();
                //console.showOpponentMinions();
            } else if (command.matches("show card info (\\d+)")) {
                battle.player.showCardInfo(Integer.parseInt(commandArray[3]));
/*            if (logic.Duelyst.currentAccount.shopMethods.getCardByIdInCollection(Integer.parseInt(commandArray[3]))!=null)
            console.cardInfo(commandArray[3]);
            else {
                console.cardNotFound();
            }*/
            } else if (command.matches("select (\\d+)")) {
                int cardId = Integer.parseInt(commandArray[1]);
                String command1 = scanner.nextLine().toLowerCase().trim();
                String[] command1Array = command1.split("\\s");
                battle.player.select(cardId);
                Force force = (Force) (battle.player.selectedCard);
                boolean x = true;
                while (x) {
                    if (command1.matches("move to [1-5] [1-9]")) {
                        battle.player.move(force.cell, battle.field.cells[Integer.parseInt(command1Array[2]) - 1][Integer.parseInt(command1Array[3]) - 1]);
                    } else if (command1.matches("attack [\\d]+")) {
                        battle.player.attack(force.cell, battle.field.cells[Integer.parseInt(command1Array[2]) - 1][Integer.parseInt(command1Array[3]) - 1]);
                    } else if (command1.matches("exit")) {
                        battle.player.selectedCard = null;
                        x = false;
                    }
                }
            } else if (command.matches("attack combo (\\d+)( (\\d+))*")) {
                Force force = (Force) battle.secondPlayer.account.shopMethods.getCardBySerialInCollection(Integer.parseInt(commandArray[1]));
                Cell cell1 = force.cell;
                Cell[] cells = new Cell[commandArray.length - 3];
                if (cell1 != null) {
                    for (int i = 3; i < commandArray.length; i++) {
                        cells[i] = ((Force) battle.firstPlayer.account.shopMethods.getCardBySerialInCollection(Integer.parseInt(commandArray[i]))).cell;
                    }
                    battle.player.comboAttack(cell1, cells);
                }
            } else if (command.matches("use special power [1-5] [1-9]")) {
                battle.player.specialPower(battle.field.cells[Integer.parseInt(commandArray[2]) - 1][Integer.parseInt(commandArray[3]) - 1]);
            } else if (command.matches("show hand")) {
                battle.player.showHand();
            } else if (command.matches("insert [a-z]+ in [1-5] [1-9]")) {
                Card card = battle.firstPlayer.account.shopMethods.getCardByNameInCollection(commandArray[1]);
                if (card instanceof Minion)
                    battle.player.deploy((Force) card, battle.field.cells[Integer.parseInt(commandArray[3]) - 1][Integer.parseInt(commandArray[4]) - 1]);
                else
                    battle.player.deploy((SpellCard) card, battle.field.cells[Integer.parseInt(commandArray[3]) - 1][Integer.parseInt(commandArray[4]) - 1]);
            } else if (command.matches("end turn")) {
                battle.player.endTurn();
                flag = false;
            } else if (command.matches("show collectibles")) {
                battle.player.showCollectibles();
            } else if (command.matches("select collectible (\\d+)")) {
                String command1 = scanner.nextLine().trim().toLowerCase();
                String[] command1Array = command1.split("\\s");
                boolean x = true;
                while (x) {
                    if (command1.matches("show info")) {
                        battle.player.showItemInfo(Integer.parseInt(commandArray[1]));
                    } else if (command1.matches("use [1-5] [1-9]")) {
                        battle.player.useItem(Integer.parseInt(commandArray[1]),battle.field.cells[Integer.parseInt(command1Array[1]) - 1][Integer.parseInt(command1Array[2]) - 1]);
                    } else if (command1.matches("exit")) {
                        x = false;
                    }
                }
            } else if (command.matches("show next card")) {
                battle.player.showNextCard();
            } else if (command.matches("enter graveyard")) {
                String command1 = scanner.nextLine().trim().toLowerCase();
                String[] command1Array = command1.split("\\s");
                boolean x = true;
                while (x) {
                    if (command1.matches("show info (\\d+)")) {
                        battle.player.showInfoInGraveYard(Integer.parseInt(command1Array[2]));
                    } else if (command1.matches("show cards")) {
                        battle.player.showCardsInGraveYard();
                    } else if (command1.matches("exit")) {
                        x = false;
                    }
                }
            } else if (command.matches("conceit")) {
                battle.player.conceit();
            }else if (command.matches("ettelaat")){
                battle.player.showField();
            }
            else if (command.matches("help")) {

            }  else if (command.matches("exit")) {
                Duelyst.currentMenu=MainMenu.getInstance();
            }
        }
    }
}