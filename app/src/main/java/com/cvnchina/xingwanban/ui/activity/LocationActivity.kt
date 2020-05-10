package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-10
 */
class LocationActivity:BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.avtivity_location

    override fun initData() {

    }

    override fun initView() {
       toolbar_title.text="添加位置"
    }

    override fun initListener() {

    }
}