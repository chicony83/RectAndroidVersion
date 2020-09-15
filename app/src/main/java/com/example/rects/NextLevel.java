package com.example.rects;

public class NextLevel {
    AreaForGame areaForGame = new AreaForGame();
    public NextLevel(){
        int nextLVL = SettingCurrentGame.getCurrentNumLVL() + 1;
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
