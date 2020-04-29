package com.jzbn.xingwanban.ui.fragment

import android.view.View
import com.jzbn.xingwanban.R
import com.lhzw.bluetooth.base.BaseFragment

/**
 * Created by heCunCun on 2020/4/29
 */
class HomeFragment :BaseFragment(){
    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
    override fun attachLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
    }

    override fun initListener() {
    }

    override fun lazyLoad() {
    }
}