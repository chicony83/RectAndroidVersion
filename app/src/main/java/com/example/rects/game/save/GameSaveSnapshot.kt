package com.example.rects.game.save

data class GameSaveSnapshot(
    val level: Int,
    val gameArea: Array<IntArray>,
    val moveBackArea: Array<IntArray>,
    val nextColor: Int,
    val backNextColor: Int,
    val movesDone: Int,
    val movesBackLeft: Int,
    val moveBackAvailable: Boolean
)
