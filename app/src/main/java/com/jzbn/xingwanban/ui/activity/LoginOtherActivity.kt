package com.jzbn.xingwanban.ui.activity

import android.view.View
import com.jzbn.xingwanban.R
import com.jzbn.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020/4/27
 */
class LoginOtherActivity:BaseActivity() {
    override fun attachLayoutRes(): Int {
       return R.layout.activity_login_other
    }

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text="登录"
        toolbar_right_tv.visibility= View.VISIBLE
    }

    override fun initListener() {

    }
}