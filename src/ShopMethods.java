import java.util.ArrayList;

public class ShopMethods {
    Account account = Duelyst.currentAccount;
    ArrayList<Card> cards;

    void buyCard(String string) {
        Card card = new Card(string);
        if (!cards.contains(card)) {
            System.out.println("This card doesn't exist in shop.");
        } else if (card.price > account.money) {
            System.out.println("You don't have enough money.");
        } //else if (numOfItemsCheck)
        else if (account.cards.contains(card)) {
            System.out.println("This card already exists in your collection");
        } else {
            account.cards.add(card);
            card.owner = account.owner;
            account.money -= card.price;
            System.out.println("This card added to your collection successfully.");
        }
    }

    void sellCard(String string) {
        Card card = new Card(string);
        if (!account.cards.contains(card)) {
            System.out.println("This card doesn't exist in your collection.");
        } else {
            account.cards.remove(card);
            account.money += card.price;
            System.out.println("You sold this cart successfully.");
        }
    }

    void showCollection() {
        for (Card card : account.cards) {
            System.out.println();
        }
    }

    void showShop() {
        for (Card card : cards) {
            System.out.println();
        }
    }

    void searchShop(String string) {
        Card card = new Card(string);
        if (cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            System.out.println("This card doesn't exist in shop.");
        }
    }

    void searchCollection(String string) {
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            System.out.println(card.ID);
        } else {
            System.out.println("This card doesn't exist in shop.");
        }
    }
}
