package com.cvnchina.xingwanban.widget

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.LayoutInflater
import android.view.View
import com.cvnchina.xingwanban.R
import kotlinx.android.synthetic.main.dialog_select_photo.*

/**
 * Created by hecuncun on 2020-5-2
 */
class SelectHeadPhotoDialog(context: Context) : BottomSheetDialog(context), View.OnClickListener {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_select_photo, null)
        setContentView(view)
        delegate.findViewById<View>(android.support.design.R.id.design_bottom_sheet)?.setBackgroundColor(context.resources.getColor(android.R.color.transparent))
        iv_close.setOnClickListener(this)
        iv_def1.setOnClickListener(this)
        iv_def2.setOnClickListener(this)
        iv_def3.setOnClickListener(this)
        iv_def4.setOnClickListener(this)
        tv_album.setOnClickListener(this)
        tv_take_photo.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        mOnChoseListener?.select(v.id)
    }

    interface OnChoseListener {
        fun select(resId: Int)
    }

    private var mOnChoseListener: OnChoseListener? = null

    fun setOnChoseListener(onChoseListener: OnChoseListener) {
        mOnChoseListener = onChoseListener
    }
}