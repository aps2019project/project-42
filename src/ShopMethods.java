import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();
    Account account = Duelyst.currentAccount;

    void buyCard(String string) {
        Card card = Card.getCardByName(string);
        System.out.println(card.price);
        System.out.println(account);
        //System.out.println(account.money);
        //System.out.println(card);
        if (card==null) {
            System.out.println(1);
            console.cardNotInShop();
        } else if (card.price > account.money) {
            System.out.println(2);
            console.insufficientMoney();
        } else if (Duelyst.getAllItems().contains(card) && account.itemCounter==3){
            System.out.println(3);
            console.cantBuyItem();
        }
        else {
            if (Duelyst.getAllHeroes().contains(card)) {
                System.out.println(4);
                account.getAccountHeroes().add((Hero) card);
            } else if (Duelyst.getAllItems().contains(card)) {
                System.out.println(5);
                account.getAccountItems().add((Item) card);
                account.itemCounter++;
            } else if (Duelyst.getAllMinions().contains(card)) {
                System.out.println(6);
                account.getAccountMinions().add((Minion) card);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                System.out.println(7);
                account.getAccountSpellCards().add((SpellCard) card);
            }
            card.owner = account.owner;
            account.money -= card.price;
            console.cardAdded(account.money);
        }
    }


    void sellCard(String string) {
        Card card = new Card(string);
        if (!containingInCollection(card)) {
            console.cardNotFound();
        } else if (card.price == 0) {
            console.collectibleItem();
        } else {
            if (Duelyst.getAllHeroes().contains(card)) {
                account.getAccountHeroes().remove(card);
            } else if (Duelyst.getAllItems().contains(card)) {
                account.getAccountItems().remove(card);
            } else if (Duelyst.getAllMinions().contains(card)) {
                account.getAccountMinions().remove(card);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                account.getAccountSpellCards().remove(card);
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
        if (Duelyst.getAllHeroes().contains(card)) {
            console.print(card.ID);
        } else if (Duelyst.getAllItems().contains(card)) {
            console.print(card.ID);
        } else if (Duelyst.getAllMinions().contains(card)) {
            console.print(card.ID);
        } else if (Duelyst.getAllSpellCards().contains(card)) {
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
        return account.getAccountHeroes().contains(card) || account.getAccountItems().contains(card) || account.getAccountMinions().contains(card) || account.getAccountSpellCards().contains(card);
    }

    boolean containingInShop(String string) {
        boolean exist = false;
        for (Hero hero : Duelyst.getAllHeroes()) {
            if (hero.name.equals(string))
                exist = true;
        }
        for (Item item : Duelyst.getAllItems()) {
            if (item.name.equals(string))
                exist = true;
        }
        for (Minion minion : Duelyst.getAllMinions()) {
            if (minion.name.equals(string))
                exist = true;
        }
        for (SpellCard spellCard : Duelyst.getAllSpellCards()) {
            if (spellCard.name.equals(string))
                exist = true;
        }
        return exist;
        //return Duelyst.getAllHeroes().contains(card) || Duelyst.getAllItems().contains(card) || Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card);
    }
}
