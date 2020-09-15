//класс создает настройки текущего уровня

package com.example.rects;

public class SettingLevels {

    private static int currentNumLevel;     //номер уровня

    private static int currentMinColor = 0;
    private static int currentMaxColor = 0;
    private static int currentSpace = 0;

    public static void setCurrentSettingLevel(int c) {
        currentNumLevel = c;                                    //номер текуцего уровня
        currentMinColor = SettingGame.getMinColor();            //минимельный цвет текущего уровня

        if(currentNumLevel == 1){
            currentMaxColor = SettingGame.getMaxColorLight();   //максимальный цвет текущего уровня
            currentSpace = SettingGame.getSpaceVeryLight();     //отступы текущего уровня
        }
        if(currentNumLevel == 2){
            currentMaxColor = SettingGame.getMaxColorLight();
            currentSpace = SettingGame.getSpaceLight();
        }
        if(currentNumLevel == 3){
            currentMaxColor = SettingGame.getMaxColorMedium();
            currentSpace = SettingGame.getSpaceMedium();
        }
        if(currentNumLevel == 4){
            currentMaxColor = SettingGame.getMaxColorMedium();
            currentSpace = SettingGame.getSpaceHard();
        }
        if (currentNumLevel == 5){
            currentMaxColor = SettingGame.getMaxColorMedium();
            currentSpace = SettingGame.getSpaceVeryHard();
        }
        if (currentNumLevel == 6){
            currentMaxColor = SettingGame.getMaxColorHard();
            currentSpace = SettingGame.getSpaceVeryHard();
        }
        if (currentNumLevel == 7){
            currentMaxColor = SettingGame.getMaxColorImpossible();
            currentSpace = SettingGame.getSpaceHard();
        }

        SettingLevels.currentMaxColor = currentMaxColor;
    }

    public static int getCurrentMaxColor() {
        return currentMaxColor;
    }

    public static int getCurrentMinColor() {
        return currentMinColor;
    }

    public static int getCurrentSpace() {
        return currentSpace;
    }

    public static int getCurrentNumLevel() {
        return currentNumLevel;
    }
}
