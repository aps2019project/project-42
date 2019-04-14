import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class Duelyst{
    ArrayList<Account> accounts;
    ArrayList<Card> source;
    Scanner scanner=new Scanner(System.in);
    void mainMenu(Account account,Scanner scanner){

    }
    void addToSource(Card card){

    }
    void start(Scanner scanner){

    }
}
class Shop{
    ArrayList<Card> cards;
    void buyCard(Scanner scanner){

    }
    void sellCard(Scanner scanner){

    }
    void showCollection(Scanner scanner){

    }
    void showShop(Scanner scanner){

    }
    void searchShop(Scanner scanner){

    }
    void searchCollection(Scanner scanner){

    }
    void help(){

    }
    void exit(){

    }
}
class AccountPage{
    void createAccount(String user,String pass){

    }
    void login(String user,String pass){

    }
    void showLeaderBoard(){

    }
    void save(){

    }
    void logOut(){

    }
    boolean validAccount(String user,String pass){

    }
    void help(){

    }
    void quit(){

    }
}
class LeaderBoard{
    ArrayList<Account> sortWinBase(ArrayList<Account> accounts){

    }
    void show(){

    }
}
class Account{
    String user;
    String pass;
    ArrayList<MatchHistory> matchList;
    ArrayList<Deck> decks;
    int mainDeckIndex;
}
class MatchHistory{
    Player enemy;
    boolean resul;
    int date;
}
class Player{
    Account account;
    int mana;
    int turn;
    Hand hand;
    GraveYard graveYard;
    CommingSoon commingSoon;
    Deck deck;
    Item usable;
    ArrayList<Item> items;
    void deploy(Card card,Cell cell){

    }
    void move(Card card,Cell cell){

    }
    void attack(Card card,Cell cell){

    }
    void comboAttack(Card card,Cell cell){

    }
    void specialPower(Cell cell){

    }
    void endTurn(){

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
class Battle{
    GameType gameType;
    int flagsNumber;
}
enum GameType{
    story,
    custom,
    multi
}
class Card{
    String name;
    String desc;//Description
    Player owner;
}
class Item extends Card{
    ArrayList<Spell> spells;
}
class SpellCard extends Item{
    int MP;//Mana Price
}
class Fighter extends SpellCard{
    int AP;//Attack Power
    int HP;//Health Power
    int rangeType;
    int range;
    HashMap<Integer,Integer> nemesis;//Card ID , number of attacks
}
class Spell{
    int targetsNumber;
    Effect effect;
    int effectQuantity;
    TargetType targetType;
    ArrayList<Cell> location;
    int duration;
}
class Deck{
    ArrayList<Card> cards;
}
class GraveYard{
    ArrayList<Card> cards;
}
class CommingSoon{
    Card card;
}
class Field{
    Cell[][] cells=new Cell[5][9];
    Item[] items=new Item[2];
    GraveYard[] graveYards=new GraveYard[2];
}
class Cell{
    CellEffects cellEffects;
    Item Collectible;
    boolean flag;
    Card card;
}
enum CellEffects{
    poison,
    firey,
    holy
}
enum Effect{
    disarm,
    dispel,
    chngeAP,
    changeHP,
    stun,
    holy
}
enum TargetType{
    hero,
    minion,
    ...
}
enum Time{
    onSpawn,
    onDeath,
    onAttack,
    onDefend,
    everyTurn,
    ...,
    ...
}
class Collection{
    ArrayList<Card> cards;
    void show(){

    }
    void search(){

    }
    void save(){

    }
    void createDeck(){

    }
    void removeDeck(){

    }
    void addToDeck(){

    }
    void removeFromDeck(){

    }
    void validateDeck(){

    }
    void selectMainDeck(){

    }
    void showAllDecks(){

    }
    void showDeck(){

    }
    void help(){

    }
    void exit(){

    }
}
class Game{
    Player first;
    Player second;
    Field field;
    void EndGame(){

    }
}
class Hand{
    Card[] cards=new Card[5];
}
public class Main {
    public static void main(String[] args) {
        // write your code here
    }
}
