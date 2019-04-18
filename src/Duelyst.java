import java.util.ArrayList;
import java.util.Scanner;

class Duelyst{
    ArrayList<Account> accounts;
    ArrayList<Card> source;
    Scanner scanner=new Scanner(System.in);
    void mainMenu(Account account,Scanner scanner){
        switch (scanner.nextLine()){
            case("collection"): {
                break;
            }
            case("shop"): {
                break;
            }
            case("battle"):{
                break;
            }
            case ("exit"):{
                break;
            }
            case ("help"):{
                help();
                break;
            }
        }
    }

    private void help() {
        System.out.println("to manage your cards: collection");
        System.out.println("to buy or sell cards and items: shop");
        System.out.println("to play game: battle");
        System.out.println("to exit the game: exit");
    }

    void addToSource(Card card){

    }
    void start(Scanner scanner){

    }
}
