import java.util.Scanner;

class AccountPage{
    AccountPage(Scanner scanner){
        String cm = scanner.nextLine();
        String cmd = cm.trim();
        String command = cmd.toLowerCase();
        String[] commandArray = command.split("\\s+");
        while (true){
            if (command.matches("exit")){
                quit();
                break;
            } else if (command.matches("create(\\s+)account[0-9a-z]")){
                System.out.println("Please enter your password to create your account.");
                String pass=scanner.nextLine();
                createAccount(commandArray[2],pass);
            } else if (command.matches("login(\\s+)[0-9a-z]")){
                System.out.println("Please enter your password to enter your account.");
                String pass=scanner.nextLine();
                login(commandArray[1],pass);
            } else if (command.matches("show(\\s+)leaderboard")){
                showLeaderBoard();
            } else if (command.matches("save")){
                save();
            } else if (command.matches("logout")){
                logOut();
            } else if (command.matches("help")){
                help();
            }
        }
    }
    void createAccount(String user,String pass){

    }
    void login(String user,String pass){

    }
    void showLeaderBoard(){

    }
    void save(){

    }
    void logOut(){

    }
    boolean validAccount(String user,String pass){

    }
    void help(){
        System.out.println("to creat an account: create account [your selective username]");
        System.out.println("login: login [your username]");
        System.out.println("to see leaderBoard sorted by number of wins: show leaderboard");
        System.out.println("to save your account: save");
        System.out.println("logout: logout");
    }
    void quit(){

    }
}
