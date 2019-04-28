import java.util.Scanner;

class AccountPage {


    private Scanner scanner = Main.scanner;

    void accountPageMenu() {
        try {
            while (true) {
                String command = scanner.nextLine().toLowerCase().trim();
                String[] commandArray = command.split("\\s+");
                if (command.matches("exit")) {
                    quit();
                    break;
                } else if (command.matches("create(\\s+)account(\\s+)[0-9a-z]+")) {
                    System.out.println("Please enter your password to create your account.");
                    String pass = scanner.nextLine();
                    createAccount(commandArray[2], pass);
                    System.out.println("welcome!");
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
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }


    void createAccount(String user, String pass) {
        Account account=new Account(user,pass);
        Duelyst.accounts.add(account);
    }

    void login(String user, String pass) {
        Account account=new Account(user,pass);
        if (Duelyst.accounts.contains(account)){

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
