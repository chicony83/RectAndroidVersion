package com.example.rects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenuActivity extends FullscreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        TextView version = findViewById(R.id.version);
        version.setText(getString(R.string.version_format, BuildConfig.VERSION_NAME));
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
