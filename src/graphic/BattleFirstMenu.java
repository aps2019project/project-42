package graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.Deck;
import logic.Duelyst;

import java.io.IOException;

public class BattleFirstMenu {
    public TextField deckName;
    public VBox decks1;
    public HBox decks;
    PrimaryStage primaryStage = PrimaryStage.getInstance();
    public StackPane single;
    public StackPane multi;
    public StackPane story;
    public StackPane costume;
    public HBox modes;

    public void singlePlayer(MouseEvent mouseEvent) {
        single.setVisible(false);
        multi.setVisible(false);
        story.setVisible(true);
        costume.setVisible(true);
    }

    public void enterStory(MouseEvent mouseEvent) {
        single.setVisible(false);
        multi.setVisible(false);
        modes.setVisible(true);
    }


    public void enterCostume(MouseEvent mouseEvent) {
        single.setVisible(false);
        multi.setVisible(false);
        modes.setVisible(false);
        story.setVisible(false);
        costume.setVisible(false);
        decks.setVisible(true);
        for (Deck deck: Duelyst.currentAccount.getDecks()) {
            decks1.getChildren().add(deck.showDeck());
        }
    }

    public void enterBattle(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Battle.class.getResource("Battle.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.stage.setTitle("Duelyst");
        primaryStage.stage.setScene(new Scene(root));
        primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }
}
