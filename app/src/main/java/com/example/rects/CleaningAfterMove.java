package com.example.rects;

import android.util.Log;

public class CleaningAfterMove {
    private static boolean needClearing = false;
    private static boolean movePending = false;

    public static void clearing() {
//        Log.i("TAG", "clearing... " );
        int x, y;
//        AreaForGame.nextColor();
        Find.setZeroInCellsForClean();
        for (y = 1; y < AreaForClearing.height - 1; y++) {
            for (x = 1; x < AreaForClearing.width - 1; x++) {
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

    public static void scheduleMoveResolution() {
        movePending = true;
    }

    public static void cancelPendingMove() {
        movePending = false;
        needClearing = false;
        AreaForClearing.cleanAreaForClearing();
        Find.setZeroInCellsForClean();
    }

    public static void isNeedClearing() {
        if (!movePending) {
            return;
        }

        movePending = false;
        if (needClearing) {
            needClearing = false;
            clearing();
        }
        Move.actionAfterMove();
    }
}
