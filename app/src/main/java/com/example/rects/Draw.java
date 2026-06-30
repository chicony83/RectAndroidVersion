package com.example.rects;

import com.example.rects.game.config.SettingCurrentGame;
import com.example.rects.game.config.SettingGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

import static android.graphics.Color.argb;

public abstract class Draw extends Context {
    private static boolean isLevelStarting = false;

    private static boolean firstDraw = false;

    static Paint p = new Paint();

    private static int red = Colors.RED;
    private static int green = Colors.GREEN;
    private static int blue = Colors.BLUE;
    private static int yellow = Colors.YELLOW;
    private static int violet = Colors.VIOLET;
    private static int cyan = Colors.CYAN;

    private static int redGray = Colors.REDgRAY;
    private static int greenGray = Colors.GREENgRAY;
    private static int blueGray = Colors.BLUEgRAY;
    private static int yellowGray = Colors.YELLOWgRAY;
    private static int violetGray = Colors.VIOLETgRAY;
    private static int cyanGray = Colors.CYANgRAY;

    private static int splashScreenGray = Colors.BACKGROUND;

    private static int silver = Colors.SILVER;

    private static int background = Colors.BACKGROUND;

    private static final int HUD_CARD = Color.rgb(20, 31, 42);
    private static final int HUD_BORDER = Color.rgb(42, 57, 70);
    private static final int TEXT_PRIMARY = Color.rgb(238, 242, 244);
    private static final int TEXT_SECONDARY = Color.rgb(165, 176, 186);
    private static final int TEXT_MUTED = Color.rgb(105, 119, 131);
    private static final RectF SHAPE = new RectF();

    private static int widthScreen = SettingGame.getWidthDisplay();
    private static int heightScreen = SettingGame.getHeightDisplay();

    private static int size = SettingGame.getSideSizeOfRect();
    private static int width = SettingGame.getWIDTH();
    private static int height = SettingGame.getHEIGHT();

    private static int heightInfoArea = SettingGame.getInformationAreaHeight();

    private static int x1BackButton = (int) Information.getX1BackButton();
    private static int y1BackButton = (int) Information.getY1BackButton();
    private static int x2BackButton = (int) Information.getX2BackButton();
    private static int y2BackButton = (int) Information.getY2BackButton();

    private static double widthBackButtonText;
    private static double backButtonTextX;
    private static double backButtonTextY;

    private static double x1NextCol = Information.getX1NextColor();
    private static double y1NextCol = Information.getY1NextColor();
    private static double x2NextCol = Information.getX2NextColor();
    private static double y2NextCol = Information.getY2NextColor();

    private static double nextColorTextX;
    private static double nextColorTextY;

    private static double blocksLeftTextX;
    private static double blocksLeftTextY;

    private static double widthNextColorText;
    private static double heightNextColorText;

    private static double textSize = 20;
    private static double textSizeBlocksLeft;
    private static double textSizeBackButton;

    private static int percentCounter = 0;

    private static int numbersOfMovesBack;

    private static String nextColorText;
    private static String blocksLeftText;
    private static String movesDoneText;
    private static String manyMovesLeftText;
    private static String oneMoveLeftText;
    private static String moveBackButtonText;

    private static String blocksLeftNumber;

    //----------рисование игрового пространства во время игры----------

    public static void onDrawGameArea(Canvas canvas) {
        onDrawGameAreaLocked(canvas);
    }

