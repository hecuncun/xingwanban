package com.cvnchina.xingwanban.widget

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.LayoutInflater
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.adapter.EvaluateAdapter
import com.cvnchina.xingwanban.bean.EvaluateListBean
import com.cvnchina.xingwanban.bean.WorksBean
import com.cvnchina.xingwanban.net.CallbackObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.dialog_evaluate.*

/**
 * Created by hecuncun on 2020-5-16
 */
class EvaluateDialog(context: Context) : BottomSheetDialog(context), View.OnClickListener {
    private var mList = mutableListOf<WorksBean.ListBean.HotCommentBean>()
    private var adapter: EvaluateAdapter? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_evaluate, null)
        setContentView(view)
        iv_close.setOnClickListener(this)
        tv_send.setOnClickListener(this)

    }

    private var videoId = 0
    private var currentPage = 0
    private var total = 0
    private var pageSize = 10


     fun setVideoId(id: Int) {
        videoId = id
         initData()
    }

    private fun initData() {
        //根据视频id,获取评论列表
        val evaluateListCall =
            SLMRetrofit.instance.api.evaluateListCall(videoId, currentPage, pageSize)
        evaluateListCall.compose(ThreadSwitchTransformer()).subscribe(object :
            CallbackObserver<EvaluateListBean>(){
            override fun onSucceed(t: EvaluateListBean?, desc: String?) {

            }

            override fun onFailed() {

            }
        })

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_close -> {
                dismiss()
            }
            R.id.tv_send -> {//发送

            }
        }
    }
}