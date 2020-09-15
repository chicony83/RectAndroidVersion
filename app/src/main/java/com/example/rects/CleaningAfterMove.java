package com.example.rects;

import android.util.Log;

public class CleaningAfterMove {
    private static boolean needClearing = false;

    public static void clearing() {
//        Log.i("TAG", "clearing... " );
        int x, y;
//        AreaForGame.nextColor();
        Find.setZeroInCellsForClean();
        for (y = 1; y < AreaForClearing.width - 1; y++) {
            for (x = 1; x < AreaForClearing.height - 1; x++) {
//                Log.i("TAG", "finding... x" + x + " y " + y );
                if (AreaForClearing.getAreaForClearing(x, y) > 0) {
//                    Log.i("TAG", "clearing...finding... x" + x + " y " + y );
                    AreaForClearing.setZeroCell(x, y);
                    AreaForGame.setGameArea(x, y, 0);
                }
            }
        }
    }

    public static void setNeedClearing(boolean nC) {
        needClearing = nC;
    }

    public static void isNeedClearing() {
        Log.i("TAG", "isNeedClearing " + needClearing);
        if (needClearing == true) {
            needClearing = false;

            Log.i("TAG", "isNeedClearing " + needClearing);
            Log.i("TAG", "isNeedClearing нужно очистить");
            clearing();
        }
        Move.actionAfterMove();
    }
}
