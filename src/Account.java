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
}
