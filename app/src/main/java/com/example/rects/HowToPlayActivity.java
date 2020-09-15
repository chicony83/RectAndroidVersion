package com.example.rects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class HowToPlayActivity extends Activity {
    public static int bitmapInt = 1;
    public static boolean runIntent = false;
    static int widthDisplay;
    static int heightDisplay;

    private static int silver = Colors.SILVER;
    private static int black = Colors.BLACK;

    public static float x1INeedPlayButton;
    public static float y1INeedPlayButton;
    public static float x2INeedPlayButton;
    public static float y2INeedPlayButton;

    Paint paint = new Paint();
    Bitmap bitmapHowTo1;
    Bitmap bitmapHowTo2;
    Bitmap bitmapHowTo3;
    Bitmap bitmapHowTo4;
    Bitmap bitmapHowTo5;
    Bitmap bitmap;


    String iNeedPlay = " я всё понял хочу играть";


    float x1HowToBitmap = 0;
    float y1HowToBitmap = 0;
    float x1StatusBar = 0;
    float y1StatusBar = 0;
    float x2StatusBar;
    float y2StatusBar;
    float widthStatusBarCell;

    float oneHundredPercent = 100;
    float percetHeightStatusBar = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        howToPlayView hTPV = new howToPlayView(this);

        ListenersHowTo listenersHowTo = new ListenersHowTo();

        sizeDisplay();
        widthDisplay = SettingGame.getWidthDisplay();
        heightDisplay = SettingGame.getHeightDisplay();

        setContentView(hTPV);
        hTPV.setOnTouchListener(listenersHowTo.handleTouch);
        calculation();
    }

    private void calculation() {
        widthStatusBarCell = widthDisplay / 5;
        y2StatusBar = (heightDisplay /oneHundredPercent) * percetHeightStatusBar;
        y1HowToBitmap = y2StatusBar;
        x2StatusBar = widthStatusBarCell;
        x1INeedPlayButton = 0;
        y1INeedPlayButton = widthDisplay+y1HowToBitmap;
        x2INeedPlayButton = widthDisplay;
        y2INeedPlayButton = heightDisplay;
    }

    class howToPlayView extends View{

        public howToPlayView(Context context) {
            super(context);
            bitmapHowTo1 = BitmapFactory.decodeResource(getResources(),R.drawable.how1);
            bitmapHowTo2 = BitmapFactory.decodeResource(getResources(),R.drawable.how2);
            bitmapHowTo3 = BitmapFactory.decodeResource(getResources(),R.drawable.how3);
            bitmapHowTo4 = BitmapFactory.decodeResource(getResources(),R.drawable.how4);
            bitmapHowTo5 = BitmapFactory.decodeResource(getResources(),R.drawable.how5);

        }
        protected void onDraw(Canvas canvas){


            canvas.drawARGB(255,0,0,0);        //скорее всего фон


            if(HowToPlayActivity.bitmapInt == 1){
                bitmap = bitmapHowTo1;
            }
            if(HowToPlayActivity.bitmapInt == 2){
                bitmap = bitmapHowTo2;
            }
            if(HowToPlayActivity.bitmapInt == 3){
                bitmap = bitmapHowTo3;
            }
            if(HowToPlayActivity.bitmapInt == 4){
                bitmap = bitmapHowTo4;
            }
            if(HowToPlayActivity.bitmapInt == 5){
                bitmap = bitmapHowTo5;
            }
            if(HowToPlayActivity.bitmapInt == 6){
                bitmap = bitmapHowTo5;
            }
            float height = (float) (widthDisplay);
            Bitmap drawBitmap = Bitmap.createScaledBitmap(bitmap,widthDisplay, (int) height,true);

            canvas.drawBitmap(drawBitmap,x1HowToBitmap,y1HowToBitmap,paint);


            if(HowToPlayActivity.bitmapInt == 5){
                paint.setColor(silver);
                canvas.drawRect(x1INeedPlayButton,y1INeedPlayButton,x2INeedPlayButton,y2INeedPlayButton,paint);
                paint.setTextSize(40);
                float iNeedPlayX;
                int centerTextY = heightDisplay - widthDisplay;
                centerTextY = centerTextY /2;
                centerTextY = widthDisplay + centerTextY;
                float widthINeedPlayText = paint.measureText(iNeedPlay);
                iNeedPlayX = widthDisplay / 2;
                iNeedPlayX = iNeedPlayX -(widthINeedPlayText / 2);
                paint.setColor(black);
                canvas.drawText(iNeedPlay,iNeedPlayX,centerTextY,paint);


            }
            if(HowToPlayActivity.bitmapInt == 6){
                HowToPlayActivity.bitmapInt = 1;
                intent();
            }

        }
    }
    public void sizeDisplay(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SettingGame.setWidthDisplay(size.x);
        SettingGame.setHeightDisplay(size.y);

//        Log.i("TAG", "widthDisplay" + SettingGame.getWidthDisplay());
//        Log.i("TAG", "heightDisplay" + SettingGame.getHeightDisplay());
    }
    public void intent() {
        runIntent = false;
        Intent intent = new Intent(HowToPlayActivity.this, SelectLevelsActivity.class);

        //        Intent intent = new Intent(this, SelectLevelsActivity.class);
        startActivity(intent);
    }

}
