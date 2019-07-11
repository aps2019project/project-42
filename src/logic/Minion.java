package logic;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Minion extends Force {
    boolean combo;
    String typeOfMinion;
    ArrayList<HashMap<Integer, Integer>> nemesis;//logic.Card ID , number of attacks

    public Minion(String name,String typeOfMinion,int AP,int HP,int MP,String desc,int price,int id) throws FileNotFoundException {
        super(name, desc, id, price, MP,HP,AP);
        this.typeOfMinion=typeOfMinion;
    }

    public Minion(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public String toString() {
        return " type : minion - name : " + this.name + " - class : " + this.typeOfMinion + " - AP : " + this.AP + " - HP : " + this.HP + " - MP : " + this.MP + " - special power : " + this.desc + " - price : " + this.price;
    }

    public StackPane makeCard() {
        StackPane card = new StackPane();
        ImageView image = new ImageView(this.image);
        image.setOpacity(0.5);
        Label info = new Label("Name : " + this.name + "\nMinion\nClass : " + this.typeOfMinion + "\nAP : " + this.AP + "\nHP : " + this.HP + "\nMP : " + this.MP + "\nDesc : " + this.desc + "\nPrice : " + this.price);
        info.setStyle("-fx-font: 24 Nazli");
        card.getChildren().addAll(info);
        return card;
    }
}
