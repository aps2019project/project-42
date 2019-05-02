import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();
    Account account = Duelyst.currentAccount;
    ArrayList<Card> cards;

    void buyCard(String string) {
        Card card = new Card(string);
        if (!cards.contains(card)) {
            console.cardNotInShop();
        } else if (Integer.parseInt(card.price) > account.money) {
            console.insufficientMoney();
        } //else if (numOfItemsCheck)
        else if (account.cards.contains(card)) {
            console.cardInCollection();
        } else {
            account.cards.add(card);
            card.owner = account.owner;
            account.money -= Integer.parseInt(card.price);
            console.cardAdded();
        }
    }

    void sellCard(String string) {
        Card card = new Card(string);
        if (!account.cards.contains(card)) {
            console.cardNotFound();
        } else {
            account.cards.remove(card);
            account.money += Integer.parseInt(card.price);
            console.sold();
        }
    }

    void showCollection() {
        console.showCollection();
    }

    void showShop() {
        console.showShop();
    }

    void searchShop(String string) {
        Card card = new Card(string);
        if (cards.contains(card)) {
            console.print(card.ID);
        } else {
            console.cardNotInShop();
        }
    }

    void searchCollection(String string) {
        Card card = new Card(string);
        if (account.cards.contains(card)) {
            console.print(card.ID);
        } else {
            console.cardNotInShop();
        }
    }
}
