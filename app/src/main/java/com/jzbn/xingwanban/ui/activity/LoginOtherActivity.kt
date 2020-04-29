package com.jzbn.xingwanban.ui.activity

import android.content.Intent
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
        toolbar_right_tv.text="随便看看"
        toolbar_right_tv.setTextColor(resources.getColor(R.color.color_gray_999999))
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}