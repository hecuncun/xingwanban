package com.cvnchina.xingwanban.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aliyun.svideo.common.baseAdapter.BaseQuickAdapter
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.adapter.WorksAdapter
import com.cvnchina.xingwanban.bean.WorksBean
import com.cvnchina.xingwanban.net.CallbackObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_works.*

/**
 * Created by hecuncun on 2020-5-6
 */
class WorksFragment : BaseFragment() {
    private var currentPage = 1
    private var total = 0
    private var pageSize = 10
    private var listWorks = mutableListOf<WorksBean.ListBean>()
    private val worksAdapter: WorksAdapter by lazy {
        WorksAdapter()
    }

    companion object {
        fun getInstance(): WorksFragment = WorksFragment()
    }

    override fun attachLayoutRes(): Int {
        return R.layout.fragment_works
    }

    override fun initView(view: View) {
        initRv()
    }

    override fun initListener() {
        worksAdapter.disableLoadMoreIfNotFullPage(rv_works)
        worksAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (total < 2) {
                worksAdapter.setEnableLoadMore(false)
            }
            currentPage++
            if (currentPage > total) {
                return@RequestLoadMoreListener
            }
            val worksCall = SLMRetrofit.instance.api.worksCall(currentPage, pageSize)
            worksCall.compose(ThreadSwitchTransformer())
                .subscribe(object : CallbackObserver<WorksBean>() {
                    override fun onSucceed(t: WorksBean, desc: String?) {
                        listWorks.addAll(t.list)
                        worksAdapter.setNewData(listWorks)
                        if (currentPage == total) {
                            worksAdapter.loadMoreEnd()
                            worksAdapter.setEnableLoadMore(false)
                        } else {
                            worksAdapter.setEnableLoadMore(true)
                            worksAdapter.loadMoreComplete()
                        }
                    }

                    override fun onFailed() {

                    }
                })

        }, rv_works)
    }

    override fun lazyLoad() {
        //获取视频列表
        val worksCall = SLMRetrofit.instance.api.worksCall(currentPage, pageSize)
        worksCall.compose(ThreadSwitchTransformer())
            .subscribe(object : CallbackObserver<WorksBean>() {
                override fun onSucceed(t: WorksBean, desc: String?) {
                    total = t.pages
                    listWorks.addAll(t.list)
                    worksAdapter.setNewData(listWorks)
                }

                override fun onFailed() {

                }
            })

    }

    private fun initRv() {

        rv_works.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = worksAdapter
        }
    }

}