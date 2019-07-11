package graphic;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import logic.Account;
import logic.AccountPage;
import logic.Duelyst;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class AccountPageMenu {
    PrimaryStage primaryStage = PrimaryStage.getInstance();

    public static AccountPageMenu accountPageMenu = new AccountPageMenu();

    public static AccountPageMenu getInstance() {
        return accountPageMenu;
    }

    Duelyst duelyst = new Duelyst();

    public void quit(MouseEvent mouseEvent) {
        ArrayList<Account> accounts = Duelyst.accounts;

        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter("accounts.json");
            String string = yaGson.toJson(accounts);
            writer.write(string);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.stage.close();
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(CreateAccountPage.class.getResource("CreateAccountPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.stage.setTitle("Duelyst");
primaryStage.stage.setScene(new Scene(root));primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(LoginPage.class.getResource("LoginPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.stage.setTitle("Duelyst");
primaryStage.stage.setScene(new Scene(root));primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void showLeaderBoard(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(LeaderBoardPage.class.getResource("LeaderBoardPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.stage.setTitle("Duelyst");
primaryStage.stage.setScene(new Scene(root));primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void AccountPageHelp(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(HelpAccountPage.class.getResource("HelpAccountPage.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.stage.setTitle("Duelyst");
primaryStage.stage.setScene(new Scene(root));primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void logOut(MouseEvent mouseEvent) {
        AccountPage accountPage = new AccountPage();
        accountPage.logOut();
    }
}
