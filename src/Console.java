import java.util.Scanner;

class Console {
    Scanner scanner= new Scanner(System.in);
    private static Console singleInstance = null;
    public static Console getInstance(){
    if (singleInstance == null){
        singleInstance = new Console();
        }
        return singleInstance;
    }

    //Console(Scanner scanner) {
    //    String command = scanner.nextLine();
    //   String[] commands = command.split(" ");
    void getpassword1() {
        System.out.println("Please enter your password to create your account.");
    }
    void getpassword2(){
        System.out.println("Please enter your password to enter your account.");
    }
    void showBegMenu(){
        System.out.println("login\ncreate account\nshow leader board\nsave\nlogout\nhelp\nexit");
    }
    void userExists(){
        System.out.println("This username already exists.");
    }
    void welcome(){
        System.out.println("Welcome");
    }
    void loginError(){
        System.out.println("Invalid username or password.");
    }
    void logoutMessage(){
        System.out.println("You logged out.");
    }
    void begHelp(){
        System.out.println("to creat an account: create account [your selective username]");
        System.out.println("login: login [your username]");
        System.out.println("to see leaderBoard sorted by number of wins: show leaderboard");
        System.out.println("to save your account: save");
        System.out.println("logout: logout");
    }
    void collectionMenu(){
        System.out.println("show collection\nsearch in collection\ncreate deck\ndelete deck\nremove from a deck\ndeck validation\nchoose main deck\nshow decks\nshow a single deck\nsave\nhelp\nexit");
    }
    void cardNotFound(){
        System.out.println("This cart doesn't exist in your collection");
    }
    void deckExists(){
        System.out.println("A deck with this name already exists.");
    }
    void deckNameNotFound(){
        System.out.println("This deck name doesn't exist");
    }
    void deckNotFound(){
        System.out.println("This deck doesn't exist in your collection.");
    }
    void cardExists(){
        System.out.println("This card already exists in this deck.");
    }
    void cardNotInDeck(){
        System.out.println("This card doesn't exists in this deck.");
    }
    void validDeck(){
        System.out.println("Valid deck.");
    }
    void notValidDeck(){
        System.out.println("Invalid deck.");
    }
    void collectionHelp(){
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
    void shopMenu(){
        System.out.println("show collection\nserach in collection\nshow shop\nsearch in shop\nbuy\nsell\nhelp\nexit");
    }
    void cardNotInShop(){
        System.out.println("This card doesn't exist in shop.");
    }
    void insufficientMoney(){
        System.out.println("You don't have enough money.");
    }
    void cardInCollection(){
        System.out.println("This card already exists in your collection");
    }
    void cardAdded(){
        System.out.println("This card was added to your collection successfully.");
    }
    void sold(){
        System.out.println("You sold this cart successfully.");
    }
    void shopHelp(){
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
}