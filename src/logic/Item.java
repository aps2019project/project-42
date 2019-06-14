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
        ImageView image = new ImageView(this.image);
        Label name = new Label("Name : " + this.name);
        Label desc = new Label("Desc : " + this.desc);
        Label price = new Label("Price : " + this.price);
        card.getChildren().addAll(image, name, desc, price);
        card.setSpacing(20);
        return card;
    }
}
