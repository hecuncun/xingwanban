package com.cvnchina.xingwanban.ui.activity

import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.ext.showToast
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-3
 */
class InfoEditActivity : BaseActivity() {
    override fun attachLayoutRes(): Int {
        return R.layout.activity_edit_info
    }

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text = "个人说明"
        toolbar_right_tv.text = "保存"
        toolbar_right_tv.setTextColor(resources.getColor(R.color.color_primary_yellow))
        toolbar_right_tv.visibility = View.VISIBLE
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            showToast("保存成功")
        }
    }
}