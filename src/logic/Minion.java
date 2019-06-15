package logic;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Minion extends Force {
    boolean combo;
    String typeOfMinion;
    ArrayList<HashMap<Integer, Integer>> nemesis;//logic.Card ID , number of attacks

    public Minion(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public String toString() {
        return " type : minion - name : " + this.name + " - class : " + this.typeOfMinion + " - AP : " + this.AP + " - HP : " + this.HP + " - MP : " + this.MP + " - special power : " + this.desc + " - price : " + this.price;
    }

    public VBox makeCard() {
        VBox card = new VBox();
        ImageView image = new ImageView(this.image);
        Label info = new Label("Name : " + this.name + "\nMinion\nClass : " + this.typeOfMinion + "\nAP : " + this.AP + "\nHP : " + this.HP + "\nMP : " + this.MP + "\nDesc : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().addAll(image,info);
        card.setSpacing(20);
        return card;
    }
}
