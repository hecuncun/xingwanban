package com.cvnchina.xingwanban.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.aliyun.svideo.common.baseAdapter.BaseQuickAdapter
import com.aliyun.svideo.common.baseAdapter.BaseViewHolder
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.application.App
import com.cvnchina.xingwanban.bean.WorksBean
import com.cvnchina.xingwanban.glide.GlideUtils

/**
 * Created by hecuncun on 2020-5-15
 */
class WorksAdapter :
    BaseQuickAdapter<WorksBean.ListBean, BaseViewHolder>(R.layout.item_works_list) {
    override fun convert(helper: BaseViewHolder, item: WorksBean.ListBean?) {
        item ?: return
        helper.setText(R.id.tv_zan, item.haszannums)
            .setText(R.id.tv_evaluate, item.commentnums)
            .setText(R.id.tv_title, item.contSubTitle)
            .setText(R.id.tv_date,item.createtime)
            .setText(R.id.tv_state,if (item.ischeck=="n") "审核中" else "")
                if (item.contTags.isNotEmpty()){
                    helper.setText(R.id.tv_tag, item.contTags[0].tagName)//显示一个标签
                }
   helper.getView<TextView>(R.id.tv_state).visibility=if (item.ischeck=="n") View.VISIBLE else View.GONE
        helper.addOnClickListener(R.id.iv_start)
        helper.addOnClickListener(R.id.iv_share)
        helper.addOnClickListener(R.id.iv_move)
        val viCover = helper.getView<ImageView>(R.id.iv_cover)
        GlideUtils.showRound(viCover, item.overimageurl, R.mipmap.ic_launcher, 8)

        val rv = helper.getView<RecyclerView>(R.id.recyclerView_evaluate)
        val adapter2 =EvaluateAdapter()
        rv.run {
            layoutManager = LinearLayoutManager(App.context)
            adapter = adapter2
        }
        if(item.hotComment!=null){
            if (item.hotComment.size<4){
                adapter2.setNewData(item.hotComment)
            }else{
                var list2= mutableListOf<WorksBean.ListBean.HotCommentBean>()
                for(i in 1..3){
                    list2.add(item.hotComment[i])
                }
                adapter2.setNewData(list2)
            }
        }



    }
}