package logic;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    public VBox makeCard() {
        VBox card = new VBox();
        ImageView image = new ImageView();
        image.setImage(this.image);
        image.setFitHeight(30);
        image.setFitWidth(30);
        Label info = new Label("Name : " + this.name + "\nDesc : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().addAll(image, info);
        card.setSpacing(20);
        return card;
    }
}
