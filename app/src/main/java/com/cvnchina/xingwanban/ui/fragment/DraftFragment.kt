package com.cvnchina.xingwanban.ui.fragment

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.adapter.NormalAdapter
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_draft.*
import java.util.*

/**
 * Created by hecuncun on 2020-5-6
 */
class DraftFragment:BaseFragment() {
    companion object {
        fun getInstance(): DraftFragment = DraftFragment()
    }
    override fun attachLayoutRes(): Int {
        return R.layout.fragment_draft
    }

    override fun initView(view: View) {
        initList()
        initRv()
    }

    override fun initListener() {

    }

    override fun lazyLoad() {

    }
    private val mAdapter: NormalAdapter by lazy {
        NormalAdapter(list)
    }
    private fun initRv() {
        rv_draft.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }


    private var list = mutableListOf<String>()
    private fun initList() {
        list = ArrayList<String>()
        for (i in 1..10) {
            list.add(i.toString() + "条")
        }
    }
}