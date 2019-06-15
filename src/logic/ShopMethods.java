package logic;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();

    public void buyCard(String string) {
        Card card = Card.getCardByName(string);
        if (card == null) {
            Alert buy = new Alert(Alert.AlertType.INFORMATION);
            buy.setContentText("Card not found!");
            buy.show();
        } else if (card.price > Duelyst.currentAccount.money) {
            Alert buy = new Alert(Alert.AlertType.INFORMATION);
            buy.setContentText("Not enough money!");
            buy.show();
        } else if (Duelyst.getAllItems().contains(card) && Duelyst.currentAccount.itemCounter == 3) {
            Alert buy = new Alert(Alert.AlertType.INFORMATION);
            buy.setContentText("You can't buy item anymore");
            buy.show();
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
            Duelyst.currentAccount.money -= card.price;
            Alert buy = new Alert(Alert.AlertType.INFORMATION);
            buy.setContentText("This card added to your collection successfully.\nYour remaining money : " + Duelyst.currentAccount.money);
            buy.show();
        }
    }


    public void sellCard(String string) {
        Card card = getCardByNameInCollection(string);
        if (card == null) {
            Alert sell = new Alert(Alert.AlertType.INFORMATION);
            sell.setContentText("Card not found!");
            sell.show();
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
            if (Duelyst.currentAccount.mainDeck != null && Duelyst.currentAccount.mainDeck.cards.contains(card)) {
                Duelyst.currentAccount.mainDeck = null;
                Alert sell = new Alert(Alert.AlertType.INFORMATION);
                sell.setContentText("You sold this cart successfully.\nYour remaining money : " + Duelyst.currentAccount.money + "\nYour deck isn't valid anymore.");
                sell.show();
            } else {
                Alert sell = new Alert(Alert.AlertType.INFORMATION);
                sell.setContentText("You sold this cart successfully.\nYour remaining money : " + Duelyst.currentAccount.money);
                sell.show();
            }
            Duelyst.currentAccount.money += card.price;
        }
    }

    void showCollection() {
        console.showCollection(Duelyst.currentAccount);
    }

    void showShop() {
        console.showShop();
    }

    public void searchShop(String string) {
        Card card = Card.getCardByName(string);
        if (Duelyst.getAllHeroes().contains(card) || Duelyst.getAllItems().contains(card) || Duelyst.getAllMinions().contains(card) || Duelyst.getAllSpellCards().contains(card)) {
            Alert id = new Alert(Alert.AlertType.INFORMATION);
            id.setContentText("Card ID is : " + card.ID);
            id.show();
        } else {
            Alert idNotFound = new Alert(Alert.AlertType.INFORMATION);
            idNotFound.setContentText("Card not found!");
            idNotFound.show();
        }
    }

    public void searchCollection(String string) {
        Card card = getCardByNameInCollection(string);
        if (card != null) {
            Alert id = new Alert(Alert.AlertType.INFORMATION);
            id.setContentText("Card ID is : " + card.ID);
            id.show();
        } else {
            Alert idNotFound = new Alert(Alert.AlertType.INFORMATION);
            idNotFound.setContentText("Card not found!");
            idNotFound.show();
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
