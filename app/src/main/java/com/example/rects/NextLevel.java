package com.example.rects;

import com.example.rects.game.config.SettingCurrentGame;
import com.example.rects.game.config.SettingLevels;

public class NextLevel {
    AreaForGame areaForGame = new AreaForGame();
    public NextLevel(){
        int nextLVL = Math.min(
                SettingCurrentGame.getCurrentNumLVL() + 1,
                SettingLevels.getMaxLevel());
        SettingCurrentGame.setCurrentNumLVL(nextLVL);
        SettingLevels.setCurrentSettingLevel(nextLVL);

        Information.setZEROMovesDoneValue();

        areaForGame.newCleanArea();
        areaForGame.newGameLevelValues();
        areaForGame.newTargetLines();
        areaForGame.nextColor();
        areaForGame.countHowManyRectanglesToClear();
        areaForGame.newMoveBackArea();

    }
}
