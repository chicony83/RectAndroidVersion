package com.example.rects;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/** Common full-screen setup shared by every screen in the game. */
public abstract class FullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }
}
