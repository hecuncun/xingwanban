package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-5
 */
class AppUpdateActivity:BaseActivity() {
    override fun attachLayoutRes(): Int {
        return R.layout.activity_app_update
    }

    override fun initData() {

    }

    override fun initView() {
      toolbar_title.text="版本更新"
    }

    override fun initListener() {

    }
}