package logic;

class Console {
    private static Console singleInstance = null;

    public static Console getInstance() {
        if (singleInstance == null) {
            singleInstance = new Console();
        }
        return singleInstance;
    }


    void getpassword1() {
        System.out.println("Please enter your password to create your account.");
    }

    void getpassword2() {
        System.out.println("Please enter your password to enter your account.");
    }

    void showBegMenu() {
        System.out.println("login\ncreate account\nshow leader board\nsave\nlogout\nhelp\nexit");
    }

    void userExists() {
        System.out.println("This username already exists.");
    }

    void welcome() {
        System.out.println("Welcome");
    }

    void loginError() {
        System.out.println("Invalid username or password.");
    }

    void logoutMessage() {
        System.out.println("You logged out.");
    }

    void begHelp() {
        System.out.println("logic.Account page commands:");
        System.out.println("to creat an account: create account [your selective username]");
        System.out.println("login: login [your username]");
        System.out.println("to see leaderBoard sorted by number of wins: show leaderboard");
        System.out.println("to save your account: save");
        System.out.println("logout: logout");
    }

    void collectionMenu() {
        System.out.println("logic.Collection:\nshow collection\nsearch in collection\ncreate deck\ndelete deck\nremove from a deck\ndeck validation\nchoose main deck\nshow decks\nshow a single deck\nsave\nhelp\nexit");
    }

    public void showCollection(Account account) {
        int counter = 1;
        System.out.println("heroes :");
        if (account.getAccountHeroes() != null) {
            for (Hero hero : account.getAccountHeroes()) {
                System.out.println(counter + "" + hero);
                counter++;
            }
        }
        System.out.println("item :");
        counter = 1;
        if (account.getAccountItems() != null) {
            for (Item item : account.getAccountItems()) {
                System.out.println(counter + "" + item);
                counter++;
            }
        }
        counter = 1;
        System.out.println("cards :");
        if (account.getAccountSpellCards() != null) {
            for (SpellCard spell : account.getAccountSpellCards()) {
                System.out.println(counter + "" + spell);
                counter++;
            }
        }
        if (account.getAccountMinions() != null) {
            for (Minion minion : account.getAccountMinions()) {
                System.out.println(counter + "" + minion);
                counter++;
            }
        }
    }

    void print(int id) {
        System.out.println(id);
    }

    void cardNotFound() {
        System.out.println("This cart doesn't exist in your collection.");
    }

    void deckAdded() {
        System.out.println("Deck added successfully.");
    }

    void deckExists() {
        System.out.println("A deck with this name already exists.");
    }

    void deckNameNotFound() {
        System.out.println("This deck doesn't exist in your collection.");
    }


    void deckHasHero() {
        System.out.println("Your deck has hero, you can't add another hero to it.");
    }

    void deckHasItem() {
        System.out.println("Your deck has item, you can't add another item to it.");
    }


    void deckDeleted() {
        System.out.println("Deck deleted successfully.");
    }

    void addCardToDeck() {
        System.out.println("logic.Card added to deck successfully.");
    }


    void deleteCardFromDeck() {
        System.out.println("logic.Card deleted from deck successfully.");
    }

    void cardNotInDeck() {
        System.out.println("This card doesn't exists in this deck.");
    }

    void validDeck() {
        System.out.println("Valid deck.");
    }

    void notValidDeck() {
        System.out.println("Invalid deck.");
    }

    void setMainDeck() {
        System.out.println("You set your main deck.");
    }

    void showDeckWithMainDeck(Account account) {
        int deckCounter = 1;
        int cardCounter = 1;
        System.out.println(deckCounter + " : " + account.mainDeck.name + " :");
        deckShowing(account.mainDeck, cardCounter);
        cardCounter = 1;
        deckCounter++;
        for (Deck deck : account.getDecks()) {
            if (deck.equals(account.mainDeck)) continue;
            System.out.println(deckCounter + " : " + deck.name + " :");
            deckShowing(deck, cardCounter);
            cardCounter = 1;
            deckCounter++;
        }
    }

    void showDeckWithoutMainDeck(Account account) {
        int deckCounter = 1;
        int cardCounter = 1;
        for (Deck deck : account.getDecks()) {
            System.out.println(deckCounter + " : " + deck.name + " :");
            deckShowing(deck, cardCounter);
            cardCounter = 1;
            deckCounter++;
        }

    }

    void showSingleDeck(Deck deck) {
        int cardCounter = 1;
        //System.out.println(deck.name + " :");
        deckShowing(deck, cardCounter);
    }

    private void deckShowing(Deck deck, int cardCounter) {
        /*//System.out.println("heroes :");
        for (Card card : deck.cards) {
            if (Duelyst.getAllHeroes().contains(card)) {
                Hero hero = (Hero) card;
                System.out.println(cardCounter + "" + hero);
                cardCounter++;
            }
        }
        cardCounter = 1;
        System.out.println("items :");
        for (Card card : deck.cards) {
            if (Duelyst.getAllItems().contains(card)) {
                Item item = (Item) card;
                System.out.println(cardCounter + "" + item);
                cardCounter++;
            }
        }
        cardCounter = 1;
        System.out.println("cards :");
        for (Card card : deck.cards) {
            if (Duelyst.getAllMinions().contains(card)) {
                Minion minion = (Minion) card;
                System.out.println(cardCounter + "" + minion);
                cardCounter++;
            }
        }
        for (Card card : deck.cards) {
            if (Duelyst.getAllSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                System.out.println(cardCounter + "" + spellCard);
                cardCounter++;
            }
        }*/


    }

