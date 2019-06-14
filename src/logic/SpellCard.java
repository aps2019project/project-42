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
        Label name = new Label("Name : " + this.name);
        Label type = new Label("Spell");
        Label MP = new Label("MP : " + this.MP);
        Label desc = new Label("Desc : " + this.desc);
        Label price = new Label("Price : " + this.price);
        card.getChildren().addAll(image, name, type, MP, desc, price);
        card.setSpacing(20);
        return card;
    }
}
