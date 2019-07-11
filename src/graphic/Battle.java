package graphic;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Battle {
    public HBox firstView;
    public StackPane comingSoon;
    public VBox graveYard;
    public VBox graveYardCards;
    public StackPane myHero;
    public StackPane anotherHero;
    public HBox secondView;
    public Pane one;
    public Pane two;
    public Pane three;
    public Pane four;
    public Pane five;
    public Pane six;
    PrimaryStage primaryStage = PrimaryStage.getInstance();
    private Random random = new Random();
    Card selected;

    public void quit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(MainMenu.class.getResource("MainMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.stage.setTitle("Duelyst");
primaryStage.stage.setScene(new Scene(root));primaryStage.stage.setMaximized(true);
        primaryStage.stage.show();
    }

    public void initialize() {
        setMyHero();
        setSecondHero();
        showDeck();
    }

    private void showDeck() {
        Deck gameDeck=Duelyst.currentAccount.mainDeck;
        int x=random.nextInt()%20;
        one.getChildren().addAll(new ImageView(new Image("unit_gifs/boss_chaosknight_breathing.gif")),new Label(gameDeck.getCards().get(x).name));
        two.getChildren().add(new ImageView(new Image("unit_gifs/boss_borealjuggernaut_breathing.gif")));
        three.getChildren().add(new ImageView(new Image("unit_gifs/boss_cindera_breathing.gif")));
        four.getChildren().add(new ImageView(new Image("unit_gifs/boss_orias_breathing.gif")));
        five.getChildren().add(new ImageView(new Image("unit_gifs/boss_oriasidol_breathing.gif")));
        six.getChildren().add(new ImageView(new Image("unit_gifs/boss_gol_breathing.gif")));
        comingSoon.getChildren().add(new ImageView(new Image("unit_gifs/boss_legion_breathing.gif")));
    }

    private void setSecondHero() {
        int y = random.nextInt() % 5;
        Image heroGif2;
        Image second;
        switch (y) {
            case 0:
                second = new Image("generals/general_portrait_image_hex_f1-third@2x.png");
                heroGif2 = new Image("unit_gifs/f1_altgeneraltier2_breathing.gif");
                break;
            case 2:
                second = new Image("generals/general_portrait_image_hex_f2@2x.png");
                heroGif2 = new Image("unit_gifs/boss_wujin_breathing.gif");
                break;
            case 3:
                second = new Image("generals/general_portrait_image_hex_f2-alt@2x.png");
                heroGif2 = new Image("unit_gifs/boss_solfist_breathing.gif");
                break;
            case 4:
                second = new Image("generals/general_portrait_image_hex_f2-third@2x.png");
                heroGif2 = new Image("unit_gifs/boss_kron_breathing.gif");
                break;
            default:
                second = new Image("generals/general_portrait_image_hex_f5-third@2x.png");
                heroGif2 = new Image("unit_gifs/f5_tier2general_breathing.gif");
                break;
        }
        ImageView imageView1 = new ImageView(second);
        imageView1.setFitWidth(300);
        imageView1.setFitHeight(300);
        secondView.setAlignment(Pos.CENTER_RIGHT);
        anotherHero.getChildren().add(new ImageView(heroGif2));
        secondView.getChildren().addAll(imageView1);
    }

    private void setMyHero() {
        int x = random.nextInt() % 5;
        Image heroGif;
        Image first;
        switch (x) {
            case 0:
                first = new Image("generals/general_portrait_image_hex_f6@2x.png");
                heroGif = new Image("unit_gifs/f6_tier2general_breathing.gif");
                break;
            case 2:
                first = new Image("generals/general_portrait_image_hex_calibero@2x.png");
                heroGif = new Image("unit_gifs/boss_harmony_breathing.gif");
                break;
            case 3:
                first = new Image("generals/general_portrait_image_hex_draugar@2x.png");
                heroGif = new Image("unit_gifs/f4_altgeneraltier2_breathing.gif");
                break;
            case 4:
                first = new Image("generals/general_portrait_image_hex_f1@2x.png");
                heroGif = new Image("unit_gifs/f1_tier2general_breathing.gif");
                break;
            default:
                first = new Image("generals/general_portrait_image_hex_f1-alt@2x.png");
                heroGif = new Image("unit_gifs/f2_altgeneraltier2_breathing.gif");
                break;
        }
        ImageView imageView = new ImageView(first);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        firstView.getChildren().add(imageView);
        myHero.getChildren().add(new ImageView(heroGif));
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
