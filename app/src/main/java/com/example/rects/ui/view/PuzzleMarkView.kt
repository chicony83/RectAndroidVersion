package com.example.rects.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class PuzzleMarkView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val block = RectF()
    private val colors = intArrayOf(
        0xFF8FA9A5.toInt(),
        0xFF7186A8.toInt(),
        0xFFC39A7A.toInt(),
        0xFFC4AE7C.toInt()
    )

    private val cells = arrayOf(
        Cell(2, 0, 1),
        Cell(1, 1, 0),
        Cell(2, 1, 2),
        Cell(0, 2, 3),
        Cell(1, 2, 1),
        Cell(2, 2, 0),
        Cell(3, 2, 2)
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cellSize = min(width / 4.6f, height / 3.4f)
        val gap = cellSize * 0.16f
        val markWidth = cellSize * 4 + gap * 3
        val markHeight = cellSize * 3 + gap * 2
        val startX = (width - markWidth) / 2f
        val startY = (height - markHeight) / 2f
        val radius = cellSize * 0.18f

        cells.forEach { cell ->
            val left = startX + cell.column * (cellSize + gap)
            val top = startY + cell.row * (cellSize + gap)
            block.set(left, top, left + cellSize, top + cellSize)
            paint.color = colors[cell.colorIndex]
            canvas.drawRoundRect(block, radius, radius, paint)
        }
    }

    private data class Cell(
        val column: Int,
        val row: Int,
        val colorIndex: Int
    )
}
