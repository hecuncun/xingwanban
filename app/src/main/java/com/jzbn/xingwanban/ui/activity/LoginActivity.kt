package com.jzbn.xingwanban.ui.activity

import android.view.View
import com.jzbn.xingwanban.R
import com.jzbn.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020/4/25
 */
class LoginActivity:BaseActivity() {
    override fun attachLayoutRes(): Int {
       return R.layout.activity_login
    }

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text="登录"
        toolbar_right_tv.text="跳过"
        toolbar_right_tv.visibility= View.VISIBLE
    }

    override fun initListener() {

    }
}