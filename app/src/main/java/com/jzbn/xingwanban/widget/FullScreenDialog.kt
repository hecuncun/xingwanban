package com.jzbn.xingwanban.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import com.jzbn.xingwanban.R
import kotlinx.android.synthetic.main.dialog_full_screen.*

/**
 * Created by heCunCun on 2020/4/29
 */
class FullScreenDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_full_screen)
        setCanceledOnTouchOutside(false)
        window.setBackgroundDrawable(ColorDrawable(context.resources.getColor(R.color.color_gray_transparent)))
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        setListener()
    }

    private fun setListener() {
        iv_cancel.setOnClickListener {
            dismiss()
        }
    }

}