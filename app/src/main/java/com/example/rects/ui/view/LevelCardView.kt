package com.example.rects.ui.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.rects.R

class LevelCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setPadding(dp(18), dp(12), dp(16), dp(12))
        isFocusable = true

        val values = context.obtainStyledAttributes(attrs, R.styleable.LevelCardView)
        val title = values.getString(R.styleable.LevelCardView_levelTitle).orEmpty()
        val subtitle = values.getString(R.styleable.LevelCardView_levelSubtitle).orEmpty()
        val accent = values.getColor(
            R.styleable.LevelCardView_levelAccent,
            ContextCompat.getColor(context, R.color.level_sage)
        )
        val danger = values.getBoolean(R.styleable.LevelCardView_levelDanger, false)
        values.recycle()

        background = cardBackground(danger)
        contentDescription = "$title. $subtitle"

        addView(View(context).apply {
            background = roundedShape(accent, dp(5).toFloat())
        }, LayoutParams(dp(16), dp(16)))

        addView(LinearLayout(context).apply {
            orientation = VERTICAL
            setPadding(dp(16), 0, dp(8), 0)
            addView(text(title, 18f, R.color.menu_text_primary))
            addView(text(subtitle, 13f, R.color.menu_text_secondary).apply {
                setPadding(0, dp(3), 0, 0)
            })
        }, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))

        addView(text("›", 27f, R.color.menu_text_muted).apply {
            gravity = Gravity.CENTER
        }, LayoutParams(dp(24), LayoutParams.MATCH_PARENT))
    }

    private fun text(value: String, size: Float, color: Int) = TextView(context).apply {
        text = value
        textSize = size
        setTextColor(ContextCompat.getColor(context, color))
        typeface = android.graphics.Typeface.create("sans-serif", android.graphics.Typeface.NORMAL)
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
    }

    private fun cardBackground(danger: Boolean): StateListDrawable {
        val border = if (danger) 0xFF8D625C.toInt() else 0xFF2A3946.toInt()
        return StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_pressed), cardShape(0xFF22313D.toInt(), border))
            addState(intArrayOf(), cardShape(0xFF18232D.toInt(), border))
        }
    }

    private fun cardShape(fill: Int, border: Int) = roundedShape(fill, dp(14).toFloat()).apply {
        setStroke(dp(1), border)
    }

    private fun roundedShape(color: Int, radius: Float) = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(color)
        cornerRadius = radius
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
