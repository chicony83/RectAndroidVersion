package com.example.rects;

import com.example.rects.game.config.SettingCurrentGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectLevelsActivity extends FullscreenActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_select);
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
    private void onClickIntent(int level){
        Intent intent = new Intent(this,GameActivity.class);
        SettingCurrentGame.setCurrentNumLVL(level);
        intent.putExtra(GameActivity.EXTRA_LEVEL, level);
        startActivity(intent);
    }

}
