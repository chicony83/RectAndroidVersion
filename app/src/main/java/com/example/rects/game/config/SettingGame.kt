package com.example.rects.game.config

object SettingGame {
    private const val WIDTH = 15
    private const val HEIGHT = 15
    private const val NULL_COLOR = 0
    private const val MIN_COLOR = 1

    private const val MAX_COLOR_LIGHT = 3
    private const val MAX_COLOR_MEDIUM = 4
    private const val MAX_COLOR_HARD = 5
    private const val MAX_COLOR_IMPOSSIBLE = 6

    private const val SPACE_VERY_LIGHT = 6
    private const val SPACE_LIGHT = 5
    private const val SPACE_MEDIUM = 4
    private const val SPACE_HARD = 3
    private const val SPACE_VERY_HARD = 2

    private var widthDisplay = 0
    private var heightDisplay = 0
    private var informationAreaWidth = 0
    private var informationAreaHeight = 0
    private var backButtonAreaWidth = 0
    private var backButtonAreaHeight = 0

    @JvmStatic fun getSideSizeOfRect(): Int = widthDisplay / WIDTH
    @JvmStatic fun getWidthDisplay(): Int = widthDisplay
    @JvmStatic fun setWidthDisplay(value: Int) { widthDisplay = value }
    @JvmStatic fun getHeightDisplay(): Int = heightDisplay
    @JvmStatic fun setHeightDisplay(value: Int) { heightDisplay = value }

    @JvmStatic fun getInformationAreaWidth(): Int = informationAreaWidth
    @JvmStatic fun setInformationAreaWidth(value: Int) { informationAreaWidth = value }
    @JvmStatic fun getInformationAreaHeight(): Int = informationAreaHeight
    @JvmStatic fun setInformationAreaHeight(value: Int) { informationAreaHeight = value }

    @JvmStatic fun getBackButtonAreaWidth(): Int = backButtonAreaWidth
    @JvmStatic fun setBackButtonAreaWidth(value: Int) { backButtonAreaWidth = value }
    @JvmStatic fun getBackButtonAreaHeight(): Int = backButtonAreaHeight
    @JvmStatic fun setBackButtonAreaHeight(value: Int) { backButtonAreaHeight = value }

    @JvmStatic fun getWIDTH(): Int = WIDTH
    @JvmStatic fun getHEIGHT(): Int = HEIGHT
    @JvmStatic fun getNullColor(): Int = NULL_COLOR
    @JvmStatic fun getMinColor(): Int = MIN_COLOR
    @JvmStatic fun getMaxColorLight(): Int = MAX_COLOR_LIGHT
    @JvmStatic fun getMaxColorMedium(): Int = MAX_COLOR_MEDIUM
    @JvmStatic fun getMaxColorHard(): Int = MAX_COLOR_HARD
    @JvmStatic fun getMaxColorImpossible(): Int = MAX_COLOR_IMPOSSIBLE
    @JvmStatic fun getSpaceVeryLight(): Int = SPACE_VERY_LIGHT
    @JvmStatic fun getSpaceLight(): Int = SPACE_LIGHT
    @JvmStatic fun getSpaceMedium(): Int = SPACE_MEDIUM
    @JvmStatic fun getSpaceHard(): Int = SPACE_HARD
    @JvmStatic fun getSpaceVeryHard(): Int = SPACE_VERY_HARD
}
