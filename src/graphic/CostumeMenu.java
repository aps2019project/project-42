package graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CostumeMenu {
    public TextField name;
    public TextField price;
    public TextField id;
    public TextField desc;
    public VBox minionChoosing;
    public TextField mp;
    public TextField typeOfMinion;
    public TextField ap;
    public TextField hp;
    public VBox heroChoosing;
    public TextField mp1;
    public TextField typeOfHero;
    public TextField ap1;
    public TextField hp1;
    public TextField coolDown;
    public StackPane itemChoosing;
    public VBox spellCardChoose;
    public TextField mp2;
    PrimaryStage primaryStage = PrimaryStage.getInstance();

    public void minionChoose(ActionEvent actionEvent) {
        minionChoosing.setVisible(true);
        heroChoosing.setVisible(false);
        itemChoosing.setVisible(false);
        spellCardChoose.setVisible(false);
    }

    public void heroChoose(ActionEvent actionEvent) {
        heroChoosing.setVisible(true);
        minionChoosing.setVisible(false);
        itemChoosing.setVisible(false);
        spellCardChoose.setVisible(false);
    }

    public void itemChoose(ActionEvent actionEvent) {
        itemChoosing.setVisible(true);
        minionChoosing.setVisible(false);
        heroChoosing.setVisible(false);
        spellCardChoose.setVisible(false);
    }

    public void spellCardChoose(ActionEvent actionEvent) {
        spellCardChoose.setVisible(true);
        itemChoosing.setVisible(false);
        minionChoosing.setVisible(false);
        heroChoosing.setVisible(false);
    }


    public void createMinion(MouseEvent mouseEvent) throws FileNotFoundException {
        Minion minion=new Minion(name.getText(),typeOfMinion.getText(),Integer.parseInt(ap.getText()),Integer.parseInt(hp.getText()),Integer.parseInt(mp.getText()),desc.getText(),Integer.parseInt(price.getText()),Integer.parseInt(id.getText()));
        Duelyst.getAllMinions().add(minion);
        Alert add=new Alert(Alert.AlertType.INFORMATION);
        add.setContentText("Minion created!");
        add.show();
    }

    public void createHero(MouseEvent mouseEvent) throws FileNotFoundException {
       Hero hero =new Hero(name.getText(),typeOfHero.getText(),Integer.parseInt(ap1.getText()),Integer.parseInt(hp1.getText()),Integer.parseInt(mp1.getText()),desc.getText(),Integer.parseInt(price.getText()),Integer.parseInt(id.getText()),Integer.parseInt(coolDown.getText()));
       Duelyst.getAllHeroes().add(hero);
        Alert add=new Alert(Alert.AlertType.INFORMATION);
        add.setContentText("Hero created!");
        add.show();
    }

    public void createItem(MouseEvent mouseEvent) throws FileNotFoundException {
        Item item=new Item(name.getText(),desc.getText(),Integer.parseInt(id.getText()),Integer.parseInt(price.getText()));
        Duelyst.getAllItems().add(item);
        Alert add=new Alert(Alert.AlertType.INFORMATION);
        add.setContentText("Item created!");
        add.show();
    }

    public void createSpellCard(MouseEvent mouseEvent) throws FileNotFoundException {
        SpellCard spellCard=new SpellCard(name.getText(),desc.getText(),Integer.parseInt(id.getText()),Integer.parseInt(price.getText()),Integer.parseInt(mp2.getText()));
        Duelyst.getAllSpellCards().add(spellCard);
        Alert add=new Alert(Alert.AlertType.INFORMATION);
        add.setContentText("SpellCard created!");
        add.show();
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
