package com.cvnchina.xingwanban.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aliyun.svideo.common.baseAdapter.BaseQuickAdapter
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.adapter.WorksAdapter
import com.cvnchina.xingwanban.base.BaseNoDataBean
import com.cvnchina.xingwanban.bean.DemoWorksBean
import com.cvnchina.xingwanban.bean.WorksBean
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.net.CallbackListObserver
import com.cvnchina.xingwanban.net.CallbackObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import com.cvnchina.xingwanban.ui.activity.PlayerActivity
import com.cvnchina.xingwanban.utils.BeanUtils
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_works.*

/**
 * Created by hecuncun on 2020-5-6
 */
class WorksFragment : BaseFragment() {
    private var currentPage = 0
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

        worksAdapter.setOnItemChildClickListener { adapter, view, position ->
            val listBean = adapter.getItem(position) as WorksBean.ListBean
            when (view.id) {
                R.id.iv_start -> {
                    val intent = Intent(activity, PlayerActivity::class.java)
                    intent.putExtra("listBean", listBean)
                    startActivity(intent)
                }
                R.id.iv_share -> {

                }
                R.id.iv_move->{
                    //移除除视频
                    val removeVideoCall =
                        SLMRetrofit.instance.api.removeVideoCall(listBean.contId.toInt())
                        removeVideoCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                        override fun onSucceed(t: BaseNoDataBean) {
                           if (t.msg=="1"){
                               showToast("删除成功")
                               worksAdapter.remove(position)
                           }else{
                               showToast(t.msgCondition)
                           }
                        }

                        override fun onFailed() {

                        }
                    })
                }
            }

        }
    }

    override fun lazyLoad() {
        //获取Demo
        val demoWorksCall = SLMRetrofit.instance.api.demoWorksCall()
        demoWorksCall.compose(ThreadSwitchTransformer())
            .subscribe(object : CallbackListObserver<DemoWorksBean>() {
                override fun onSucceed(t: DemoWorksBean) {
                    if (t.msg=="1"){
                        for (item in t.data){
                            val bean = BeanUtils.modelA2B(item, WorksBean.ListBean::class.java)
                            listWorks.add(bean)
                        }
                        worksAdapter.setNewData(listWorks)
                        if (listWorks.isEmpty()){
                            ll_empty_view.visibility=View.VISIBLE
                        }else{
                            ll_empty_view.visibility=View.GONE
                        }
                    }
                }

                override fun onFailed() {

                }

            })
        //获取视频列表
        val worksCall = SLMRetrofit.instance.api.worksCall(currentPage, pageSize)
        worksCall.compose(ThreadSwitchTransformer())
            .subscribe(object : CallbackObserver<WorksBean>() {
                override fun onSucceed(t: WorksBean, desc: String?) {
                    total = t.pages
                    listWorks.addAll(t.list)
                    worksAdapter.setNewData(listWorks)
                    if (listWorks.isEmpty()){
                        ll_empty_view.visibility=View.VISIBLE
                    }else{
                        ll_empty_view.visibility=View.GONE
                    }
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