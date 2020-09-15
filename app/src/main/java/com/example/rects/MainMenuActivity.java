package com.example.rects;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClickSelectLevel(View view) {
        Intent intentSelectLevelActivity = new Intent(this, SelectLevelsActivity.class);
        startActivity(intentSelectLevelActivity);
    }

    public void onClickHowToPlay(View view) {
        Intent intentHowToPlayActivity = new Intent(this, HowToPlayActivity.class);
        startActivity(intentHowToPlayActivity);
    }
}
