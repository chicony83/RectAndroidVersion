package com.example.rects;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class SelectLevelsActivity extends Activity {
    int numLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_select);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClickSelectForKidsLevel(View view) {
        int n = 1;
        onClickIntent(n);
    }

    public void onClickSelectVeryEasyLevel(View view) {
        int n = 2;
        onClickIntent(n);
    }

    public void onClickSelectEasyLevel(View view) {
        int n = 3;
        onClickIntent(n);
    }

    public void onClickSelectMediumLevel(View view) {
        int n = 4;
        onClickIntent(n);
    }

    public void onClickSelectHardLevel(View view) {
        int n = 5;
        onClickIntent(n);
    }

    public void onClickSelectVeryHardLevel(View view) {
        int n = 6;
        onClickIntent(n);
    }
    public void onClickSelectImpossible(View view) {
        int n = 7;
        onClickIntent(n);
    }
    private void onClickIntent(int n){
        Intent intent = new Intent(this,GameActivity.class);
        numLevel = n;
        SettingCurrentGame.setCurrentNumLVL(n);
        intent.putExtra("NUM_LEVEL",numLevel);
        startActivity(intent);
    }

}
