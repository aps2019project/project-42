import java.util.Scanner;

public class BattleFirstMenu extends Menu {
    Scanner scanner=new Scanner(System.in);
    Console console=Console.getInstance();
    private static BattleFirstMenu battleFirstMenu=new BattleFirstMenu();
    public static BattleFirstMenu getInstance(){
        return battleFirstMenu;
    }

    void battleMenu(String command){
        Battle battle=Duelyst.currentAccount.battle;
        try {
            //String[] commandArray = command.split("\\s+");
            if (command.equals("single player")){
                String c1=scanner.nextLine();
                if (c1.equals("story")){
                    console.storyGame();
                } else if (c1.equals("costume game")){
                    console.chooseHero();
                    Duelyst.currentAccount.collectionMethods.showAllDecks();
                    String c2=scanner.nextLine();
                    console.chooseMood();
                    console.moods();
                    String c3=scanner.nextLine();
                    if (c3.matches("start game [0-9a-z]+ mode[123] (\\d)")){

                    } else {
                        console.invalidCommand();
                    }
                } else {
                    console.invalidCommand();
                }
            } else if(command.equals("multi player")){
                console.chooseOpponent();
                for (Account account:Duelyst.accounts){
                    console.showUser(account);
                }
                String c1=scanner.nextLine();
                for (Account account:Duelyst.accounts){
                    if (account.getUser().equals(c1) && !Duelyst.currentAccount.getUser().equals(c1)){
                        if (account.mainDeck==null){
                            console.invalidMainDeck();
                            break;
                        }
                        console.chooseMood();
                        String c2=scanner.nextLine();
                        if (c2.matches("start multiplayer game [123] (\\d)")){

                        }
                    }
                }
            } else {
                console.invalidCommand();
            }
        }catch (NullPointerException e){
            e.getMessage();
        }
    }
}
