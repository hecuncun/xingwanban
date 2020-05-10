package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-10
 */
class CanShowActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_can_show

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text = "谁可以看"
    }

    override fun initListener() {

    }
}