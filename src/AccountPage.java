import java.util.Scanner;

class AccountPage extends Menu {

    private static AccountPage accountPage = new AccountPage();

    public static AccountPage getInstance() {
        return accountPage;
    }

    private Scanner scanner = Main.scanner;

    void accountPageMenu(String command) {
        try {
            String[] commandArray = command.split("\\s+");
            if (command.matches("exit")) {
                quit();
            } else if (command.matches("show(\\s+)menu")) {
                System.out.println("login\ncreate account\nshow leader board\nsave\nlogout\nhelp\nexit");
            } else if (command.matches("create(\\s+)account(\\s+)[0-9a-z]+")) {
                System.out.println("Please enter your password to create your account.");
                String pass = scanner.nextLine();
                System.out.println("welcome!");
                createAccount(commandArray[2], pass);
            } else if (command.matches("login(\\s+)[0-9a-z]+")) {
                System.out.println("Please enter your password to enter your account.");
                String pass = scanner.nextLine();
                login(commandArray[1], pass);
                System.out.println("welcome!");
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


    void createAccount(String user, String pass) {
        Account account = new Account(user, pass);
        boolean check = true;
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user)) {
                System.out.println("This username already exists.");
                check = false;
                break;
            }
        }
        if (check) {
            Duelyst.accounts.add(account);

        }
    }

    void login(String user, String pass) {
        Account account = new Account(user, pass);
        boolean check = false;
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user) && account1.getPass().equals(pass)) {
                check = true;
                break;
            }
        }
    }

    void showLeaderBoard() {

    }

    void save() {

    }

    void logOut() {

    }

    boolean validAccount(String user, String pass) {
        return true;
    }

    void help() {
        System.out.println("to creat an account: create account [your selective username]");
        System.out.println("login: login [your username]");
        System.out.println("to see leaderBoard sorted by number of wins: show leaderboard");
        System.out.println("to save your account: save");
        System.out.println("logout: logout");
    }

    void quit() {

    }
}