    private static void onDrawGameAreaLocked(Canvas canvas) {

        numbersOfMovesBack = Information.getNumbersOfMovesBack();

        nextColorText = Information.getNextColorText();
        blocksLeftText = Information.getBlocksLeftText();
        movesDoneText = Information.getMovesDoneText();
        manyMovesLeftText = Information.getManyMovesLeftText();
        oneMoveLeftText = Information.getOneMoveLeftText();
        moveBackButtonText = Information.getMoveBackButtonText();

        blocksLeftNumber = String.valueOf(Information.getHowManyRectanglesToClear());

        double widthScreenD = widthScreen;

        double percentDraw = (heightScreen / 100) * percentCounter;

        textSizeBlocksLeft = widthScreenD / textSize;         //определяем размер шрифта

        String gameOver = "GAME OVER";

        double gameOverTextX;
        double gameOverTextY;

        double widthGameOverText;

//---------координаты кнопки назад--------
//        Log.i("TAG_buttonBack", "x1BackButton " + x1BackButton+ " y1BackButton " + y1BackButton + " x2BackButton " + x2BackButton + " y2BackButton " +y2BackButton);

        drawBackground(canvas);
//        Log.i("TAG", "countHowManyRectanglesToClear" + Information.getHowManyRectanglesToClear());
//---------рисование поля следующего цвета------
        drawHud(canvas);
//------------отображение текста "блоков осталось (число)"

//------------отображение текста "ходов сделано (число)---------
//------------рисование игрового поля-------------
        int xPressed = ListenersGame.getXPressed();
        int yPressed = ListenersGame.getYPressed();

        int x1Pressed, y1Pressed, x2Pressed, y2Pressed;
        long curTime = GameActivity.getTime();
        long moveTime = Move.getMoveTime();
        long elapsedTime;

        xPressed = xPressed / size;
        yPressed = (yPressed - heightInfoArea) / size;

        elapsedTime = curTime - moveTime;

        if (Information.getHowManyRectanglesToClear() > 0) {    //если на игровом поле есть квадраты

            drawRectanglesArea(canvas);

            drawTargetLines(canvas);

            if (SettingCurrentGame.isGameLoose()) {       //когда проиграл

                float gameOverTextSize = 60;
                int koef = 3;

//                AreaForGame.newCleanAreaWithoutTargetLine();
//                SettingCurrentGame.setGameLoose(false);
                p.setColor(background);
                drawRect(canvas, size * koef, (size * koef) + heightInfoArea, widthScreen - (size * koef), (size * height) + (heightInfoArea) - (size * koef));
                p.setColor(red);

                p.setTextSize(gameOverTextSize);
                widthGameOverText = p.measureText(gameOver);
                gameOverTextX = widthScreen / 2;
                gameOverTextX = gameOverTextX - (widthGameOverText / 2);
                gameOverTextY = (heightInfoArea + ((size * height) / 2));
                canvas.drawText(gameOver, (float) gameOverTextX, (float) gameOverTextY, p);
                Log.i(" TAG ", "YouLOOSE!!!");

            }
        }

        if (Information.getHowManyRectanglesToClear() <= 0) {       //когда уровень очищен

        }

//------------рисование нажатых квадратов-------------

        if (ListenersGame.getIsLinePressed() == true) {
            if (yPressed >= 0 & yPressed < height) {
                if (xPressed >= 0 & xPressed < width) {
                    x1Pressed = (xPressed * size);
                    y1Pressed = (yPressed * size) + heightInfoArea;
                    x2Pressed = x1Pressed + size;
                    y2Pressed = (y1Pressed + size);
                    Log.i("TAG", "getLinePressed: (x" + xPressed + ", y" + yPressed + ")");
                    int colorPressed = AreaForGame.getArea(xPressed, yPressed);
                    if (colorPressed == 1) {
                        p.setColor(red);
                    }
                    if (colorPressed == 2) {
                        p.setColor(green);
                    }
                    if (colorPressed == 3) {
                        p.setColor(blue);
                    }
                    if (colorPressed == 4) {
                        p.setColor(yellow);
                    }
                    if (colorPressed == 5) {
                        p.setColor(violet);
                    }
                    drawPressedRect(canvas, x1Pressed, y1Pressed, x2Pressed, y2Pressed, xPressed, yPressed);
                }
            }
        }
//------------рисование кнопки назад------------------
        drawBackButton(canvas);

        if (!Information.isIsGameAreaDrawable()) {
            startLevelSplashScreen(canvas, percentDraw);
        }
    }

    private static void drawHud(Canvas canvas) {
        float margin = widthScreen * 0.035f;
        float cardTop = margin;
        float cardBottom = heightInfoArea - margin;
        float cardRadius = widthScreen * 0.025f;

        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        p.setColor(HUD_CARD);
        SHAPE.set(margin, cardTop, widthScreen - margin, cardBottom);
        canvas.drawRoundRect(SHAPE, cardRadius, cardRadius, p);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(Math.max(2f, widthScreen / 420f));
        p.setColor(HUD_BORDER);
        canvas.drawRoundRect(SHAPE, cardRadius, cardRadius, p);
        p.setStyle(Paint.Style.FILL);

        float labelSize = widthScreen / 26f;
        float valueSize = widthScreen / 15f;
        float leftX = margin * 2f;
        float rightX = widthScreen * 0.43f;
        float labelTop = cardTop + (cardBottom - cardTop) * 0.23f;

        setTextStyle(labelSize, TEXT_SECONDARY, Typeface.NORMAL);
        canvas.drawText(nextColorText, leftX, labelTop, p);

        float swatchSize = Math.min(widthScreen * 0.14f, (cardBottom - cardTop) * 0.42f);
        float swatchTop = labelTop + labelSize * 0.65f;
        setRectColor(AreaForGame.getNextColor());
        SHAPE.set(leftX, swatchTop, leftX + swatchSize, swatchTop + swatchSize);
        canvas.drawRoundRect(SHAPE, swatchSize * 0.18f, swatchSize * 0.18f, p);

        setTextStyle(labelSize, TEXT_SECONDARY, Typeface.NORMAL);
        canvas.drawText(blocksLeftText, rightX, labelTop, p);
        setTextStyle(valueSize, TEXT_PRIMARY, Typeface.NORMAL);
        canvas.drawText(blocksLeftNumber, rightX, labelTop + valueSize * 0.95f, p);

        float movesLabelY = cardTop + (cardBottom - cardTop) * 0.70f;
        setTextStyle(labelSize, TEXT_SECONDARY, Typeface.NORMAL);
        canvas.drawText(movesDoneText, rightX, movesLabelY, p);
        setTextStyle(valueSize * 0.78f, TEXT_PRIMARY, Typeface.NORMAL);
        canvas.drawText(String.valueOf(Information.getMovesDoneValue()),
                rightX, movesLabelY + valueSize * 0.78f, p);
    }

