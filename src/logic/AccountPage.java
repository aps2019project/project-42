package logic;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.*;

public class AccountPage extends Menu {

    Console console = Console.getInstance();

    public static AccountPage accountPage = new AccountPage();

    public static AccountPage getInstance() {
        return accountPage;
    }



    public void createAccount(String user, String pass) {
        boolean check = true;
        Account account = new Account(user, pass);
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user)) {
                Alert userExist = new Alert(Alert.AlertType.ERROR);
                userExist.setContentText("This username already exists.");
                userExist.show();
                check = false;
            }
        }
        if (check) {
            Duelyst.wins.putIfAbsent(user, 0);
            account.setUser(user);
            account.setPass(pass);
            Duelyst.accounts.add(account);
            Alert created = new Alert(Alert.AlertType.INFORMATION);
            created.setContentText("Welcome. You can login to enter your account.");
            created.show();
        }
    }

    public boolean login(String user, String pass) {
        Account account = new Account(user, pass);
        for (Account account1 : Duelyst.accounts) {
            if (account1.getUser().equals(user) && account1.getPass().equals(pass)) {
                Duelyst.currentMenu = MainMenu.getInstance();
                Duelyst.currentAccount = account;
                return true;
            }
        }
        Alert invalid = new Alert(Alert.AlertType.ERROR);
        invalid.setContentText("Invalid username or password.");
        invalid.show();
        return false;
    }

    public VBox showLeaderBoard() {
        Map<String, Integer> w = sortByValue(Duelyst.wins);
        VBox vBox=new VBox();
        int counter = 1;
        for (Map.Entry<String, Integer> x : w.entrySet()) {
            Label info=new Label(counter + " - Username : " + x.getKey() + " - Wins : " + x.getValue());
            info.setStyle("-fx-font: 24 Nazli");
            vBox.getChildren().add(info);
            counter++;
        }
        return vBox;
    }

    private Map<String, Integer> sortByValue(HashMap<String, Integer> wins) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(wins.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    void save() {
        console.savedAccount();
    }

    public void logOut() {
        Duelyst.currentMenu = AccountPage.getInstance();
        Duelyst.currentAccount=null;
        Alert logout=new Alert(Alert.AlertType.INFORMATION);
        logout.setContentText("You logged out.");
        logout.show();
        //console.logoutMessage();
    }


    void help() {
        console.begHelp();
    }

    public static void quit() {
        Duelyst.finishGame = true;
    }
}
