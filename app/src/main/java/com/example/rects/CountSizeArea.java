//класс для подсчета высоты информационного и игрового поля
package com.example.rects;

import android.util.Log;

public class CountSizeArea {

    private static double widthScreen;
    private static double heightScreen;

    private static double ONEHUNDREDPERCENT = 100;

    private static double HEIGHTINFORMATIONAREAPERCENT = 20;
    private static double HEIGHTBACKBUTTONAREAPERCENT = 15;

    public static void sizeInformationArea() {
        double percent = HEIGHTINFORMATIONAREAPERCENT;
        SettingGame.setInformationAreaWidth((int) widthScreen);     //ширина инфо поля
//        Log.i("width_InformationArea", String.valueOf(SettingGame.getInformationAreaWidth()));

        double informationAreaHeight = (heightScreen/ONEHUNDREDPERCENT)*percent;    //высота инфо поля
        SettingGame.setInformationAreaHeight((int) informationAreaHeight);
//        Log.i("height_InformationArea", String.valueOf(SettingGame.getInformationAreaHeight()));
    }

    public static void sizeGameArea() {    }

    public static void sizeBackButtonArea(){
        double percent = HEIGHTBACKBUTTONAREAPERCENT;
        SettingGame.setBackButtonAreaWidth((int) widthScreen);
        double backButtonAreaHeight = (heightScreen/ONEHUNDREDPERCENT)*percent;
        SettingGame.setBackButtonAreaHeight((int) backButtonAreaHeight);
        Log.i("W/H BackButtonArea", SettingGame.getBackButtonAreaWidth() +" "+
                SettingGame.getBackButtonAreaHeight());
    }
    public static void preparationCountSize(){
        widthScreen = SettingGame.getWidthDisplay();
        heightScreen = SettingGame.getHeightDisplay();

    }



}