    private static void setTextStyle(float size, int color, int style) {
        p.setStyle(Paint.Style.FILL);
        p.setTypeface(Typeface.create("sans-serif", style));
        p.setTextSize(size);
        p.setColor(color);
    }

    private static void drawBlockLeftArea(Canvas canvas) {
        blocksLeftTextX = x1NextCol * 2;
        blocksLeftTextY = (y2NextCol * 2) - (y2NextCol / 4);

        blocksLeftText = blocksLeftText + " " + blocksLeftNumber;

        canvas.drawText(blocksLeftText, (float) blocksLeftTextX, (float) blocksLeftTextY, p);
    }

    private static void drawTargetLines(Canvas canvas) {
        //    for (int x = 1 ; x < width-1 ; x++){              //верхнюю и нижнюю лини
        //        int yUp = 0;
        //        int yDown = height-1;
        //        int grayRectColor = 6;
        //        int rectColorTargetLineUp = AreaForGame.getColorOfRectGameArea(x,yUp);
        //        int rectColorTargetLineDown = AreaForGame.getColorOfRectGameArea(x,yDown);
//
        //        if(AreaForGrayTargetLine.getAreaGrayTargetLine(x,yUp)){
        //            rectColorTargetLineUp = grayRectColor;
        //            rectColorTargetLineDown = grayRectColor;
        //        }
        //        createRectGameElement(canvas,x,yUp,rectColorTargetLineUp);
        //        createRectGameElement(canvas,x,yDown,rectColorTargetLineDown);
//
        //    }
        //    for (int y = 0 ; y < height ; y++){             //левую и правую линии
        //        int xLeft = 0;
        //        int xRight = width - 1;
        //        int grayRectColor = 6;
        //        int rectColorTargetLineLeft = AreaForGame.getColorOfRectGameArea(xLeft,y);
        //        int rectColorTargetLineRight = AreaForGame.getColorOfRectGameArea(xRight,y);
//
        //        if (AreaForGrayTargetLine.getAreaGrayTargetLine(xLeft,y)){
        //            rectColorTargetLineLeft = grayRectColor;
        //            rectColorTargetLineRight = grayRectColor;
        //        }
        //        createRectGameElement(canvas,xLeft,y,rectColorTargetLineLeft);
        //        createRectGameElement(canvas,xRight,y,rectColorTargetLineRight);
//
        //    }

    }

    private static void drawMap(Canvas canvas) {

        double x1Map, y1Map, x2Map, y2Map;
        double widthMap, heightMap;

        double x1RectOnMap, y1RectOnMap, x2RectOnMap, y2RectOnMap;

        double widthDotOfRectInMap, heightDotOfRectInMap;

        heightMap = heightInfoArea - 5;
        widthMap = heightMap;
        x2Map = widthScreen - 5;
        y2Map = heightMap;
        x1Map = x2Map - widthMap;
        y1Map = y2Map - heightMap;

        widthDotOfRectInMap = widthMap / width - 1;
        heightDotOfRectInMap = heightMap / height - 1;

        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GRAY);
        canvas.drawRect((float) x1Map, (float) y1Map, (float) x2Map, (float) y2Map, p);

