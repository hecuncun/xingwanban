package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-10
 */
class SortActivity:BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_sort


    override fun initData() {

    }

    override fun initView() {
      toolbar_title.text="内容分类"
    }

    override fun initListener() {

    }
}