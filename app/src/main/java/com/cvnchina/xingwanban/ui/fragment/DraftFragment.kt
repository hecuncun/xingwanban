package com.cvnchina.xingwanban.ui.fragment

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.adapter.DraftAdapter
import com.cvnchina.xingwanban.bean.DraftBean
import com.cvnchina.xingwanban.ui.activity.PlayerActivity
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_draft.*
import org.litepal.LitePal

/**
 * Created by hecuncun on 2020-5-6
 */
class DraftFragment : BaseFragment() {
    companion object {
        fun getInstance(): DraftFragment = DraftFragment()
    }

    override fun attachLayoutRes(): Int {
        return R.layout.fragment_draft
    }

    private var list = mutableListOf<DraftBean>()
    override fun initView(view: View) {
        initRv()
    }

    override fun initListener() {
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            val draftBean = adapter.getItem(position) as DraftBean
            when (view.id) {
                R.id.iv_start -> {
                    val intent = Intent(activity, PlayerActivity::class.java)
                    intent.putExtra("path",draftBean.path)
                    intent.putExtra("thumbnailPath",draftBean.thumbnailPath)
                    intent.putExtra("title",draftBean.title)
                    intent.putExtra("tags",draftBean.tags)
                    startActivity(intent)
                }
                R.id.tv_edit -> {

                }
                R.id.iv_move->{
                    //移除除视频
                    draftBean.delete()
                    mAdapter.remove(position)
                }
            }
        }
    }

    override fun lazyLoad() {
        list=LitePal.findAll(DraftBean::class.java)
        mAdapter.setNewData(list)
        if (list.isEmpty()){
            ll_empty_view.visibility=View.VISIBLE
        }else{
            ll_empty_view.visibility=View.GONE
        }

    }

    private val mAdapter: DraftAdapter by lazy {
        DraftAdapter()
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


}