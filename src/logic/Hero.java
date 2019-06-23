package logic;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    public VBox makeCard() {
        VBox card = new VBox();
//        ImageView image = new ImageView(this.image);
        Label info = new Label("Name : " + this.name + "\nClass : " + this.typeOfHero + "\nAP : " + this.AP + "\nHP : " + this.HP + "\nSpecial Power : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
//        card.getChildren().addAll(image,info);
        card.setSpacing(20);
        //card.setBackground(new Background(new BackgroundFill(Color.web("#000000"), CornerRadii.EMPTY, Insets.EMPTY)));
        return card;
    }
}
