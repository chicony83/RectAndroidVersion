package com.example.rects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class AreaForGame {
    private static int widthGameArea = SettingGame.getWIDTH();      //ширина игрового поля
    private static int heightGameArea = SettingGame.getHEIGHT();    //высота игрвого поля

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

    public static void setGameArea(int x,int y,int c){
        gameArea[x][y] = c;
    }

    public static void ifThereAreMoves() {              //есть ли еще ходы
        int c = 0;
        Log.i("TAG", "widthGameArea: " + widthGameArea );
        Log.i("TAG", "heightGameArea: " + heightGameArea );

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
        Log.i("TAG", "setGameLoose: " + c );
        Log.i("TAG", "cellsToLoose: " + cellsToLoose );

        if (c==cellsToLoose){
            SettingCurrentGame.setGameLoose(true);
            Log.i("TAG", "setGameLoose: true" );
        }
    }

    public void newCleanArea(){ //обнулить уровень
        int x;
        int y;
        for (y = 0; y < widthGameArea; y++) {
            for (x = 0; x < heightGameArea; x++) {
                gameArea[x][y] = nullColor;
                Log.i("TAG",String.valueOf(nullColor));
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
        for (ySpace = space; ySpace < widthGameArea - space; ySpace++){
            for (xSpace = space; xSpace < heightGameArea - space; xSpace++) {
                Random r = new Random();
                int randomNumColor = r.nextInt(maxColor+1);
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


    private int randomColorForTargetLine(){
        int rndColForTsrgLine = randomColor();
        return rndColForTsrgLine;
    }
    public static void nextColor(){                         //значения следующего цвета
        nextColor = randomColor();
        Log.i("nextColorText", String.valueOf(nextColor));
    }
    private static int randomColor(){
        Random r = new Random();
        int rC = r.nextInt(maxColor)+1;
        return rC;
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
        Log.i("TAG", "moveBackArea:" + moveBackArea[1][0]);
    }

    public static void returnArea(){                    //вернуть ход
        for (int x = 0;x<widthGameArea;x++) {
            for (int y = 0; y < heightGameArea; y++) {
                gameArea[x][y] = moveBackArea[x][y];
            }
        }
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
