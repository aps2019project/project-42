package logic;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;

public class Hero extends Force {
    int coolDown;
    String typeOfHero;

    public Hero(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public String toString() {
        return " : name : " + this.name + " - AP : " + this.AP + " - HP : " + this.HP + " - class : " + this.typeOfHero + " - cool down : " + this.coolDown + " - special power : " + this.desc + " - price : " + this.price;
    }

    public StackPane makeCard() {
        StackPane card = new StackPane();
        ImageView image = new ImageView(this.image);
        Label info = new Label("Name : " + this.name + "\nClass : " + this.typeOfHero + "\nAP : " + this.AP + "\nHP : " + this.HP + "\nSpecial Power : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().add(info);
        return card;
    }
}
