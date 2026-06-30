package com.example.rects.game.save

import android.content.Context
import com.example.rects.AreaForGame
import com.example.rects.AreaForGrayTargetLine
import com.example.rects.CleaningAfterMove
import com.example.rects.Information
import com.example.rects.MoveBack
import com.example.rects.game.config.SettingCurrentGame
import com.example.rects.game.config.SettingGame
import com.example.rects.game.config.SettingLevels
import org.json.JSONArray
import org.json.JSONObject

object GameSaveRepository {
    private const val PREFERENCES = "saved_game"
    private const val SAVE_KEY = "snapshot"
    private const val SAVE_VERSION = 1

    @JvmStatic
    fun hasSave(context: Context): Boolean = load(context) != null

    @JvmStatic
    fun save(context: Context): Boolean = synchronized(AreaForGame.getStateLock()) {
        CleaningAfterMove.isNeedClearing()

        val snapshot = GameSaveSnapshot(
            level = SettingLevels.getCurrentNumLevel(),
            gameArea = AreaForGame.copyGameArea(),
            moveBackArea = AreaForGame.copyMoveBackArea(),
            nextColor = AreaForGame.getNextColor(),
            backNextColor = AreaForGame.getBackNextColor(),
            movesDone = Information.getMovesDoneValue(),
            movesBackLeft = Information.getNumbersOfMovesBack(),
            moveBackAvailable = MoveBack.isBackwardAllowed()
        )

        context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            .edit()
            .putString(SAVE_KEY, snapshot.toJson().toString())
            .commit()
    }

    @JvmStatic
    fun load(context: Context): GameSaveSnapshot? {
        val savedJson = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            .getString(SAVE_KEY, null) ?: return null

        return try {
            JSONObject(savedJson).toSnapshot()
        } catch (_: Exception) {
            null
        }
    }

    @JvmStatic
    fun restore(snapshot: GameSaveSnapshot) = synchronized(AreaForGame.getStateLock()) {
        AreaForGame.restoreSavedState(
            snapshot.gameArea,
            snapshot.moveBackArea,
            snapshot.nextColor,
            snapshot.backNextColor
        )
        Information.setMovesDoneValue(snapshot.movesDone)
        Information.setNumbersOfMovesBack(snapshot.movesBackLeft)
        MoveBack.setBackwardAllowed(snapshot.moveBackAvailable)
        AreaForGame.countHowManyRectanglesToClear()
        AreaForGrayTargetLine.grayLineSearch()
        AreaForGame.ifThereAreMoves()
        SettingCurrentGame.setCurrentNumLVL(snapshot.level)
    }

    private fun GameSaveSnapshot.toJson() = JSONObject().apply {
        put("version", SAVE_VERSION)
        put("level", level)
        put("width", SettingGame.getWIDTH())
        put("height", SettingGame.getHEIGHT())
        put("gameArea", gameArea.toJsonArray())
        put("moveBackArea", moveBackArea.toJsonArray())
        put("nextColor", nextColor)
        put("backNextColor", backNextColor)
        put("movesDone", movesDone)
        put("movesBackLeft", movesBackLeft)
        put("moveBackAvailable", moveBackAvailable)
    }

    private fun JSONObject.toSnapshot(): GameSaveSnapshot {
        require(getInt("version") == SAVE_VERSION)
        val width = getInt("width")
        val height = getInt("height")
        require(width == SettingGame.getWIDTH() && height == SettingGame.getHEIGHT())

        return GameSaveSnapshot(
            level = getInt("level").coerceIn(1, SettingLevels.getMaxLevel()),
            gameArea = getJSONArray("gameArea").toArea(width, height),
            moveBackArea = getJSONArray("moveBackArea").toArea(width, height),
            nextColor = getInt("nextColor"),
            backNextColor = getInt("backNextColor"),
            movesDone = getInt("movesDone").coerceAtLeast(0),
            movesBackLeft = getInt("movesBackLeft").coerceAtLeast(0),
            moveBackAvailable = getBoolean("moveBackAvailable")
        )
    }

    private fun Array<IntArray>.toJsonArray() = JSONArray().apply {
        forEach { column ->
            column.forEach { value -> put(value) }
        }
    }

    private fun JSONArray.toArea(width: Int, height: Int): Array<IntArray> {
        require(length() == width * height)
        return Array(width) { x ->
            IntArray(height) { y -> getInt(x * height + y) }
        }
    }
}
