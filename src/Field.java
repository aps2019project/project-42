class Field{
    Cell[][] cells=new Cell[5][9];
    Item[] items=new Item[2];
    GraveYard[] graveYards=new GraveYard[2];
    Battle battle;
    Field(){
        for (int i=0;i<5;i++){
            for (int j=0;j<9;j++){
                cells[i][j].setX(i);
                cells[i][j].setY(j);
            }
        }
    }
}
