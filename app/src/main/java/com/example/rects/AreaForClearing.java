package com.example.rects;

public class AreaForClearing {

    public static final int width = SettingGame.getWIDTH();
    public static final int height = SettingGame.getHEIGHT();

    private static int lastMoveX;
    private static int lastMoveY;
    private static int lastColor;


    private static int areaForClearing[][] = new int[width][height];

    public static void cleanAreaForClearing(){ //обнулить уровень
        int x;
        int y;
        for (y = 0; y < width; y++) {
            for (x = 0; x < height; x++) {
                areaForClearing[x][y] = 0;
            }
        }
    }

    public static void setLastMove(int lastX,int lastY) {
        areaForClearing[lastX][lastY] = 1;

        lastMoveX = lastX;
        lastMoveY = lastY;
    }

    public static void setAddCell(int x, int y, int c){
        areaForClearing[x][y] = c;

    }
    public static void setCell(int x, int y, int c){
        areaForClearing[x][y] = c;
    }

    public static void setZeroCell(int x,int y){
        areaForClearing[x][y] = 0;
    }
    public static int getAreaForClearing(int x,int y){
        return areaForClearing[x][y];
    }

    public static int getLastMoveX(int lastX) { return lastMoveX; }
    public static int getLastMoveY(int lastY) {
        return lastMoveY;
    }

}
