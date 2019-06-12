package logic;

import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();

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
            /*for (logic.Deck deck:logic.Duelyst.currentAccount.decks) {
                if (deck.cards.contains())
            }*/
            Duelyst.currentAccount.money -= card.price;
            console.cardAdded(Duelyst.currentAccount.money);
        }
    }


    void sellCard(int id) {
        Card card = getCardByIdInCollection(id);
        if (card == null) {
            console.cardNotFound();
        } else {
            ArrayList<Deck> tempAccountDecks = new ArrayList<>(Duelyst.currentAccount.getDecks());
            if (!tempAccountDecks.isEmpty()) {
                for (Deck deck : tempAccountDecks) {
                    if (deck.cards.contains(card)) {
                        if (Duelyst.getAllHeroes().contains(card)) {
                            deck.hero = null;
                            deck.numOfHeroes--;
                        } else if (Duelyst.getAllItems().contains(card)) {
                            deck.usable = null;
                            deck.numOfItems--;
                        } else if (Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card)) {
                            deck.numOfCards--;
                        }
                        deck.cards.remove(card);
                    }
                }
            }
            if (Duelyst.getAllHeroes().contains(card)) {
                Duelyst.currentAccount.getAccountHeroes().remove(card);
            } else if (Duelyst.getAllItems().contains(card)) {
                Duelyst.currentAccount.getAccountItems().remove(card);
            } else if (Duelyst.getAllMinions().contains(card)) {
                Duelyst.currentAccount.getAccountMinions().remove(card);
            } else if (Duelyst.getAllSpellCards().contains(card)) {
                Duelyst.currentAccount.getAccountSpellCards().remove(card);
            }
            Duelyst.currentAccount.money += card.price;
            console.sold(Duelyst.currentAccount.money);
            if (Duelyst.currentAccount.mainDeck != null && Duelyst.currentAccount.mainDeck.cards.contains(card)) {
                Duelyst.currentAccount.mainDeck = null;
                console.deckNotValidateAnymore();
            }
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
        if (card != null) {
            console.print(card.ID);
        } else {
            console.cardNotFound();
        }
    }

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

    public Card getCardByIdInCollection(int id) {
        for (Hero hero : Duelyst.currentAccount.getAccountHeroes()) {
            if (hero.ID == id) {
                return hero;
            }
        }
        for (SpellCard spell : Duelyst.currentAccount.getAccountSpellCards()) {
            if (spell.ID == id) {
                return spell;
            }
        }
        for (Minion minion : Duelyst.currentAccount.getAccountMinions()) {
            if (minion.ID == id) {
                return minion;
            }
        }
        for (Item item : Duelyst.currentAccount.getAccountItems()) {
            if (item.ID == id) {
                return item;
            }
        }
        return null;
    }
}
