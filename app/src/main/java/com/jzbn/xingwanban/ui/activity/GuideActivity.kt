package com.jzbn.xingwanban.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.jzbn.xingwanban.R
import com.jzbn.xingwanban.adapter.GuidePageAdapter
import com.jzbn.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.guide_3.view.*

/**
 * Created by hecuncun on 2020/4/26
 *  应用MD5:  1E:AF:26:44:95:30:BA:22:2D:8E:3C:84:50:A9:94:BC
 */
class GuideActivity : BaseActivity() {
    private var viewList = mutableListOf<View>()
    override fun attachLayoutRes(): Int {
        return R.layout.activity_guide
    }

    override fun initData() {
    }

    override fun initView() {
        val view1 = LayoutInflater.from(this).inflate(R.layout.guide_1, null)
        val view2 = LayoutInflater.from(this).inflate(R.layout.guide_2, null)
        val view3 = LayoutInflater.from(this).inflate(R.layout.guide_3, null)
        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        viewpager.adapter=GuidePageAdapter(viewList as ArrayList<View>)
        view3.btn_finish.setOnClickListener {
           // jumpToMain()
            jumpToLogin()
            finish()
        }
    }

    override fun initListener() {
    }

    private fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun jumpToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}