package graphic;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import logic.Duelyst;

import java.io.IOException;

public class AccountPageMenu {
    PrimaryStage primaryStage = PrimaryStage.getInstance();

    public static AccountPageMenu accountPageMenu = new AccountPageMenu();

    public static AccountPageMenu getInstance() {
        return accountPageMenu;
    }

    Duelyst duelyst = new Duelyst();


    public void quit(MouseEvent mouseEvent) {
        primaryStage.stage.close();
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(CreateAccountPage.class.getResource("CreateAccountPage.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(LoginPage.class.getResource("LoginPage.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void showLeaderBoard(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(LeaderBoardPage.class.getResource("LeaderBoardPage.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void AccountPageHelp(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(HelpAccountPage.class.getResource("HelpAccountPage.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
