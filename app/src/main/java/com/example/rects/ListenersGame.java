package com.example.rects;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ListenersGame {

    private static boolean isLinePressed = false;

    private static int xPressed;
    private static int yPressed;

    public static View.OnTouchListener handleTouch = new View.OnTouchListener() {     //слушатель игрового поля

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int widthInfoArea = SettingGame.getInformationAreaWidth();
            int heightInfoArea = SettingGame.getInformationAreaHeight();

            int x = (int) event.getX();
            int y = (int) event.getY();

            int newX = (int) event.getX();
            int newY = (int) event.getY();

            int rectSize = SettingGame.getSideSizeOfRect();
            int areaWidth = SettingGame.getWIDTH();
            int areaHeight = SettingGame.getHEIGHT();

            int xMove = 0;
            int yMove = 0;

            xPressed = 0;
            yPressed = 0;

            int x1BB = (int) Information.getX1BackButton();
            int y1BB = (int) Information.getY1BackButton();
            int x2BB = (int) Information.getX2BackButton();
            int y2BB = (int) Information.getY2BackButton();

//-------------обработка нажатий игрового поля---------
            if (Information.isIsGameAreaDrawable()) {
                if (SettingCurrentGame.isGameLoose() == false) {
                    if (y > heightInfoArea & y < heightInfoArea + (rectSize * areaHeight)) {
                        if (x > 0 & x < rectSize * areaWidth) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:

                                    isLinePressed = true;
//                                    Log.i("TAG", "DOWN: (" + x + ", " + y + ")");
                                    xPressed = x;
                                    yPressed = y;
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    Log.i("TAG", "moving: (" + x + ", " + y + ")");

                                    isLinePressed = true;

                                    if ((x != newX) | (y != newY)) {

                                        x = (int) event.getX();
                                        y = (int) event.getY();
                                    }
                                    if ((y > heightInfoArea) & (y < heightInfoArea + (rectSize * areaHeight))) {
                                        if ((x > 0) & x < (rectSize * areaWidth)) {
                                            xPressed = x;
                                            yPressed = y;
                                        }
                                    }
                                    break;

                                case MotionEvent.ACTION_UP:

                                    isLinePressed = false;
                                    if (y > heightInfoArea & y < heightInfoArea + (rectSize * areaHeight)) {
                                        if (x > 0 & x < rectSize * areaWidth) {
                                            xPressed = x;
                                            yPressed = y;
                                        }
                                    }

                                    if (x > rectSize & x < ((rectSize * areaWidth) - rectSize)) {
                                        if (y > heightInfoArea & y < (rectSize + heightInfoArea)) {                                     //нажатие сверху
                                            xMove = x;
                                            new Move().moveUpToDown(xMove);
                                        }
                                        if (y > (((rectSize * areaHeight) - rectSize) + heightInfoArea) &
                                                y < ((rectSize * areaHeight) + heightInfoArea)) {
                                            xMove = x;
                                            new Move().moveDownToUp(xMove);
                                        }
                                    }
                                    if (y > (rectSize + heightInfoArea) &
                                            y < (((rectSize * areaHeight) - rectSize) + heightInfoArea)) {

                                        if (x > 0 & x < rectSize) {
                                            yMove = y - heightInfoArea;
                                            new Move().moveLeftToRight(yMove);

                                        }
                                        if (x > (rectSize * areaWidth) - rectSize & x < rectSize * areaWidth) {
                                            yMove = y - heightInfoArea;
                                            new Move().moveRightToLeft(yMove);
                                        }
                                    }
                                    break;
                            }
                        }
                    }
//----------обработка нажатия кнопки ход назад----------
                    if (Information.getMovesDoneValue() > 0 & MoveBack.isBackwardAllowed()) {
                        if (Information.getNumbersOfMovesBack() > 0) {
                            if (y > y1BB & y < y2BB) {
                                if (x > x1BB & x < x2BB) {

                                    switch (event.getAction()) {

                                        case MotionEvent.ACTION_DOWN:
                                            Log.i("TAG_buttonBack", "DOWN: (" + x + ", " + y + ")");
                                            break;
                                        case MotionEvent.ACTION_UP:
                                            Log.i("TAG_buttonBack", "touched up(" + x + ", " + y + ")");
                                            AreaForGame.returnArea();
                                            Information.incNumbersOfMovesBack();
                                            MoveBack.setBackwardAllowed(false);
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
//---------------обработка пустых квадратов по углам игрового поля
            if (y > heightInfoArea & y < heightInfoArea + rectSize) {
                if (x > 0 & x < rectSize) {
                    isLinePressedSetFalse();
                }
                if (x > (areaWidth * rectSize) - rectSize & x < (areaWidth * rectSize)) {
                    isLinePressedSetFalse();
                }
            }
            if ((y > ((rectSize * areaHeight) + heightInfoArea) - rectSize) &
                    y < ((rectSize * areaHeight) + heightInfoArea)) {
                if (x > 0 & x < rectSize) {
                    isLinePressedSetFalse();
                }
                if (x > (areaWidth * rectSize) - rectSize & x < (areaWidth * rectSize)) {
                    isLinePressedSetFalse();
                }
            }

            return true;
        }
    };
    private static void isLinePressedSetFalse(){
        isLinePressed = false;
    }
    public static boolean getIsLinePressed() {
        return isLinePressed;
    }

    public static int getXPressed() {
        return xPressed;
    }

    public static int getYPressed() {
        return yPressed;
    }
}
