package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-10
 */
class MsgActivity:BaseActivity() {
    override fun attachLayoutRes(): Int= R.layout.activity_msg

    override fun initData() {

    }

    override fun initView() {
      toolbar_title.text="消息"
    }

    override fun initListener() {

    }
}