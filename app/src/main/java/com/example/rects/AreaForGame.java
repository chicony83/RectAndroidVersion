package com.example.rects;

import com.example.rects.game.config.SettingCurrentGame;
import com.example.rects.game.config.SettingGame;
import com.example.rects.game.config.SettingLevels;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class AreaForGame {
    private static final Random RANDOM = new Random();
    private static final Object STATE_LOCK = new Object();

    private static final int widthGameArea = SettingGame.getWIDTH();      //ширина игрового поля
    private static final int heightGameArea = SettingGame.getHEIGHT();    //высота игрвого поля

    private static int nextColor = SettingLevels.getCurrentMaxColor();                                   //следующий цвет
    private static int backNextColor;

    private static int numOfCells = 0;

    private int nullColor = SettingGame.getNullColor();             //нулевой цвет

    private static int gameArea[][] = new int[widthGameArea][heightGameArea]; //игровое поле

    private static int moveBackArea[][] = new int[widthGameArea][heightGameArea];

//    private

    private int minColor;
    private static int maxColor;
    private int space;
    Paint p = new Paint();

    Bitmap bitmap;
    private static int cellsToLoose;

    public AreaForGame(){
        cellsToLoose = (((widthGameArea -2)+(heightGameArea -2))*2)-4;  //подсчет количества клеток
                                                                        // для завершения игры с проигрышем
        Log.i("TAG","cellsToLoose " + cellsToLoose);
    }

    public static int getArea(int x, int y) {
    //    Log.i("gameArea_x", String.valueOf(x));
    //    Log.i("gameArea_y", String.valueOf(y));
    //    Log.i("gameArea[x][y]", String.valueOf(gameArea[x][y]));

        return gameArea[x][y];
    }

    public static Object getStateLock() {
        return STATE_LOCK;
    }

    public static void setGameArea(int x,int y,int c){
        gameArea[x][y] = c;
    }

    public static void ifThereAreMoves() {              //есть ли еще ходы
        int c = 0;
        for(int x1 = 1; x1 < (widthGameArea - 1) ; x1++){
            if(gameArea[x1][1] != 0) {                  //проверка верхней линии
                c++;
            }
            if(gameArea[x1][heightGameArea -2]!= 0 ){   //проверка нижней линии
                c++;
            }
        }
        for (int y1 = 2; y1<(heightGameArea -2); y1++){ //проверка левой линии
            if(gameArea[1][y1]!=0){
                c++;
            }
            if(gameArea[widthGameArea -2][y1] !=0){     //проверка правой линии
                c++;
            }
        }
        SettingCurrentGame.setGameLoose(c >= cellsToLoose);
    }

    public void newCleanArea(){ //обнулить уровень
        int x;
        int y;
        for (y = 0; y < heightGameArea; y++) {
            for (x = 0; x < widthGameArea; x++) {
                gameArea[x][y] = nullColor;
            }
        }
    }

    //public static void newCleanAreaWithoutTargetLine(){ //обнулить уровень
    //    int x;
    //    int y;
    //    for (y = 1; y < widthGameArea-1; y++) {
    //        for (x = 1; x < heightGameArea-1; x++) {
    //            gameArea[x][y] = 0;
    //        }
    //    }
    //}

    public void newGameLevelValues(){ //создаем уровень со значениями
        int numLevel = SettingLevels.getCurrentNumLevel();
        int x;
        int y;
        int xSpace;
        int ySpace;
        Log.i("num_level_in_area", String.valueOf(numLevel));
        minColor = SettingLevels.getCurrentMinColor();           //минимальный цвет
        maxColor = SettingLevels.getCurrentMaxColor();
        space = SettingLevels.getCurrentSpace();

//        Log.i(" maxColor ", String.valueOf(maxColor));
//        Log.i(" minColor ", String.valueOf(minColor));
//        Log.i(" interval ", String.valueOf(interval));
//        Log.i(" space ", String.valueOf(space));

        //значения отвечают за цвета на поле
        for (ySpace = space; ySpace < heightGameArea - space; ySpace++){
            for (xSpace = space; xSpace < widthGameArea - space; xSpace++) {
                int randomNumColor = RANDOM.nextInt(maxColor + 1);
//                Log.i(" rectColor ", String.valueOf(randomNumColor));
                setColorInRectGameArea(xSpace,ySpace,randomNumColor);
            }
        }
    }

    public static int getColorOfRectGameArea(int x, int y) {
//                   Log.i(" x ", String.valueOf(x));
//                   Log.i(" y ", String.valueOf(y));
//                   Log.i(" gameArea[x][y] ", String.valueOf(gameArea[x][y]));

        return gameArea[x][y];
    }
    public void setColorInRectGameArea(int x, int y, int c){
        gameArea[x][y] = c;
    }

    public void newTargetLines() {

        for(int x = 1 ; x < widthGameArea-1 ; x++){

            //значения верхних линий
            int r = randomColorForTargetLine();
//            Log.i(" rndUpColorTargetLine ", String.valueOf(r));
            gameArea[x][0] = r;

            //значения нижних линий
            r = randomColorForTargetLine();
//            Log.i("rndDownColorTargetLine", String.valueOf(r));
            gameArea[x][heightGameArea-1] = r;
        }
        for(int y = 1; y< heightGameArea-1 ; y++){
            //значения левых линий
            int r = randomColorForTargetLine();
            gameArea[0][y] = r;
            //значения правых линий
            r = randomColorForTargetLine();
            gameArea[widthGameArea-1][y] = r;
        }
    }
    public static void setAreaRandomNumToCell(int x, int y){
        gameArea[x][y] = getNextColor();
    }

    public static int getNextColor() {
        return nextColor;
    }

    public static int getBackNextColor() {
        return backNextColor;
    }

    public static int[][] copyGameArea() {
        return copyArea(gameArea);
    }

    public static int[][] copyMoveBackArea() {
        return copyArea(moveBackArea);
    }

    public static void restoreSavedState(int[][] savedGameArea, int[][] savedMoveBackArea,
                                         int savedNextColor, int savedBackNextColor) {
        if (savedGameArea.length != widthGameArea || savedMoveBackArea.length != widthGameArea) {
            throw new IllegalArgumentException("Saved game has an invalid width");
        }

        for (int x = 0; x < widthGameArea; x++) {
            if (savedGameArea[x].length != heightGameArea ||
                    savedMoveBackArea[x].length != heightGameArea) {
                throw new IllegalArgumentException("Saved game has an invalid height");
            }
            System.arraycopy(savedGameArea[x], 0, gameArea[x], 0, heightGameArea);
            System.arraycopy(savedMoveBackArea[x], 0, moveBackArea[x], 0, heightGameArea);
        }

        nextColor = savedNextColor;
        backNextColor = savedBackNextColor;
        CleaningAfterMove.cancelPendingMove();
    }

    private static int[][] copyArea(int[][] source) {
        int[][] copy = new int[widthGameArea][heightGameArea];
        for (int x = 0; x < widthGameArea; x++) {
            System.arraycopy(source[x], 0, copy[x], 0, heightGameArea);
        }
        return copy;
    }


    private int randomColorForTargetLine(){
        int rndColForTsrgLine = randomColor();
        return rndColForTsrgLine;
    }
    public static void nextColor(){                         //значения следующего цвета
        nextColor = randomColor();
        Log.i("nextColorText", String.valueOf(nextColor));
    }
    private static int randomColor(){
        return RANDOM.nextInt(maxColor) + 1;
    }

    public static void countHowManyRectanglesToClear(){
        int widthGameArea = SettingGame.getWIDTH();
        int heightGameArea = SettingGame.getHEIGHT();
        int count = 0;
        for (int x = 1 ; x < widthGameArea - 1; x++){
            for (int y = 1 ; y < heightGameArea - 1; y++){
                if(gameArea[x][y]>0){
                    count++;
                }
            }
        }
        Information.setHowManyRectanglesToClear(count);
    }

    public static void saveMove() {                     //сохранить ход
        for (int x = 0;x<widthGameArea;x++) {
            for (int y = 0; y < heightGameArea; y++) {
                moveBackArea[x][y] = gameArea[x][y];
            }
        }
        backNextColor = nextColor;
    }

    public static void returnArea(){                    //вернуть ход
        for (int x = 0;x<widthGameArea;x++) {
            for (int y = 0; y < heightGameArea; y++) {
                gameArea[x][y] = moveBackArea[x][y];
            }
        }
        nextColor = backNextColor;
        CleaningAfterMove.cancelPendingMove();
        Information.removeMove();
        countHowManyRectanglesToClear();
        AreaForGrayTargetLine.grayLineSearch();
        ifThereAreMoves();
        MoveBack.setBackwardAllowed(false);             //сообщает что ход назад выполнен
    }

    public static void newMoveBackArea(){
        for (int x = 1;x<widthGameArea - 1;x++) {
            for (int y = 1; y < heightGameArea - 1; y++) {
                moveBackArea[x][y] = 0;
            }
        }

    }
}
