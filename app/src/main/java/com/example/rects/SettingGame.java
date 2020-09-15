package com.example.rects;


public class SettingGame {

//ширина-высота экрана
    private static int widthDisplay;        //ширина экрана
    private static int heightDisplay;       //высота экрана

    private static final int WIDTH = 15;    //ширина игрового поля в клетках
    private static final int HEIGHT = 15;   //высота игрового поля в клетках
    private static int sideSizeOfRect;      //размер стороны прямоугольника

    private static int informationAreaWidth; //ширина информационного поля
    private static int informationAreaHeight; //высота информационного поля

    private static int backButtonAreaWidth; //ширина поля кнопки ход назад
    private static int backButtonAreaHeight; //высота поля кнопки ход назад

    private static final int nullColor = 0; //значение обозначающее пустое пространство

    private static final int minColor = 1;  //минимальное значение цвета

    //максимальные значения для уровней разных сложностей
    private static final int maxColorLight = 3;
    private static final int maxColorMedium = 4;
    private static final int maxColorHard = 5;
    private static final int maxColorImpossible = 6;

    //отступы для каждого из уровней
    private static final int spaceVeryLight = 6;
    private static final int spaceLight = 5;
    private static final int spaceMedium = 4;
    private static final int spaceHard = 3;
    private static final int spaceVeryHard = 2;


    public static int getSideSizeOfRect() {
        sideSizeOfRect = widthDisplay/WIDTH;
        return sideSizeOfRect;
    }

    public static int getWidthDisplay() {
        return widthDisplay;
    }

    public static void setWidthDisplay(int widthDisplay) {
        SettingGame.widthDisplay = widthDisplay;
    }

    public static int getHeightDisplay() {
        return heightDisplay;
    }

    public static void setHeightDisplay(int heightDisplay) {
        SettingGame.heightDisplay = heightDisplay;
    }

    public static int getInformationAreaWidth() {
        return informationAreaWidth;
    }

    public static int getInformationAreaHeight() {
        return informationAreaHeight;
    }

    public static void setInformationAreaHeight(int informationAreaHeight) {
        SettingGame.informationAreaHeight = informationAreaHeight;
    }

    public static void setInformationAreaWidth(int informationAreaWidth) {
        SettingGame.informationAreaWidth = informationAreaWidth;
    }

    public static int getBackButtonAreaHeight() {
        return backButtonAreaHeight;
    }

    public static int getBackButtonAreaWidth() {
        return backButtonAreaWidth;
    }

    public static void setBackButtonAreaHeight(int backButtonAreaHeight) {
        SettingGame.backButtonAreaHeight = backButtonAreaHeight;
    }

    public static void setBackButtonAreaWidth(int backButtonAreaWidth) {
        SettingGame.backButtonAreaWidth = backButtonAreaWidth;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getNullColor() {
        return nullColor;
    }

    public static int getMinColor() {
        return minColor;
    }

    public static int getMaxColorLight() {
        return maxColorLight;
    }

    public static int getMaxColorMedium() {
        return maxColorMedium;
    }

    public static int getMaxColorHard() {
        return maxColorHard;
    }

    public static int getSpaceVeryLight() {
        return spaceVeryLight;
    }

    public static int getSpaceLight() {
        return spaceLight;
    }

    public static int getSpaceMedium() {
        return spaceMedium;
    }

    public static int getSpaceHard() {
        return spaceHard;
    }

    public static int getSpaceVeryHard() {
        return spaceVeryHard;
    }

    public static int getMaxColorImpossible() {
        return maxColorImpossible;
    }

}
