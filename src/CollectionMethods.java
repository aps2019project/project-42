import java.util.ArrayList;

public class CollectionMethods {
    Account account = Duelyst.currentAccount;
    ArrayList<Card> cards;

    void show(){

    }
    void search(String string){
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            System.out.println("This cart doesn't exist in your collection");
        }
    } void save(){

    }
    void createDeck(String string){

    }
    void deleteDeck(String string){

    }
    void addToDeck(String str1,String str2){

    }
    void removeFromDeck(String str1,String str2){

    }
    boolean deckValidation(String string){

    }
    void validateDeck(String string){

    }
    void selectMainDeck(String string){

    }
    void showAllDecks(){

    }
    void showDeck(String string){

    }
}
