import java.util.Scanner;

class AccountPage extends Menu {

    Console console = Console.getInstance();

    public static AccountPage accountPage = new AccountPage();

    public static AccountPage getInstance() {
        return accountPage;
    }

    public Scanner scanner = Main.scanner;

    void accountPageMenu(String command) {
        try {
            String[] commandArray = command.split("\\s+");
            if (command.matches("exit")) {
                quit();
            } else if (command.matches("show(\\s+)menu")) {
                showMenu();
            } else if (command.matches("create(\\s+)account(\\s+)[0-9a-z]+")) {
                console.getpassword1();
                String pass = scanner.nextLine();
                createAccount(commandArray[2], pass);
            } else if (command.matches("login(\\s+)[0-9a-z]+")) {
                console.getpassword2();
                String pass = scanner.nextLine();
                login(commandArray[1], pass);
            } else if (command.matches("show(\\s+)leaderboard")) {
                showLeaderBoard();
            } else if (command.matches("save")) {
                save();
            } else if (command.matches("logout")) {
                logOut();
            } else if (command.matches("help")) {
                help();
            } else {
                System.out.println("Invalid command");
            }

        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    private void showMenu() {
        console.showBegMenu();
    }

    public void createAccount(String user, String pass) {
        Account account = new Account(user, pass);
        Account account1 = new Account(user);
        if (Duelyst.accounts.contains(account1)) {
            console.userExists();
        } else {
            Duelyst.accounts.add(account);
            Duelyst.currentMenu = MainMenu.getInstance();
            console.welcome();
        }
        /*for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user)) {
                check = false;
                break;
            }
        }*/
        /*if (check){
            System.out.println("hi");
        }*/
    }

    void login(String user, String pass) {
        Account account = new Account(user, pass);
        if (Duelyst.accounts.contains(account)){
            Duelyst.currentMenu = MainMenu.getInstance();
            console.welcome();
        } else {
            console.loginError();
        }
        /*for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user) && account1.getPass().equals(pass)) {
                Duelyst.currentMenu = MainMenu.getInstance();
                System.out.println("welcome!");
                break;
            }
        }*/
    }

    void showLeaderBoard() {

    }

    void save() {

    }

    void logOut() {
        Duelyst.currentMenu = AccountPage.getInstance();
        console.logoutMessage();
    }

    boolean validAccount(String user, String pass) {
        return true;
    }

    void help() {
        console.begHelp();
    }

    void quit() {
        Duelyst.finishGame=true;
    }
}
