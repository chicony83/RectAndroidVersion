package com.example.rects;

import android.content.res.Resources;

public class Information {

    private static boolean isGameAreaDrawable = false;

    private static String nextColorText;
    private static String blocksLeftText;
    private static String movesDoneText;

    private static String moveBackButtonText;

//    private static String movesBackOver;

    private static String manyMovesLeftText;
    private static String oneMoveLeftText;

    private static double widthScreen;
    private static double heightScreen;

    private static double heightInfoArea;
    private static double widthInfoArea;

    private static double heightGameArea;
    private static double widthGameArea;

    private static double x1NextColor;
    private static double y1NextColor;
    private static double x2NextColor;
    private static double y2NextColor;

    private static double x1BackButton;
    private static double y1BackButton;
    private static double x2BackButton;
    private static double y2BackButton;

    private static double nextColorPositionX;
    private static double nextColorPositionY;

    private static double ONEHUNDREDPERCENT = 100;      //цифра 100 для обозначения 100%

    private static int howManyRectanglesToClear = 0;    //счетчик оставшихся блоков

    private static int movesDoneValue;                  //счетчик сделаных ходов

    private static int numbersOfMovesBack;              //счетчик ходов назад

    private static final int indexMovesBack = 4;       //мифический индекс для расчета ходов назад

    public Information(){ }

    //protected void onDrawGameArea(Canvas canvas){    }

    public void setPositionsNextColor(){                    //расчет положения поля "следующий цвет"

        double oneHundredPercent = ONEHUNDREDPERCENT;
        double leftIndent = 2;
        double upIndent = 2;
        double rightIndent = 60;
        double downIndent = 25;

        x1NextColor = (widthScreen / oneHundredPercent) * leftIndent;
        y1NextColor = (heightInfoArea / oneHundredPercent) * upIndent;
        x2NextColor = (widthScreen / oneHundredPercent) * rightIndent;
        y2NextColor = (heightInfoArea / oneHundredPercent) * downIndent;
        nextColorPositionX = x1NextColor;
        nextColorPositionY = y1NextColor;

    }
    public void SetPositionMoveBackButton(){                //расчет положения кнопки "назад"

        double oneHundredPercent = ONEHUNDREDPERCENT;
        double widthBackButtonArea = SettingGame.getBackButtonAreaWidth();
        double heightBackButtonArea = SettingGame.getBackButtonAreaHeight();

        double indent = 10;

        double leftIndent = indent;
        double upIndent = indent;
        double rightIndent = indent;
        double downIndent = indent;

        x1BackButton = ( widthScreen / oneHundredPercent) * leftIndent;
        y1BackButton = heightInfoArea + heightGameArea +
                ((heightBackButtonArea/oneHundredPercent) * upIndent);

        x2BackButton = ( widthBackButtonArea -
                (( widthBackButtonArea / oneHundredPercent ) * rightIndent ));

        y2BackButton = heightInfoArea + heightGameArea + ( heightBackButtonArea -
                (( heightBackButtonArea / oneHundredPercent ) * downIndent ));
    }

    public static double getX1NextColor() {
        return x1NextColor;
    }

    public static double getX2NextColor() {
        return x2NextColor;
    }

    public static double getY1NextColor() {
        return y1NextColor;
    }

    public static double getY2NextColor() {
        return y2NextColor;
    }

    public static double getX1BackButton() {
        return x1BackButton;
    }

    public static double getX2BackButton() {
        return x2BackButton;
    }

    public static double getY1BackButton() {
        return y1BackButton;
    }

    public static double getY2BackButton() {
        return y2BackButton;
    }

    public static int getHowManyRectanglesToClear() {
        return howManyRectanglesToClear;
    }

    public static void setHowManyRectanglesToClear(int h) {
        howManyRectanglesToClear = h;
    }

    public static void addMove(){
        movesDoneValue++;
//        Log.i(" TAG ", "movesDoneValue"+ movesDoneValue);
    }
    public static int getMovesDoneValue() {
        return movesDoneValue;
    }

    public static void setMovesDoneValue(int movesDoneValue) {
        Information.movesDoneValue = movesDoneValue;
    }
    public static void setZEROMovesDoneValue() {
        Information.movesDoneValue = 0;
    }
    public static void movesDoneValueResetCounter() {
        Information.movesDoneValue = 0;
    }

    public void dataPreparation() {
        widthScreen = SettingGame.getWidthDisplay();
        heightScreen = SettingGame.getHeightDisplay();
        widthInfoArea = SettingGame.getInformationAreaWidth();
        heightInfoArea = SettingGame.getInformationAreaHeight();
        widthGameArea = SettingGame.getWIDTH()*SettingGame.getSideSizeOfRect();
        heightGameArea = SettingGame.getHEIGHT()*SettingGame.getSideSizeOfRect();
    }
    public static void calculationOfTheNumberOfMovesBack(){
        numbersOfMovesBack = 5;
    }

    public static int getNumbersOfMovesBack() {
        return numbersOfMovesBack;
    }

    public static void incNumbersOfMovesBack() {
        numbersOfMovesBack--;
    }

    public static void setValuesInformationStrings(
            String nextColorText, String blocksLeftText, String movesDoneText,
            String moveBackButtonText, String manyMovesLeftText, String oneMoveLeftText) {

        Information.nextColorText = nextColorText;
        Information.blocksLeftText = blocksLeftText;
        Information.movesDoneText = movesDoneText;
        Information.moveBackButtonText = moveBackButtonText;
        Information.manyMovesLeftText = manyMovesLeftText;
        Information.oneMoveLeftText = oneMoveLeftText;

    }

    public static String getNextColorText() {
        return nextColorText;
    }
    public static String getBlocksLeftText() {
        return blocksLeftText;
    }

    public static String getMovesDoneText() {
        return movesDoneText;
    }
    public static String getMoveBackButtonText() {
        return moveBackButtonText;
    }

    public static String getManyMovesLeftText() {
        return manyMovesLeftText;
    }

    public static String getOneMoveLeftText() {
        return oneMoveLeftText;
    }

    public static boolean isIsGameAreaDrawable() {
        return isGameAreaDrawable;
    }

    public static void setIsGameAreaDrawable(boolean isGameAreaDrawable) {
        Information.isGameAreaDrawable = isGameAreaDrawable;
    }

}
