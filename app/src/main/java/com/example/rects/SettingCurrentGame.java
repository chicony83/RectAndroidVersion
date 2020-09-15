package com.example.rects;

public class SettingCurrentGame {

    private static boolean gameLoose = false;
    private static int currentNumLVL = 0;

    public static void setGameLoose(boolean gL) {
        gameLoose = gL;
    }

    public static boolean isGameLoose() {
        return gameLoose;
    }

    public static int getCurrentNumLVL() {
        return currentNumLVL;
    }

    public static void setCurrentNumLVL(int currentNumLVL) {

        if(currentNumLVL >=1){
            SettingCurrentGame.currentNumLVL = currentNumLVL;
        }
        else {
            SettingCurrentGame.currentNumLVL = 1;
        }
    }
}
