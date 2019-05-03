import java.util.*;

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
                boolean check = isCheck(commandArray[2]);
                if (check) {
                    console.getpassword1();
                    String pass = scanner.nextLine();
                    createAccount(commandArray[2], pass);
                }
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
                console.invalidCommand();
            }

        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    private boolean isCheck(String string) {
        boolean check = true;
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(string)) {
                console.userExists();
                check = false;
            }
        }
        return check;
    }

    private void showMenu() {
        console.showBegMenu();
    }


    public void createAccount(String user, String pass) {
        boolean check = true;
        Account account = new Account(user, pass);
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user)) {
                console.userExists();
                check = false;
            }
        }
        if (check) {
            Duelyst.wins.putIfAbsent(user, 0);
            account.setUser(user);
            account.setPass(pass);
            Duelyst.accounts.add(account);
            Duelyst.currentMenu = MainMenu.getInstance();
            console.welcome();
        }
    }

    void login(String user, String pass) {
        Account account = new Account(user, pass);
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user) && account1.getPass().equals(pass)) {
                Duelyst.currentMenu = MainMenu.getInstance();
                Duelyst.currentAccount=account;
                console.welcome();
            } else {
                console.loginError();
            }
        }
    }

    void showLeaderBoard() {
        Map<String, Integer> w = sortByValue(Duelyst.wins);
        int counter = 1;
        for (Map.Entry<String, Integer> x : w.entrySet()) {
            System.out.println(counter + " - Username : " + x.getKey() + " - Wins : " + x.getValue());
            counter++;
        }
    }

    private Map<String, Integer> sortByValue(HashMap<String, Integer> wins) {
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

    void save() {

    }

    void logOut() {
        Duelyst.currentAccount = null;
        Duelyst.currentMenu = AccountPage.getInstance();
        console.logoutMessage();
    }


    void help() {
        console.begHelp();
    }

    void quit() {
        Duelyst.finishGame = true;
    }
}
