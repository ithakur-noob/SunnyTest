package com.example.sunnytask.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import com.example.sunnytask.R

object CommonFunctions {
    private var dialog: Dialog? = null
    fun showProgress(activity: Context) {
        if (dialog != null) {
            if (dialog!!.isShowing) {
                return

            }
        }
        try {
            dialog = Dialog(activity)
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.progress_bar)
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setCancelable(false)

            dialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("ProgessError", e.message!!)
        }

    }

    fun dismissProgress() {
        try {
            if (dialog != null) {
                if (dialog!!.isShowing) {
                    dialog!!.dismiss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}