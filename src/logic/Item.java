package logic;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Item extends Card {

    ArrayList<Spell> spells;

    public Item(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public String toString() {
        return " : name : " + this.name + " - desc : " + this.desc + " - price : " + this.price;
    }

    public StackPane makeCard() {
        StackPane card = new StackPane();
        ImageView image = new ImageView(this.image);
        image.setOpacity(0.5);
        Label info = new Label("Name : " + this.name + "\nDesc : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().addAll(info);
        return card;
    }
}
