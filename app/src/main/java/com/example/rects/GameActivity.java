package com.example.rects;

import com.example.rects.game.config.SettingCurrentGame;
import com.example.rects.game.config.SettingGame;
import com.example.rects.game.config.SettingLevels;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameActivity extends FullscreenActivity {

    private long prevRedrawTime;

    public static final String EXTRA_LEVEL = "com.example.rects.extra.LEVEL";

    private static final int FRAME_INTERVAL_MS = 16;
    private static final int CLEARING_TIME = 100;

    AreaForGame areaForGame = new AreaForGame();
    Information information = new Information();
    AreaForGrayTargetLine areaForGrayTargetLine = new AreaForGrayTargetLine();

    private static int numLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawView drawView = new DrawView(this);
        setContentView(drawView);

        ListenersGame listenersGameArea = new ListenersGame();
        drawView.setOnTouchListener(listenersGameArea.handleTouch);

        //настройка активности
        //настройка уровней
        numLevel = getIntent().getIntExtra(EXTRA_LEVEL, 3);
        SettingLevels.setCurrentSettingLevel(numLevel);

        //вспомогательная строка чтобы узнать что получили из интента
//        Log.i("num_level_in_G_activity", String.valueOf(numLevel));
        Resources res = getResources();

        getNamesInformationStrings(res);

        sizeDisplay();

        information.calculationOfTheNumberOfMovesBack();        //подсчет количества разрешенных ходов назад

        CountSizeArea.preparationCountSize();
        CountSizeArea.sizeInformationArea();
        CountSizeArea.sizeGameArea();
        CountSizeArea.sizeBackButtonArea();

        information.dataPreparation();
        information.setPositionsNextColor();
        information.SetPositionMoveBackButton();

        areaForGame.newCleanArea();
        areaForGame.newGameLevelValues();
        areaForGame.newTargetLines();
        areaForGame.nextColor();

        areaForGame.countHowManyRectanglesToClear();

        areaForGrayTargetLine.grayLineSearch();

        Information.setIsGameAreaDrawable(false);
        //information.getHowManyRectanglesToClear();
    }

    private void getNamesInformationStrings(Resources res) {
        String nextColorText = res.getString(R.string.next_color);
        String blocksLeftText = res.getString(R.string.blocks_left);
        String movesDoneText = res.getString(R.string.moves_done);
        String moveBackButtonText = res.getString(R.string.move_back);
        String manyMovesLeftText = res.getString(R.string.many_moves_left);
        String oneMoveLeftText = res.getString(R.string.one_move_left);

        Information.setValuesInformationStrings(
                nextColorText, blocksLeftText, movesDoneText,
                moveBackButtonText, manyMovesLeftText, oneMoveLeftText);
    }

    protected void onDestroy() {

        super.onDestroy();

        information.movesDoneValueResetCounter();
        SettingCurrentGame.setGameLoose(false);

    }

    //public void onBackPressed(){
    //    Log.i("TAG", "onBackPressed time " + getTime());
    //}

    public void sizeDisplay() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SettingGame.setWidthDisplay(size.x);
        SettingGame.setHeightDisplay(size.y);

        //Log.i("widthDisplay", String.valueOf(SettingGame.getWidthDisplay()));
        //Log.i("heightDisplay", String.valueOf(SettingGame.getHeightDisplay()));
    }


    class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;          //создаем новый поток рисования
        //Paint paint;

        public DrawView(Context context) {      //конструктор класс с аргументом для рисования
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, //если размеры изменятся
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {      //когда поток создается
            drawThread = new DrawThread(getHolder());           //добавляем поток рисования в цикл
            drawThread.setRunning(true);                        //устанавливаем флаг
            drawThread.start();                                 //запускаем поток перерисовывания
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {    //при уничтожении потока
            if (drawThread == null) {
                return;
            }
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    retry = false;
                }
            }
        }

        class DrawThread extends Thread {                       //запускаем отдельный поток рисования

            private volatile boolean running;
            private final SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {    //переопределение потока рисования
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
                prevRedrawTime = getTime();
            }

            @Override
            public void run() {                                 //предопределенный метод обработчика
                Canvas canvas;

                while (running) {
                    long curTime = getTime();
                    long elapsedTime = curTime - prevRedrawTime;
                    long clearingTime = curTime - Move.getMoveTime();
                    Draw.setFirstDraw(true);

                    if (elapsedTime < FRAME_INTERVAL_MS) {
                        try {
                            Thread.sleep(FRAME_INTERVAL_MS - elapsedTime);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        continue;
                    }
                    canvas = null;
                    try {

                        //CleaningAfterMove.clearing();
                        canvas = surfaceHolder.lockCanvas(null);    //блокируем canvas для рисования

                        if (canvas == null)
                            continue;
                        if (clearingTime > CLEARING_TIME) {         //проверяем прошло ли время для очистки
                            synchronized (AreaForGame.getStateLock()) {
                                CleaningAfterMove.isNeedClearing();
                            }
                        }
                        Draw.onDrawGameArea(canvas);                //перерисовываем игровой уровень

                        if (Information.getHowManyRectanglesToClear() <= 0) {       //когда уровень очищен
                            synchronized (AreaForGame.getStateLock()) {
                                new NextLevel();
                            }
                        }
                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas); //освобождаем canvas
                        }
                    }
                    prevRedrawTime = curTime;
                }
            }
        }
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }
}
