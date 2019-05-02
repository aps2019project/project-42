import java.util.*;

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
                showMenu();
            } else if (command.matches("create(\\s+)account(\\s+)[0-9a-z]+")) {
                boolean check = isCheck(commandArray[2]);
                if (check) {
                    System.out.println("Please enter your password to create your account.");
                    String pass = scanner.nextLine();
                    createAccount(commandArray[2], pass);
                }
            } else if (command.matches("login(\\s+)[0-9a-z]+")) {
                System.out.println("Please enter your password to enter your account.");
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

    private boolean isCheck(String anObject) {
        boolean check = true;
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(anObject)) {
                System.out.println("This username already exists.");
                check = false;
            }
        }
        return check;
    }

    private void showMenu() {
        System.out.println("login\ncreate account\nshow leader board\nsave\nlogout\nhelp\nexit");
    }


    void createAccount(String user, String pass) {
        Account account = new Account(user, pass);
        Duelyst.wins.putIfAbsent(user, 0);
        account.setUser(user);
        account.setPass(pass);
        Duelyst.accounts.add(account);
        Duelyst.currentMenu = MainMenu.getInstance();
        System.out.println("welcome!");
    }

    void login(String user, String pass) {
        Account account = new Account(user, pass);
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user) && account1.getPass().equals(pass)) {
                Duelyst.currentMenu = MainMenu.getInstance();
                System.out.println("welcome!");
            } else {
                System.out.println("Invalid username or password.");
            }
        }
    }

    void showLeaderBoard() {
        Map<String, Integer> w = sortByValue(Duelyst.wins);
        int counter = 1;
        for (Map.Entry<String, Integer> x : w.entrySet()) {
            System.out.println(counter + " - username : " + x.getKey() + " - wins : " + x.getValue());
            counter++;
        }
    }

    void save() {
    }

    void logOut() {
        Duelyst.currentMenu = AccountPage.getInstance();
        System.out.println("You logged out.");
    }

    boolean validAccount(String user, String pass) {
        return true;
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> wins) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(wins.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    void help() {
        System.out.println("to creat an account: create account [your selective username]");
        System.out.println("login: login [your username]");
        System.out.println("to see leaderBoard sorted by number of wins: show leaderboard");
        System.out.println("to save your account: save");
        System.out.println("logout: logout");
    }

    void quit() {
        Duelyst.finishGame = true;
    }
}
