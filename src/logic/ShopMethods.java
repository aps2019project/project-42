package logic;

import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ShopMethods {
    Console console = Console.getInstance();

    public void buyCard(String string) throws FileNotFoundException {
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
            Card theCard = card.duplicate();
            theCard.serial = card.ID + "~" + Integer.toString(howManyCards(string) + 1) + "~" + Duelyst.currentAccount.user;
            if (theCard instanceof Hero) {
                Duelyst.currentAccount.getAccountHeroes().add((Hero) theCard);
            } else if (theCard instanceof Item) {
                Duelyst.currentAccount.getAccountItems().add((Item) theCard);
                Duelyst.currentAccount.itemCounter++;
            } else if (theCard instanceof Minion) {
                Duelyst.currentAccount.getAccountMinions().add((Minion) theCard);
            } else if (theCard instanceof SpellCard) {
                Duelyst.currentAccount.getAccountSpellCards().add((SpellCard) theCard);
            }
            Duelyst.currentAccount.money -= theCard.price;
            Alert buy = new Alert(Alert.AlertType.INFORMATION);
            buy.setContentText("This card added to your collection successfully.\nYour remaining money : " + Duelyst.currentAccount.money);
            buy.show();
        }
    }


    public void sellCard(String id) {
        String serial = id + "~" + howManyCards(id) + "~" + Duelyst.currentAccount.user;
        Card card = getCardBySerialInCollection(serial);
        if (card == null) {
            Alert sell = new Alert(Alert.AlertType.INFORMATION);
            sell.setContentText("Card not found!");
            sell.show();
        } else {
            ArrayList<Deck> tempAccountDecks = new ArrayList<>(Duelyst.currentAccount.getDecks());
            if (!tempAccountDecks.isEmpty()) {
                for (Deck deck : tempAccountDecks) {
                    if (deck.cards.contains(card)) {
                        if (card instanceof Hero) {
                            deck.hero = null;
                            deck.numOfHeroes--;
                        } else if (card instanceof Item) {
                            deck.usable = null;
                            deck.numOfItems--;
                        } else if (card instanceof Minion || card instanceof SpellCard) {
                            deck.numOfCards--;
                        }
                        deck.cards.remove(card);
                    }
                }
            }
            boolean b = true;
            if (b) {
                for (Card c : Duelyst.currentAccount.getAccountHeroes()) {
                    if (c.serial.equals(serial)) {
                        Duelyst.currentAccount.getAccountHeroes().remove(c);
                        b = false;
                        break;
                    }
                }
            }
            if (b) {
                for (Card c : Duelyst.currentAccount.getAccountItems()) {
                    if (c.serial.equals(serial)) {
                        Duelyst.currentAccount.getAccountItems().remove(c);
                        b = false;
                        break;
                    }
                }
            }
            if (b) {
                for (Card c : Duelyst.currentAccount.getAccountMinions()) {
                    if (c.serial.equals(serial)) {
                        Duelyst.currentAccount.getAccountMinions().remove(c);
                        b = false;
                        break;
                    }
                }
            }
            if (b) {
                for (Card c : Duelyst.currentAccount.getAccountSpellCards()) {
                    if (c.serial.equals(serial)) {
                        Duelyst.currentAccount.getAccountSpellCards().remove(c);
                        b = false;
                        break;
                    }
                }
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

    int howManyCards(int id) {
        int i = 0;
        for (Hero hero : Duelyst.currentAccount.accountHeroes) {
            if (hero.ID == id) {
                i++;
            }
        }
        for (SpellCard spell : Duelyst.currentAccount.getAccountSpellCards()) {
            if (spell.ID == id) {
                i++;
            }
        }
        for (Minion minion : Duelyst.currentAccount.getAccountMinions()) {
            if (minion.ID == id) {
                i++;
            }
        }
        for (Item item : Duelyst.currentAccount.getAccountItems()) {
            if (item.ID == id) {
                i++;
            }
        }
        return i;
    }

    int howManyCards(String name) {
        int i = 0;
        for (Hero hero : Duelyst.currentAccount.accountHeroes) {
            if (hero.name.equals(name)) {
                i++;
            }
        }
        for (SpellCard spell : Duelyst.currentAccount.getAccountSpellCards()) {
            if (spell.name.equals(name)) {
                i++;
            }
        }
        for (Minion minion : Duelyst.currentAccount.getAccountMinions()) {
            if (minion.name.equals(name)) {
                i++;
            }
        }
        for (Item item : Duelyst.currentAccount.getAccountItems()) {
            if (item.name.equals(name)) {
                i++;
            }
        }
        return i;
    }

    public Card getCardBySerialInCollection(String serial) {
        for (Hero hero : Duelyst.currentAccount.getAccountHeroes()) {
            if (hero.serial.equals(serial)) {
                return hero;
            }
        }
        for (SpellCard spell : Duelyst.currentAccount.getAccountSpellCards()) {
            if (spell.serial.equals(serial)) {
                return spell;
            }
        }
        for (Minion minion : Duelyst.currentAccount.getAccountMinions()) {
            if (minion.serial.equals(serial)) {
                return minion;
            }
        }
        for (Item item : Duelyst.currentAccount.getAccountItems()) {
            if (item.serial.equals(serial)) {
                return item;
            }
        }
        return null;
    }
}
