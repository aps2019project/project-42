import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();
    private Account account = Duelyst.currentAccount;

    void buyCard(String string) {
        Card card = Card.getCardByName(string);
        if (card == null) {
            console.cardNotInShop();
        } else if (card.price > Duelyst.currentAccount.money) {
            console.insufficientMoney();
        } else if (Duelyst.getAllItems().contains(card) && Duelyst.currentAccount.itemCounter == 3) {
            console.cantBuyItem();
        } else {
            if (Duelyst.getAllHeroes().contains(card)) {
                Duelyst.currentAccount.getAccountHeroes().add((Hero) card);
            } else if (Duelyst.getAllItems().contains(card)) {
                Duelyst.currentAccount.getAccountItems().add((Item) card);
                Duelyst.currentAccount.itemCounter++;
            } else if (Duelyst.getAllMinions().contains(card)) {
                Duelyst.currentAccount.getAccountMinions().add((Minion) card);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                Duelyst.currentAccount.getAccountSpellCards().add((SpellCard) card);
            }
            card.owner = Duelyst.currentAccount.owner;
            Duelyst.currentAccount.money -= card.price;
            console.cardAdded(Duelyst.currentAccount.money);
        }
    }


    void sellCard(String string) {
        Card card = getCardByNameInCollection(string);
        if (card == null) {
            console.cardNotFound();
        } else {
            if (Duelyst.getAllHeroes().contains(card)) {
                Duelyst.currentAccount.getAccountHeroes().remove(card);
            } else if (Duelyst.getAllItems().contains(card)) {
                Duelyst.currentAccount.getAccountItems().remove(card);
            } else if (Duelyst.getAllMinions().contains(card)) {
                Duelyst.currentAccount.getAccountMinions().remove(card);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                Duelyst.currentAccount.getAccountSpellCards().remove(card);
            }
            for (Deck deck : Duelyst.currentAccount.decks) {
                if (deck.cards.contains(card)){
                    deck.cards.remove(card);
                }
            }
            Duelyst.currentAccount.money += card.price;
            console.sold(Duelyst.currentAccount.money);
        }
    }

    void showCollection() {
        console.showCollection(Duelyst.currentAccount);
    }

    void showShop() {
        console.showShop();
    }

    void searchShop(String string) {
        Card card = Card.getCardByName(string);
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
        Card card = getCardByNameInCollection(string);
        if (card!=null) {
            console.print(card.ID);
        } else {
            console.cardNotInShop();
        }
    }

    /*boolean containingInCollection(Card card) {
        return account.getAccountHeroes().contains(card) || account.getAccountItems().contains(card) || account.getAccountMinions().contains(card) || account.getAccountSpellCards().contains(card);
    }*/
    public Card getCardByNameInCollection(String name) {
        for (Hero hero : Duelyst.currentAccount.accountHeroes) {
            if (hero.name.equals(name)) {
                return hero;
            }
        }
        for (SpellCard spell : Duelyst.currentAccount.getAccountSpellCards()) {
            if (spell.name.equals(name)) {
                return spell;
            }
        }
        for (Minion minion : Duelyst.currentAccount.getAccountMinions()) {
            if (minion.name.equals(name)) {
                return minion;
            }
        }
        for (Item item : Duelyst.currentAccount.getAccountItems()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    /*boolean containingInShop(String string) {
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
    }*/
}
