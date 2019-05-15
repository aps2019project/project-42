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
        //Battle battle = Duelyst.currentAccount.battle;
        //        String[] commandArray = command.split("\\s+");
        try {
            if (command.equals("single player")) {
                costumeGameSingle();
            } else if (command.equals("multi player")) {
                multiPlayer();
            } else if (command.equals("exit")) {
                Duelyst.currentMenu = MainMenu.getInstance();
            } else {
                console.invalidCommand();
            }
        } catch (NullPointerException e) {
            e.getMessage();
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
                    Battle battle = new Battle(GameType.multi, Integer.parseInt(c2Array[3]), Duelyst.currentAccount, account);
                    battleGameMenu(battle);
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
        if (c1.equals("story")) {
            console.storyGame();
        } else if (c1.equals("costume game")) {
            console.singleCostumePlayer();
            String string = scanner.nextLine();
            if (string.matches("start game [0-9a-z]+ mode[123] (\\d)")) {
                String[] stringArray = string.split("\\s");
                if (Duelyst.currentAccount.collectionMethods.getDeckByName(stringArray[2]) != null) {
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

                } else if (Duelyst.currentAccount.collectionMethods.getDeckByName(stringArray[2]) == null) {
                    console.deckNameNotFound();
                }
            } else if (string.equals("exit")) {
                Duelyst.currentMenu = MainMenu.getInstance();
            } else {
                console.invalidCommand();
            }
        } else if (c1.equals("exit")) {
            Duelyst.currentMenu = MainMenu.getInstance();
        } else {
            console.invalidCommand();
        }
    }

    void battleGameMenu(Battle battle) {
        while (battle.lasting) {
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
                //console.showMyMinions(Duelyst.currentAccount);
            } else if (command.matches("show(\\s+)opponent(\\s+)minions")) {
                battle.player.showOpponentMinions();
                //console.showOpponentMinions();
            } else if (command.matches("show card info (\\d+)")) {
                battle.player.showCardInfo(Integer.parseInt(commandArray[3]));
/*            if (Duelyst.currentAccount.shopMethods.getCardByIdInCollection(Integer.parseInt(commandArray[3]))!=null)
            console.cardInfo(commandArray[3]);
            else {
                console.cardNotFound();
            }*/
            } else if (command.matches("select (\\d+)")) {
                String command1 = scanner.nextLine().toLowerCase().trim();
                String[] command1Array = command1.split("\\s");
                battle.player.select(Integer.parseInt(command1Array[1]));
                boolean x = true;
                while (x) {
                    if (command1.matches("move to \\([0-8],[0-4]\\)")) {

                    } else if (command1.matches("attack [\\d]+")) {

                    } else if (command1.matches("attack combo [\\d]+ ([\\d]+)+")) {

                    } else if (command1.matches("exit")) {
                        battle.player.selectedCard = null;
                        x = false;
                    }
                }
            } else if (command.matches("use special power \\([0-8],[0-4]\\)")) {

            } else if (command.matches("show hand")) {
                battle.player.showHand();
            } else if (command.matches("insert [a-z]+ in \\([0-8],[0-4]\\)")) {

            } else if (command.matches("end turn")) {
                battle.player.endTurn();
            } else if (command.matches("show collectibles")) {
                battle.player.showCollectibles();
            } else if (command.matches("select [\\d]+")) {
                String command1 = scanner.nextLine().trim().toLowerCase();
                String[] command1Array = command1.split("\\s");
                boolean x = true;
                while (x) {
                    if (command1.matches("show info")) {
                        battle.player.showCollectibles();
                    } else if (command1.matches("use \\([0-8],[0-4]\\)")) {

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
            } else if (command.matches("help")) {

            } else if (command.matches("end game")) {

            } else if (command.matches("exit")) {

            }
        }
    }
}