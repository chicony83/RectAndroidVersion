package com.example.rects.ui.menu

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.example.rects.R

class GamePauseMenu(private val activity: Activity) {
    private var dialog: Dialog? = null

    fun toggle() {
        if (dialog?.isShowing == true) {
            dismiss()
        } else {
            show()
        }
    }

    fun dismiss() {
        dialog?.dismiss()
        dialog = null
    }

    private fun show() {
        val pauseDialog = Dialog(activity).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_game_pause)
            setCanceledOnTouchOutside(true)
            setOnDismissListener { dialog = null }
            show()
        }

        dialog = pauseDialog

        pauseDialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

            attributes = attributes.apply {
                dimAmount = 0.72f
            }

            val width = (activity.resources.displayMetrics.widthPixels * 0.88f).toInt()
            setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        }
    }
}
