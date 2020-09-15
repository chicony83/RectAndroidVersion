package com.example.rects;

public class Find {

    private static boolean firstCell;

    private boolean identicalCells = false;

    private static int cellsForClean = 0;                                     //колчество добавленных ячеек

    private static int colorForFind;                    //цвет который будет искаться

    private static int colorDown;
    private static int colorUp;
    private static int colorLeft;
    private static int colorRight;

    private static int wayToMove;                       //направление перемещения

    int c;
    int x;
    int y;

    int lMX = AreaForClearing.getLastMoveX(x);
    int lMY = AreaForClearing.getLastMoveY(y);
    private static int areaWidth = SettingGame.getWIDTH();
    private static int areaHeight = SettingGame.getHEIGHT();

    public Find() {

        cellsForClean = 0;

        identicalCells = true;

        int lastMoveX = lMX;
        int lastMoveY = lMY;

        colorForFind = AreaForGame.getArea(lastMoveX, lastMoveY);

        colorDown = AreaForGame.getArea(lastMoveX, lastMoveY + 1);
        colorUp = AreaForGame.getArea(lastMoveX, lastMoveY - 1);

        colorLeft = AreaForGame.getArea(lastMoveX - 1, lastMoveY);
        colorRight = AreaForGame.getArea(lastMoveX + 1, lastMoveY);


        /*-----------контроль за превышением------------*/

        if (lastMoveY + 1 >= areaHeight-1) {
            colorDown = 0;
        }
        if (lastMoveY - 1 <= 0) {
            colorUp = 0;
        }
        if (lastMoveX + 1 >= areaWidth-1) {
            colorRight = 0;
        }
        if (lastMoveX - 1 <= 0) {
            colorLeft = 0;
        }

        /*поиск клеток с одинаковым цветом*/


        if (colorForFind == colorDown) {
            find();
            identicalCells = false;
        }


        if (colorForFind == colorUp & identicalCells == true) {
            find();
            identicalCells = false;

        }

        if (colorForFind == colorLeft & identicalCells == true) {
            find();
            identicalCells = false;

        }

        if (colorForFind == colorRight & identicalCells == true) {
            find();
            identicalCells = false;
        }

        /*-----------очистка найденого------------*/
        if(cellsForClean > 2){
            CleaningAfterMove.setNeedClearing(true);
            changeColorBeforeCleaning();
        }

    }

    public static void find() {

        firstCell = true;

        for (int j = 1; j < areaWidth - 1; j++) {                       //поиск в низ
            for (int i = 1; i < areaHeight - 1; i++) {                  //в право
                if (AreaForClearing.getAreaForClearing(i, j) == 1) {
                    if (firstCell == true) {
                        cellsForClean++;
                        firstCell =false;
                    }
                    indexing(i,j);
                }
            }
        }

        for (int j = 1; j < areaWidth - 1; j++) {                       //поиск в низ
            for (int i = areaHeight-1; i > 0; i--) {                    //в лево
                if (AreaForClearing.getAreaForClearing(i, j) == 1) {
                    if (firstCell == true) {
                        cellsForClean++;
                        firstCell =false;
                    }
                    indexing(i,j);
                }
            }
        }
        for (int j = 1; j < areaWidth - 1; j++) {                       //поиск в верх
            for (int i = 1; i < areaHeight - 1; i++) {                  //в лево
                if (AreaForClearing.getAreaForClearing(i, j) == 1) {
                    if (firstCell == true) {
                        cellsForClean++;
                        firstCell =false;
                    }
                    indexing(i,j);
                }
            }
        }

        for (int j = 1; j < areaWidth - 1; j++) {                       //поиск в верх
            for (int i = 1; i < areaHeight - 1; i++) {                  //в право
                if (AreaForClearing.getAreaForClearing(i, j) == 1) {
                    if (firstCell == true) {
                        cellsForClean++;
                        firstCell =false;
                    }
                    indexing(i,j);
                }
            }
        }
    }


    public static void indexing(int x,int y){
        int i = x;
        int j = y;

        AreaForClearing.setCell(i, j, 5);

        int jDown = j+1;
        int jUp = j-1;
        int iRight = i+1;
        int iLeft = i-1;

        boolean skipDown = false;
        boolean skipUp = false;
        boolean skipRight = false;
        boolean skipLeft = false;

        if (jDown >= areaHeight-1) {
            skipDown = true;
        }
        if (jUp <= 0) {
            skipUp = true;
        }
        if (iRight >= areaWidth-1) {
            skipRight = true;
        }
        if (iLeft <= 0) {
            skipLeft = true;
        }

        if(skipRight == false){
            if (AreaForGame.getArea(i, j) == AreaForGame.getArea(iRight, j)) {                  //правее
                if (AreaForClearing.getAreaForClearing(iRight, j) < 2) {
                    AreaForClearing.setCell(iRight, j, 1);
                    cellsForClean++;
                }
            }
        }

        if(skipDown == false){
            if (AreaForGame.getArea(i, j) == AreaForGame.getArea(i, jDown)) {                     //ниже
                if (AreaForClearing.getAreaForClearing(i, jDown) < 2) {
                    AreaForClearing.setCell(i, jDown, 1);
                    cellsForClean++;
                }
            }
        }

        if(skipLeft == false){
            if (AreaForGame.getArea(i, j) == AreaForGame.getArea(iLeft, j)) {                   //левее
                if (AreaForClearing.getAreaForClearing(iLeft, j) < 2) {
                    AreaForClearing.setCell(iLeft, j, 1);
                    cellsForClean++;
                }
            }
        }
        if(skipUp == false){
            if (AreaForGame.getArea(i, j) == AreaForGame.getArea(i, jUp)) {                    //выше
                if (AreaForClearing.getAreaForClearing(i, jUp) < 2) {
                    AreaForClearing.setCell(i, jUp, 1);
                    cellsForClean++;
                }
            }
        }
    }

    private static void changeColorBeforeCleaning(){
        for ( int x = 1; x < areaWidth - 1; x++){
            for(int y = 1; y < areaHeight - 1; y++){
                if (AreaForClearing.getAreaForClearing(x,y)>0){
                    AreaForGame.setGameArea(x,y,99);
                }
            }
        }
    }

    public static void setZeroInCellsForClean(){
        cellsForClean = 0;
    }

    public static int getCellsForClean() {
        return cellsForClean;
    }
}



