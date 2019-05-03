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
        System.out.println("to creat an account: create account [your selective username]");
        System.out.println("login: login [your username]");
        System.out.println("to see leaderBoard sorted by number of wins: show leaderboard");
        System.out.println("to save your account: save");
        System.out.println("logout: logout");
    }

    void collectionMenu() {
        System.out.println("show collection\nsearch in collection\ncreate deck\ndelete deck\nremove from a deck\ndeck validation\nchoose main deck\nshow decks\nshow a single deck\nsave\nhelp\nexit");
    }

    public void showCollection() {
    }

    void print(int id) {
        System.out.println(id);
    }

    void cardNotFound() {
        System.out.println("This cart doesn't exist in your collection");
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

    void deckDeleted() {
        System.out.println("Deck deleted successfully.");
    }

    public void addCardToDeck() {
        System.out.println("Card added to deck successfully.");
    }

    void cardExists() {
        System.out.println("This card already exists in this deck.");
    }

    void deleteCardFromDeck() {
        System.out.println("Card deleted from deck successfully.");
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

    void showDeckWithMainDeck() {
    }

    void showDeckWithoutMainDeck() {
    }

    void showSingleDeck() {
    }

    void collectionHelp() {
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

    void shopMenu() {
        System.out.println("show collection\nserach in collection\nshow shop\nsearch in shop\nbuy\nsell\nhelp\nexit");
    }

    void showShop() {
        int counter = 1;
        System.out.println("Heroes :");
        for (Hero hero : Card.getAllHeroes()) {
            System.out.print(counter);
            System.out.println(hero);
            counter++;
        }
        counter = 1;
        System.out.println("Items :");
        for (Item item : Card.getAllItems()) {
            System.out.print(counter);
            System.out.println(item);
            counter++;
        }
        counter = 1;
        System.out.println("Cards :");
        System.out.println("Spells :");
        for (SpellCard spellCard : Card.getAllSpells()) {
            System.out.print(counter);
            System.out.println(spellCard);
            counter++;
        }
        counter = 1;
        System.out.println("Minions :");
        for (Minion minion : Card.getAllMinions()) {
            System.out.print(counter);
            System.out.println(minion);
            counter++;
        }
    }

    void cardNotInShop() {
        System.out.println("This card doesn't exist in shop.");
    }

    void insufficientMoney() {
        System.out.println("You don't have enough money.");
    }

    void cardInCollection() {
        System.out.println("This card already exists in your collection");
    }

    void cardAdded() {
        System.out.println("This card was added to your collection successfully.");
    }

    void sold() {
        System.out.println("You sold this cart successfully.");
    }

    void shopHelp() {
        System.out.println("to show your cards: show collection");
        System.out.println("to find a card id in shop: search [card name]");
        System.out.println("to find an item id in shop: search [item name]");
        System.out.println("to find a card id in your collection: search collection [card name]");
        System.out.println("to find an item id in your collection: search collection [item name]");
        System.out.println("to buy a card: buy [card name]");
        System.out.println("to buy an item: buy [item name]");
        System.out.println("to sell your card: sell [card name]");
        System.out.println("to sell your item: sell [item name]");
        System.out.println("to see shop: show");
        System.out.println("to return to mainMenu: exit");
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

    void invalidCommand(){
        System.out.println("invalid command");
    }

    void showMainMenu(){
        System.out.println("Collection\nShop\nBatlle\nExit\nHelp");
    }

    void showMainHelp(){
        System.out.println("to manage your cards: enter collection");
        System.out.println("to buy or sell cards and items: enter shop");
        System.out.println("to play game: enter battle");
        System.out.println("to exit the game: exit");
    }
}