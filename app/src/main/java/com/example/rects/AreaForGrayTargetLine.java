package com.example.rects;

public class AreaForGrayTargetLine {
    private static int widthGameArea = SettingGame.getWIDTH();      //ширина игрового поля
    private static int heightGameArea = SettingGame.getHEIGHT();    //высота игрвого поля

    private static boolean areaGrayTargetLine[][] = new boolean[widthGameArea][heightGameArea];

    private static boolean grayLine;

    public static void grayLineSearch(){

        for (int y = 1 ; y < heightGameArea - 1 ; y++){             //подготовка левой и правой линий
            A:
            for (int x = 1 ; x < widthGameArea - 1 ; x++){
                if(AreaForGame.getArea(x,y)>0){
                    grayLine = false;
                    areaGrayTargetLine[0][y] = grayLine;
                    areaGrayTargetLine[widthGameArea-1][y] = grayLine;
                    break A;
                }else{
                    grayLine = true;
                    areaGrayTargetLine[0][y] = grayLine;
                    areaGrayTargetLine[widthGameArea-1][y] = grayLine;
                }

            }
        }

        for (int x = 1 ; x < widthGameArea - 1 ; x++){              //подготовка верхней и нижней в линий
            A:
            for (int y = 1 ; y < heightGameArea - 1; y++){
                if (AreaForGame.getArea(x,y)>0){
                    grayLine = false;
                    areaGrayTargetLine[x][0] = grayLine;
                    areaGrayTargetLine[x][heightGameArea-1] = grayLine;
                    break A;
                }else {
                    grayLine = true;
                    areaGrayTargetLine[x][0] = grayLine;
                    areaGrayTargetLine[x][heightGameArea-1] = grayLine;
                }
            }
        }
    }

    public static boolean getAreaGrayTargetLine(int x, int y) {
        return areaGrayTargetLine[x][y];
    }
}
