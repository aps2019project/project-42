import java.util.ArrayList;

class Collection{
    ArrayList<Card> cards;
    void show(){

    }
    void search(){

    }
    void save(){

    }
    void createDeck(){

    }
    void removeDeck(){

    }
    void addToDeck(){

    }
    void removeFromDeck(){

    }
    void validateDeck(){

    }
    void selectMainDeck(){

    }
    void showAllDecks(){

    }
    void showDeck(){

    }
    void help(){
        System.out.println("to show your collection: show");
        System.out.println("to find a card id in your cards: search [card name]");
        System.out.println("to find an item id in your items: search [item name]");
        System.out.println("to create a deck: create deck [your selective name for deck]");
        System.out.println("to delete a deck: delete deck [the name of the deck you want to delete]");
        System.out.println("to add a card to a deck: add [card id] to deck [deck name]");
        System.out.println("to add an item to a deck: add [item id] to deck [deck name]");
        System.out.println("to remove a card from a deck: remove [card id] from deck [deck name]");
        System.out.println("to remove an item from a deck: remove [item id] from deck [deck name]");
        System.out.println("to check validity of a deck (have exactly 20 cards and 1 hero): validate deck [deck name]");
        System.out.println("to select a deck to be main deck: select deck [deck name]");
        System.out.println("to show cards and items in a deck: show deck [deck name]");
        System.out.println("to save changes: save");
        System.out.println("to return to main menu: exit");

    }
    void exit(){

    }
}
