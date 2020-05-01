package com.cvnchina.xingwanban.ui.fragment

import android.view.View
import com.cvnchina.xingwanban.R
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2020/4/29
 */
class MineFragment :BaseFragment(){
    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }
    override fun attachLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(view: View) {
        toolbar_title.text="个人中心"
        toolbar_right_img.setImageResource(R.mipmap.icon_more)
        toolbar_right_img.visibility=View.VISIBLE
    }

    override fun initListener() {
    }

    override fun lazyLoad() {
    }
}