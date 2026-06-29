package com.example.rects.game.config

object SettingCurrentGame {
    private var gameLoose = false
    private var currentNumLevel = 1

    @JvmStatic fun setGameLoose(value: Boolean) { gameLoose = value }
    @JvmStatic fun isGameLoose(): Boolean = gameLoose
    @JvmStatic fun getCurrentNumLVL(): Int = currentNumLevel
    @JvmStatic fun setCurrentNumLVL(value: Int) {
        currentNumLevel = value.coerceAtLeast(1)
    }
}
