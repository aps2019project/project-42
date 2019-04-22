import java.util.ArrayList;

public class Account {
    String user;
    String pass;
    Player owner;
    ArrayList<MatchHistory> matchList;
    ArrayList<Deck> decks;
    ArrayList<Card> cards;
    Deck mainDeck;
    int mainDeckIndex;
    int money;
    Account(String user){
        this.user=user;
    }
    Account(String user,String pass){
        this.user=user;
        this.pass=pass;
    }
}