        p.setStyle(Paint.Style.FILL);
        p.setColor(background);
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int l = AreaForGame.getColorOfRectGameArea(x, y);
                if (l > 0) {

                    setRectColor(l);

                    x1RectOnMap = (x * widthDotOfRectInMap) + x1Map;
                    y1RectOnMap = (y * heightDotOfRectInMap);
                    x2RectOnMap = x1RectOnMap + widthDotOfRectInMap;
                    y2RectOnMap = y1RectOnMap + heightDotOfRectInMap;

                    drawRect(canvas, (int) x1RectOnMap, (int) y1RectOnMap, (int) x2RectOnMap, (int) y2RectOnMap);
                }
            }
        }
        p.setStyle(Paint.Style.FILL);
    }

    private static void drawNextColorArea(Canvas canvas) {
        int nextColor = AreaForGame.getNextColor();

        drawRectNextColor(canvas, x1NextCol, y1NextCol, x2NextCol, y2NextCol, nextColor);

//------------отображение текста поля "следующего цвета"------------

        p.setColor(Color.WHITE);
        p.setTextSize((float) textSizeBlocksLeft);

        widthNextColorText = p.measureText(nextColorText);      //получаем ширину текста
        nextColorTextX = (x2NextCol - x1NextCol) / 2;                       //высчитываем серидину ширины квадрата следующего цвета
        nextColorTextX = nextColorTextX - (widthNextColorText / 2); //ищем координату х

        nextColorTextY = y2NextCol - (y2NextCol / 4);

        canvas.drawText(nextColorText, (float) nextColorTextX, (float) nextColorTextY, p);
    }

    private static void drawBackButton(Canvas canvas) {
        float centerY = (y1BackButton + y2BackButton) / 2f;
        float buttonHeight = Math.min(widthScreen * 0.13f, (y2BackButton - y1BackButton) * 0.58f);
        float top = centerY - buttonHeight / 2f;
        float bottom = centerY + buttonHeight / 2f;
        float radius = buttonHeight * 0.24f;
        boolean enabled = numbersOfMovesBack > 0 && MoveBack.isBackwardAllowed();

        SHAPE.set(x1BackButton, top, x2BackButton, bottom);
        p.setStyle(Paint.Style.FILL);
        p.setColor(enabled ? HUD_CARD : Color.rgb(14, 22, 30));
        canvas.drawRoundRect(SHAPE, radius, radius, p);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(Math.max(2f, widthScreen / 420f));
        p.setColor(enabled ? HUD_BORDER : Color.rgb(28, 40, 50));
        canvas.drawRoundRect(SHAPE, radius, radius, p);

        String label = "↶  " + moveBackButtonText + "  ·  " + numbersOfMovesBack;
        setTextStyle(widthScreen / 26f, enabled ? TEXT_PRIMARY : TEXT_MUTED, Typeface.NORMAL);
        float textX = (widthScreen - p.measureText(label)) / 2f;
        Paint.FontMetrics metrics = p.getFontMetrics();
        float textY = centerY - (metrics.ascent + metrics.descent) / 2f;
        canvas.drawText(label, textX, textY, p);
    }

    private static void drawBackButtonLegacy(Canvas canvas) {

        if (numbersOfMovesBack < 0) {
//            p.setColor(Color.BLACK);
        }
        if (numbersOfMovesBack == 0) {
            p.setColor(Color.BLACK);
//            moveBackButtonText = noMovesBackText;
        }
        if (numbersOfMovesBack > 0) {
            p.setColor(Color.GRAY);
            drawRect(canvas, x1BackButton, y1BackButton, x2BackButton, y2BackButton);
            p.setColor(Color.BLACK);
            textSizeBackButton = textSize + 8;
            p.setTextSize((float) textSizeBackButton);

            if (numbersOfMovesBack == 1) {
                moveBackButtonText = moveBackButtonText + " " + oneMoveLeftText + " " + numbersOfMovesBack;
            }
            //if( numbersOfMovesBack >= 2 & numbersOfMovesBack <= 4 ){
            //    moveBackButtonText = moveBackButtonText + " " + numbersOfMovesBack+ " " + movesMass[1];
            //}
            if (numbersOfMovesBack > 1) {
                moveBackButtonText = moveBackButtonText + " " + manyMovesLeftText + " " + numbersOfMovesBack;
            }

            widthBackButtonText = p.measureText(moveBackButtonText);
//            Log.i("TAG", "widthBackButtonText " + widthBackButtonText);
            backButtonTextX = (widthScreen / 2) - (widthBackButtonText / 2);

//            Log.i("TAG", "backButtonTextX " + backButtonTextX);

            backButtonTextY = y1BackButton + ((y2BackButton - y1BackButton) / 2);

            canvas.drawText(moveBackButtonText, (float) backButtonTextX, (float) backButtonTextY, p);
        }
    }

    private static void startLevelSplashScreen(Canvas canvas, double percentDraw) {
        if (percentCounter >= 100) {
            percentCounter = 100;
            Information.setIsGameAreaDrawable(true);
            return;
        }

        p.setColor(splashScreenGray);
        canvas.drawRect(0, (int) percentDraw, widthScreen, heightScreen, p);

        percentCounter++;
        if (percentCounter >= 100) {
            Information.setIsGameAreaDrawable(true);
        }

    }

    private static void drawBackground(Canvas canvas) {
        p.setColor(background);
        canvas.drawRect(0, 0, widthScreen, heightScreen, p);
    }

    private static void drawRectanglesArea(Canvas canvas) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int l = AreaForGame.getColorOfRectGameArea(x, y);
                if (l == 0) {
                    p.setColor(Color.BLACK);
                    drawRect(canvas, x, y);
                }
                createRectGameElement(canvas, x, y, l);
            }
        }
    }

    private static void drawPressedRect(Canvas canvas, int x1,
                                        int y1, int x2, int y2, int xPressed, int yPressed) {
        int width = SettingGame.getWIDTH();
        int height = SettingGame.getHEIGHT();
        int SideSizeOfRect = SettingGame.getSideSizeOfRect();
        int currentSize = SideSizeOfRect / 2;
        if (xPressed == 0) {
//            Log.i("pressed_Left_Line", String.valueOf(xPressed));
            drawRect(canvas, x1, y1 - currentSize, x2 + currentSize, y2 + currentSize);
        }
        if (yPressed == 0) {
//            Log.i("pressed_UP_Line", String.valueOf(yPressed));
            drawRect(canvas, x1 - currentSize, y1, x2 + currentSize, y2 + currentSize);
        }
        if (xPressed == width - 1) {
//            Log.i("pressed_Right_Line", String.valueOf(xPressed));
            drawRect(canvas, x1 - currentSize, y1 - currentSize, x2, y2 + currentSize);
        }
        if (yPressed == height - 1) {
//            Log.i("pressed_Down_Line", String.valueOf(yPressed));
            drawRect(canvas, x1 - currentSize, y1 - currentSize, x2 + currentSize, y2);
        }
//        Log.i("press_up_xPressed", String.valueOf(xPressed));
//        Log.i("press_up_yPressed", String.valueOf(yPressed));
    }

    private static void createRectGameElement(Canvas canvas, int x, int y, int l) {

        boolean grayColor = AreaForGrayTargetLine.getAreaGrayTargetLine(x, y);
        int x1 = x * size;
        int y1 = (y * size) + heightInfoArea;
        int x2 = (x * size) + size;
        int y2 = ((y * size) + size) + heightInfoArea;

        if (grayColor) {
            setRectColorGray(l);
        } else {
            setRectColor(l);
        }
        drawRect(canvas, x1, y1, x2, y2);
    }

    private static void drawRectNextColor(Canvas canvas, double x1, double y1, double x2, double y2, int l) {
        setRectColor(l);
        drawRect(canvas, (int) x1, (int) y1, (int) x2, (int) y2);
    }

    private static void gameOverLose() {                                     // когда проиграл
    }

    private static void drawRect(Canvas canvas, int x, int y) {
        int x1 = x * size;
        int y1 = (y * size) + heightInfoArea;
        int x2 = (x * size) + size;
        int y2 = ((y * size) + size) + heightInfoArea;

        canvas.drawRect(x1, y1, x2, y2, p);
    }

    private static void drawRect(Canvas canvas, int x1, int y1, int x2, int y2) {

        canvas.drawRect(x1, y1, x2, y2, p);
    }

    public static void setFirstDraw(boolean firstDraw) {
        Draw.firstDraw = firstDraw;
    }

    private static void setRectColor(int numColor) {
        if (numColor == 1) {
            p.setColor(red);
        }
        if (numColor == 2) {
            p.setColor(green);
        }
        if (numColor == 3) {
            p.setColor(blue);
        }
        if (numColor == 4) {
            p.setColor(yellow);
        }
        if (numColor == 5) {
            p.setColor(violet);
        }
        if (numColor == 6) {
            p.setColor(cyan);          //серый цвет
        }
        if (numColor == 99) {
            p.setColor(silver);
        }
    }

    private static void setRectColorGray(int numColor) {
        if (numColor == 1) {
            p.setColor(redGray);
        }
        if (numColor == 2) {
            p.setColor(greenGray);
        }
        if (numColor == 3) {
            p.setColor(blueGray);
        }
        if (numColor == 4) {
            p.setColor(yellowGray);
        }
        if (numColor == 5) {
            p.setColor(violetGray);
        }
        if (numColor == 6) {
            p.setColor(cyanGray);
        }
        if (numColor == 99) {
            p.setColor(silver);
        }

    }
}
