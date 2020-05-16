package com.cvnchina.xingwanban.widget

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.adapter.EvaluateAdapter
import com.cvnchina.xingwanban.bean.WorksBean
import kotlinx.android.synthetic.main.dialog_evaluate.*

/**
 * Created by hecuncun on 2020-5-16
 */
class EvaluateDialog(context: Context):BottomSheetDialog(context), View.OnClickListener {
    private var mList= mutableListOf<WorksBean.ListBean.HotCommentBean>()
    private var adapter:EvaluateAdapter?=null
    private var commentId=-1
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_evaluate, null)
        setContentView(view)
        iv_close.setOnClickListener(this)
        tv_send.setOnClickListener(this)
        adapter= EvaluateAdapter()
        adapter?.setOnItemChildClickListener { adapter, view, position ->
          //  tv_send.visibility=View.VISIBLE
            //commentId=mList[position]
        }
    }
    fun setData(array: MutableList<WorksBean.ListBean.HotCommentBean>){
        mList=array
        //开始适配rv

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
        adapter?.setNewData(mList)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_close->{ dismiss()}
            R.id.tv_send->{//发送

            }
        }
    }
}