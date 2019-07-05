package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.*;

import java.io.IOException;
import java.util.Random;


public class Battle {
    public ImageView firstView;
    public StackPane comingSoon;
    public VBox graveYard;
    public VBox graveYardCards;
    private Image first;
    private Image second;
    public ImageView secondView;
    PrimaryStage primaryStage = PrimaryStage.getInstance();
    private Random random = new Random();

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void initialize() {
        int x = random.nextInt() % 5;
        switch (x) {
            case 0:
                first = new Image("/generals/general_portrait_image_hex_f6@2x.png");
                break;
            case 2:
                first = new Image("/generals/general_portrait_image_hex_calibero@2x.png");
                break;
            case 3:
                first = new Image("/generals/general_portrait_image_hex_draugar@2x.png");
                break;
            case 4:
                first = new Image("/generals/general_portrait_image_hex_f1@2x.png");
                break;
            case 1:
                first = new Image("/generals/general_portrait_image_hex_f1-alt@2x.png");
                break;
        }
        firstView = new ImageView(first);
        firstView.setX(50);
        firstView.setY(50);
        int y = random.nextInt() % 5;
        switch (y) {
            case 0:
                second = new Image("/generals/general_portrait_image_hex_f1-third@2x.png");
                break;
            case 2:
                second = new Image("/generals/general_portrait_image_hex_f2@2x.png");
                break;
            case 3:
                second = new Image("/generals/general_portrait_image_hex_f2-alt@2x.png");
                break;
            case 4:
                second = new Image("/generals/general_portrait_image_hex_f2-third@2x.png");
                break;
            case 1:
                second = new Image("/generals/general_portrait_image_hex_f5-third@2x.png");
                break;
        }
        secondView = new ImageView(second);
        secondView.setY(50);
        secondView.setX(50);
    }

    public void enterGraveYard(MouseEvent mouseEvent) {
        graveYard.setVisible(true);
        for (Card card : Duelyst.currentAccount.owner.graveYard.cards) {
            if (Duelyst.getAllHeroes().contains(card)) {
                Hero hero = (Hero) card;
                graveYardCards.getChildren().add(hero.makeCard());
            }
            if (Duelyst.getAllMinions().contains(card)) {
                Minion minion = (Minion) card;
                graveYardCards.getChildren().add(minion.makeCard());
            }
            if (Duelyst.getAllItems().contains(card)) {
                Item item = (Item) card;
                graveYardCards.getChildren().add(item.makeCard());
            }
            if (Duelyst.getAllSpellCards().contains(card)) {
                SpellCard spellCard = (SpellCard) card;
                graveYardCards.getChildren().add(spellCard.makeCard());
            }
            graveYardCards.getChildren().add(new Label("_________"));
        }
    }

    public void quitGraveYard(MouseEvent mouseEvent) {
        graveYard.setVisible(false);
    }
}