    void collectionHelp() {
        System.out.println("logic.Collection commands:");
        System.out.println("To show your collection: show");
        System.out.println("To find a card id in your cards: search [card name]");
        System.out.println("To find an item id in your items: search [item name]");
        System.out.println("To create a deck: create deck [your selective name for deck]");
        System.out.println("To delete a deck: delete deck [the name of the deck you want to delete]");
        System.out.println("To add a card to a deck: add [card id] to deck [deck name]");
        System.out.println("To add an item to a deck: add [item id] to deck [deck name]");
        System.out.println("To remove a card from a deck: remove [card id] from deck [deck name]");
        System.out.println("To remove an item from a deck: remove [item id] from deck [deck name]");
        System.out.println("To check validity of a deck (have exactly 20 cards and 1 hero): validate deck [deck name]");
        System.out.println("To select a deck to be main deck: select deck [deck name]");
        System.out.println("To show cards and items in a deck: show deck [deck name]");
        System.out.println("To see your decks: show all decks");
        System.out.println("To save changes: save");
        System.out.println("To return to main menu: exit");
    }

    void shopMenu() {
        System.out.println("logic.Shop:\nshow collection\nsearch in collection\nshow shop\nsearch in shop\nbuy\nsell\nhelp\nexit");
    }

    void showShop() {
        int counter = 1;
        System.out.println("Heroes :");
        for (Hero hero : Duelyst.getAllHeroes()) {
            System.out.println(counter+""+hero);
            counter++;
        }
        counter = 1;
        System.out.println("Items :");
        for (Item item : Duelyst.getAllItems()) {
            System.out.println(counter+""+item);
            counter++;
        }
        counter = 1;
        System.out.println("Cards :");
        for (SpellCard spellCard : Duelyst.getAllSpellCards()) {
            System.out.println(counter+""+spellCard);
            counter++;
        }
        for (Minion minion : Duelyst.getAllMinions()) {
            System.out.println(counter+""+minion);
            counter++;
        }
    }

    void cardNotInShop() {
        System.out.println("This card doesn't exist in shop.");
    }

    void insufficientMoney() {
        System.out.println("You don't have enough money.");
    }

    void cardAdded(int money) {
        System.out.println("This card added to your collection successfully. Your remaining money : "+money);
    }

    void sold(int money) {
        System.out.println("You sold this cart successfully. Your remaining money : "+money);
    }

    void shopHelp() {
        System.out.println("logic.Shop commands:");
        System.out.println("To show your cards: show collection");
        System.out.println("To find a card id in shop: search [card name]");
        System.out.println("To find an item id in shop: search [item name]");
        System.out.println("To find a card id in your collection: search collection [card name]");
        System.out.println("To find an item id in your collection: search collection [item name]");
        System.out.println("To buy a card: buy [card name]");
        System.out.println("To buy an item: buy [item name]");
        System.out.println("To sell your card: sell [card id]");
        System.out.println("To sell your item: sell [item id]");
        System.out.println("To see shop: show");
        System.out.println("To return to mainMenu: exit");
    }

    void notEnoughMana() {
        System.out.println("You don't have enough mana.");
    }

    void noOrigin() {
        System.out.println("You didn't choose your origin.");
    }

    void tooFar() {
        System.out.println("This distance is out of bond for your force.");
    }

    void filledHouse() {
        System.out.println("Your chosen destiny is occupied.");
    }

    void emptyDestination() {
        System.out.println("Your destination is empty for attacking.");
    }

    void invalidCommand() {
        System.out.println("Invalid command");
    }

    void showMainMenu() {
        System.out.println("logic.Collection\nlogic.Shop\nlogic.Battle\nExit\nHelp");
    }

    void showMainHelp() {
        //System.out.println("Main menu commands:");
        System.out.println("To manage your cards: enter collection");
        System.out.println("To buy or sell cards and items: enter shop");
        System.out.println("To play game: enter battle");
        System.out.println("To exit the game: exit");
    }

    public void cantBuyItem() {
        System.out.println("You have 3 items in your collection, you can't buy items anymore.");
    }

    public void creation() {
        System.out.println("Welcome. You can login to enter your account.");
    }

    public void savedAccount() {
        System.out.println("Your account saved successfully.");
    }

    public void collectionSave() {
        System.out.println("Your collection changes saved successfully.");
    }

    public void noDeck() {
        System.out.println("You don't have any deck in your account");
    }

    public void invalidMainDeck() {
        System.out.println("selected deck is invalid.");
    }

    public void chooseYourDeck() {
        System.out.println("choose your deck.");
    }

    public void chooseMood() {
        System.out.println("Choose your mode:");
    }

    public void moods() {
        System.out.println("mode1\nmode2\nmode3");
    }

    public void chooseOpponent() {
        System.out.println("choose your opponent");
    }

    public void showUser(Account account) {
        System.out.println(account.getUser());
    }

    public void singleCostumePlayer() {
        chooseYourDeck();
        chooseMood();
        moods();
    }

    public void notValidMode() {
        System.out.println("your chosen mode is not valid.");
    }

    public void notValidAcc() {
        System.out.println("You can't choose yourself as your opponent.");
    }

    public void invalidNumOfFlags() {
        System.out.println("The number of flags is invalid.");
    }

    public void enterShop() {
        System.out.println("You entered shop.");
    }

    public void enterCollection() {
        System.out.println("You entered collection.");
    }

    public void deckNotValidateAnymore() {
        System.out.println("Your main deck is not valid anymore.");
    }

    public void showMinions(Account account) {
        for (Minion minion : account.getAccountMinions()) {
            System.out.println(minion);
        }
    }

    public void storyGame(){
        System.out.println("Level 1: logic.Hero: white demon, mode: first mode");
        System.out.println("Level 2: logic.Hero: Zahak, mode: second mode");
        System.out.println("Level 3: logic.Hero: Arash, mode: third mode");
    }
}