package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import logic.AccountPage;
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
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(LoginPage.class.getResource("LoginPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void showLeaderBoard(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(LeaderBoardPage.class.getResource("LeaderBoardPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void AccountPageHelp(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(HelpAccountPage.class.getResource("HelpAccountPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void logOut(MouseEvent mouseEvent) {
        AccountPage accountPage = new AccountPage();
        accountPage.logOut();
    }
}
