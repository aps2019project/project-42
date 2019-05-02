import java.util.ArrayList;

class Player{
    Account account;
    int mana;
    int turn;
    Hand hand;
    GraveYard graveYard;
    CommingSoon commingSoon;
    Deck deck;
    Item usable;
    Battle battle;
    ArrayList<Item> items;
    void goinOrNotGoin(Cell cell){
        if (cell.force.HP==0){
            this.graveYard.cards.add(cell.force);
            cell.force=null;
        }
    }
    boolean rangeValidation(Cell originCell,Cell destinationCell){
        if (((originCell.force.rangeType.equals(RangeType.melee))&&((Math.abs(destinationCell.getX()-originCell.getX())>1)||(Math.abs(destinationCell.getY()-originCell.getY())>1)))||((originCell.force.rangeType.equals(RangeType.ranged))&&((Math.abs(destinationCell.getX()-originCell.getX())+Math.abs(destinationCell.getY()-originCell.getY())>originCell.force.range)||((Math.abs(destinationCell.getX()-originCell.getX())>1)||(Math.abs(destinationCell.getY()-originCell.getY())<2))))||((originCell.force.rangeType.equals(RangeType.hybrid))&&(Math.abs(destinationCell.getX()-originCell.getX())+Math.abs(destinationCell.getY()-originCell.getY())>originCell.force.range)))
            return false;
        else
            return true;
    }
    Deck shuffleDeck(Deck deck){
        ArrayList<Card> cards=new ArrayList<>();
        Deck shuffledDeck=new Deck(deck.name);
        while (deck.cards.size()!=0){
            int m=(int)(Math.random()*deck.cards.size());
            shuffledDeck.cards.add(deck.cards.get(m));
            deck.cards.remove(m);
        }
        return shuffledDeck;
    }
    void fillingHand(Deck deck,Hand hand,CommingSoon commingSoon){
        if(hand.cards.length==5)
            return;
        else {
            int i=0;
            while (!hand.cards[i].equals(null)){
                i++;
            }
            hand.cards[i]=commingSoon.card;
            commingSoon.card=deck.cards.get(0);
            deck.cards.remove(0);
        }
    }
    Player(Account account){
        this.turn=1;
        this.account=account;
        this.mana=2;
        this.deck=account.mainDeck;
        this.usable=account.mainDeck.usable;
        this.deck=shuffleDeck(this.deck);
        for (int i=0;i<5;i++){
            fillingHand(this.deck,this.hand,this.commingSoon);
        }
    }
    void deploy(Minion minion,Cell cell){
        if(this.mana<minion.MP){
            System.out.println("wtf dude i don have that kinda mana");
        }
        else {
            this.mana-=minion.MP;
            cell.force =minion;
           // minion.x=cell.getX();
           // minion.y=cell.getY();
        }
    }
    void move(Cell originCell,Cell destinationCell){
        if(originCell.force.equals(null)){
            "wtf r u doin? no one's here dumbass"
        }
        else if(Math.abs(destinationCell.getX()-originCell.getX())+Math.abs(destinationCell.getY()-originCell.getY())>2){
            System.out.println("wtf dude that's WAY too far");
        }
        else if(!destinationCell.force.equals(null)){
            System.out.println("where tf r u goin?? that cell is occupied with some stupid shit like u");
        }
        else {
            destinationCell.force =originCell.force;
            originCell.force =null;
        //    destinationCell.force.x=destinationCell.getX();
          //  destinationCell.force.y=destinationCell.getY();
        }
    }
    void attack(Cell originCell,Cell destinationCell){
        if(destinationCell.force.equals(null)){
            System.out.println("who tf r u attackin?? no ones here assface");
        }
        else if(originCell.force.equals(null)){
            System.out.println("wtf, no one's here, do it lesser so u can do it always stupid wanker");
        }
        else if (!rangeValidation(originCell, destinationCell)){
            System.out.println("wtf r u?? sum sack of shit?? i cant atk this place");
        }
        else{
            destinationCell.force.HP-=originCell.force.AP;
            if (destinationCell.force.HP<0)
                destinationCell.force.HP=0;
            if(rangeValidation(destinationCell,originCell)){
                originCell.force.HP-=destinationCell.force.AP;
                if (originCell.force.HP<0)
                    originCell.force.HP=0;
            }
            goinOrNotGoin(destinationCell);
            goinOrNotGoin(originCell);
        }
    }
    void comboAttack(Cell destinationCell,Cell... originCells){
        boolean b=true;
        for(int i=0;i<originCells.length;i++){
            if(!rangeValidation(originCells[i],destinationCell))
                b=false;
        }
        if(!b){
            System.out.println("wtf dude, we cant attack this place");
        }
        else{
           // b=true;// unneeded
            for (int i=0;i<originCells.length;i++){
                destinationCell.force.HP-=originCells[i].force.AP;
                if (destinationCell.force.HP<0)
                    destinationCell.force.HP=0;
                if(rangeValidation(destinationCell,originCells[i])&&b){
                    originCells[i].force.HP-=destinationCell.force.AP;
                    if(originCells[i].force.HP<0)
                        originCells[i].force.HP=0;
                    goinOrNotGoin(originCells[i]);
                }
                goinOrNotGoin(destinationCell);
            }
        }
    }
    void specialPower(Cell cell){
        cast spell this.deck.hero.specialPower
    }
    void endTurn(){
        this.battle.turn++;
    }
    void gameInfo(){

    }
    void showMyMinions(){

    }
    void showOppenontMinions(){

    }
    void showCardInfo(Card card){

    }
    void showHand(){

    }
    void select(){

    }
    void showCollectibles(){

    }
    void showItemInfo(){

    }
    void useItem(Item item){ //use collectible
        for (Item a:this.items){
            cast spell a
        }
    }
    void showNextCard(){

    }
    void enterGraveYard(){

    }
    void showInfoInGraveYard(Card card){

    }
    void showCardsInGraveYard(){

    }
    void help(){

    }
    void exit(){

    }
    void showMenu(){

    }
}
