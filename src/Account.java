import java.util.ArrayList;

public class Account {
    String user;
    private String pass;
    Player owner;
    ArrayList<MatchHistory> matchList;
    ArrayList<Deck> decks=new ArrayList<>();
    //ArrayList<Card> cards;
    ArrayList<Hero> accountHeroes=new ArrayList<>();
    ArrayList<Item> accountItems=new ArrayList<>();
    ArrayList<Minion> accountMinions=new ArrayList<>();
    ArrayList<SpellCard> accountSpells=new ArrayList<>();
    Deck mainDeck;
    int mainDeckIndex;
    int money=150000000;
    int itemCounter=0;
    CollectionMethods collectionMethods = new CollectionMethods();
    ShopMethods shopMethods=new ShopMethods();
    Battle battle=new Battle();

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public ArrayList<Hero> getAccountHeroes() {
        return accountHeroes;
    }

    public ArrayList<Item> getAccountItems() {
        return accountItems;
    }

    public ArrayList<Minion> getAccountMinions() {
        return accountMinions;
    }

    public ArrayList<SpellCard> getAccountSpellCards() {
        return accountSpells;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    Account(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }


}
