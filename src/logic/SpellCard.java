package logic;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;

public class SpellCard extends Item {

    int MP;

    public SpellCard(String name) throws FileNotFoundException {
        super(name);
    }

    public SpellCard(String name,String desc,int id,int price,int MP) throws FileNotFoundException {
        super(name,desc,id,price);
        this.MP=MP;
    }

    @Override
    public String toString() {
        return " : type : spell - : name : " + this.name + " - MP : " + this.MP + " - desc : " + this.desc + " - price : " + this.price;

    }

    public StackPane makeCard() {
        StackPane card = new StackPane();
        ImageView image = new ImageView(this.image);
        image.setOpacity(0.5);
        Label info = new Label("Name : " + this.name + "\nSpell" + "\nMP : " + this.MP + "\nDesc : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().addAll(info);
        return card;
    }
}
