package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.AccountPage;

import java.io.IOException;

public class LoginPage {
    public TextField username;
    public PasswordField password;
    PrimaryStage primaryStage = PrimaryStage.getInstance();


    public void login(MouseEvent mouseEvent) throws IOException {
        AccountPage accountPage=new AccountPage();
        accountPage.login(username.getText(),password.getText());
        if (accountPage.login(username.getText(),password.getText())) {
            Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
            primaryStage.stage.setTitle("Duelyst");
            primaryStage.stage.setScene(new Scene(root));
            primaryStage.stage.setMaximized(true);
            primaryStage.stage.show();
        }
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
