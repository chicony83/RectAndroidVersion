package com.example.rects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.rects.game.save.GameSaveRepository;
import com.example.rects.game.save.GameSaveSnapshot;

public class MainMenuActivity extends FullscreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        TextView version = findViewById(R.id.version);
        version.setText(getString(R.string.version_format, BuildConfig.VERSION_NAME));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button loadGameButton = findViewById(R.id.loadGameButton);
        boolean hasSavedGame = GameSaveRepository.hasSave(this);
        loadGameButton.setEnabled(hasSavedGame);
        loadGameButton.setAlpha(hasSavedGame ? 1.0f : 0.45f);
    }

    public void onClickSelectLevel(View view) {
        Intent intentSelectLevelActivity = new Intent(this, SelectLevelsActivity.class);
        startActivity(intentSelectLevelActivity);
    }

    public void onClickHowToPlay(View view) {
        Intent intentHowToPlayActivity = new Intent(this, HowToPlayActivity.class);
        startActivity(intentHowToPlayActivity);
    }

    public void onClickLoadGame(View view) {
        GameSaveSnapshot savedGame = GameSaveRepository.load(this);
        if (savedGame == null) {
            return;
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.EXTRA_LEVEL, savedGame.getLevel());
        intent.putExtra(GameActivity.EXTRA_LOAD_SAVED_GAME, true);
        startActivity(intent);
    }
}
