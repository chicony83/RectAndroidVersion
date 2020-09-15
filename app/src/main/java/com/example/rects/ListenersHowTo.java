package com.example.rects;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ListenersHowTo {
    public static View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();
//            int y = (int) event.getY();

            Log.i("press_up_x", String.valueOf(x));
            Log.i("TAG" , "bitmap_1 " + HowToPlayActivity.bitmapInt);
            HowToPlayActivity.bitmapInt = HowToPlayActivity.bitmapInt +1;
            Log.i("TAG" , "bitmap_2 " + HowToPlayActivity.bitmapInt);
//            if (HowToPlayActivity.bitmapInt == 6){
//                HowToPlayActivity.bitmapInt = 1;
//            }
            Log.i("TAG" , "bitmapInt " + HowToPlayActivity.bitmapInt);
            Log.i("TAG" , "bitmap_3 " + HowToPlayActivity.bitmapInt);
            v.invalidate();

            switch (event.getAction()){
                case MotionEvent.ACTION_UP:

    //                if(HowToPlayActivity.bitmapInt == 6){
    //                    if(y > HowToPlayActivity.y1INeedPlayButton){
    //                        Log.i("TAG" , "тык_y " + y);
//
    //                        HowToPlayActivity.runIntent = true;
    //                    }
    //                }
                    v.invalidate();

                break;
            }

            return false;
        }
    };
}
