package com.example.rects.game.config

object SettingLevels {
    private const val MIN_LEVEL = 1
    private const val MAX_LEVEL = 7

    private var currentNumLevel = MIN_LEVEL
    private var currentMinColor = SettingGame.getMinColor()
    private var currentMaxColor = SettingGame.getMaxColorLight()
    private var currentSpace = SettingGame.getSpaceVeryLight()

    @JvmStatic
    fun setCurrentSettingLevel(level: Int) {
        currentNumLevel = level.coerceIn(MIN_LEVEL, MAX_LEVEL)
        currentMinColor = SettingGame.getMinColor()

        when (currentNumLevel) {
            1 -> {
                currentMaxColor = SettingGame.getMaxColorLight()
                currentSpace = SettingGame.getSpaceVeryLight()
            }
            2 -> {
                currentMaxColor = SettingGame.getMaxColorLight()
                currentSpace = SettingGame.getSpaceLight()
            }
            3 -> {
                currentMaxColor = SettingGame.getMaxColorMedium()
                currentSpace = SettingGame.getSpaceMedium()
            }
            4 -> {
                currentMaxColor = SettingGame.getMaxColorMedium()
                currentSpace = SettingGame.getSpaceHard()
            }
            5 -> {
                currentMaxColor = SettingGame.getMaxColorMedium()
                currentSpace = SettingGame.getSpaceVeryHard()
            }
            6 -> {
                currentMaxColor = SettingGame.getMaxColorHard()
                currentSpace = SettingGame.getSpaceVeryHard()
            }
            7 -> {
                currentMaxColor = SettingGame.getMaxColorImpossible()
                currentSpace = SettingGame.getSpaceHard()
            }
        }
    }

    @JvmStatic fun getCurrentMaxColor(): Int = currentMaxColor
    @JvmStatic fun getCurrentMinColor(): Int = currentMinColor
    @JvmStatic fun getCurrentSpace(): Int = currentSpace
    @JvmStatic fun getCurrentNumLevel(): Int = currentNumLevel
    @JvmStatic fun getMaxLevel(): Int = MAX_LEVEL
}
