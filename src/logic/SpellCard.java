package logic;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class SpellCard extends Item {

    int MP;

    public SpellCard(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public String toString() {
        return " : type : spell - : name : " + this.name + " - MP : " + this.MP + " - desc : " + this.desc + " - price : " + this.price;

    }

    public VBox makeCard() {
        VBox card = new VBox();
        ImageView image = new ImageView(this.image);
        Label info = new Label("Name : " + this.name + "\nSpell" + "\nMP : " + this.MP + "\nDesc : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().addAll(image, info);
        card.setSpacing(20);
        return card;
    }
}
