package graphic;


import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import logic.AccountPage;

import java.io.IOException;

public class CreateAccountPage {
    public TextField username;
    public PasswordField password;

    PrimaryStage primaryStage = PrimaryStage.getInstance();


    public void createAcc(MouseEvent mouseEvent) throws IOException {
        AccountPage accountPage = new AccountPage();
        accountPage.createAccount(username.getText(), password.getText());
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
