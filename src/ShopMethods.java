import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();
    Account account = Duelyst.currentAccount;

    void buyCard(String string) {
        Card card = new Card(string);
        if (!containingInShop(card)) {
            console.cardNotInShop();
        } else if (card.price > account.money) {
            console.insufficientMoney();
        } else if (card.price == 0) {
            console.collectibleItem();
        } //else if (numOfItemsCheck)
        else if (containingInCollection(card)) {
            console.cardInCollection();
        } else {
            if (Card.getAllHeroes().contains(card)) {
                account.getAccountHeroes().add((Hero) card);
            } else if (Card.getAllItems().contains(card)) {
                account.getAccountItems().add((Item) card);
            } else if (Card.getAllMinions().contains(card)) {
                account.getAccountMinions().add((Minion) card);
            } else if (Card.getAllSpells().contains(card)) {
                account.getAccountSpells().add((SpellCard) card);
            }
        }
        card.owner = account.owner;
        account.money -= card.price;
        console.cardAdded();
    }


    void sellCard(String string) {
        Card card = new Card(string);
        if (!containingInCollection(card)) {
            console.cardNotFound();
        } else if (card.price == 0) {
            console.collectibleItem();
        } else {
            if (Card.getAllHeroes().contains(card)) {
                account.getAccountHeroes().remove(card);
            } else if (Card.getAllItems().contains(card)) {
                account.getAccountItems().remove(card);
            } else if (Card.getAllMinions().contains(card)) {
                account.getAccountMinions().remove(card);
            } else if (Card.getAllSpells().contains(card)) {
                account.getAccountSpells().remove(card);
            }
            account.money += card.price;
            console.sold();
        }
    }

    void showCollection() {
        console.showCollection(account);
    }

    void showShop() {
        console.showShop();
    }

    void searchShop(String string) {
        Card card = new Card(string);
        if (Card.getAllHeroes().contains(card)) {
            console.print(card.ID);
        } else if (Card.getAllItems().contains(card)) {
            console.print(card.ID);
        } else if (Card.getAllMinions().contains(card)) {
            console.print(card.ID);
        } else if (Card.getAllSpells().contains(card)) {
            console.print(card.ID);
        } else {
            console.cardNotInShop();
        }

    }

    void searchCollection(String string) {
        Card card = new Card(string);
        if (containingInCollection(card)) {
            console.print(card.ID);
        } else {
            console.cardNotInShop();
        }
    }

    boolean containingInCollection(Card card) {
        return account.getAccountHeroes().contains(card) || account.getAccountItems().contains(card) || account.getAccountMinions().contains(card) || account.getAccountSpells().contains(card);
    }

    boolean containingInShop(Card card) {
        return Card.getAllHeroes().contains(card) || Card.getAllItems().contains(card) || Card.getAllMinions().contains(card) || Card.getAllSpells().contains(card);
    }
}
