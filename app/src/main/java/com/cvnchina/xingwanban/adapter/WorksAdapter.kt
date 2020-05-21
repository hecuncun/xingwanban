package com.cvnchina.xingwanban.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.jzvd.JzvdStd
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
            .setText(R.id.tv_date, item.createtime)
            .setText(R.id.tv_state, if (item.ischeck == "n") "审核中" else "")
        if (item.contTags.isNotEmpty()) {
            helper.setText(R.id.tv_tag, item.contTags[0].tagName)//显示一个标签
        }
        val tvState = helper.getView<TextView>(R.id.tv_state)
        tvState.visibility = if (item.ischeck == "y") View.GONE else View.VISIBLE
        if (item.ischeck == "r") {
            tvState.text = "审核中"
        } else if (item.ischeck == "n") {
            tvState.text = "审核不通过"
        }

         val player = helper.getView<JzvdStd>(R.id.jz_player)
         player.setUp(item.contDownUrl,"",JzvdStd.SCREEN_FULLSCREEN)
         player.thumbImageView.scaleType=ImageView.ScaleType.CENTER_CROP
         player.backButton.visibility = View.INVISIBLE
         player.startButton.visibility=View.GONE
         player.replayTextView.visibility=View.GONE
         player.batteryTimeLayout.visibility = View.GONE
         player.fullscreenButton.visibility = View.INVISIBLE
         GlideUtils.showRound(player.thumbImageView,item.overimageurl,R.mipmap.ic_launcher,8)
         helper.addOnClickListener(R.id.iv_start)
         helper.addOnClickListener(R.id.iv_share)
         helper.addOnClickListener(R.id.iv_move)
         helper.addOnClickListener(R.id.tv_cover)

        val rv = helper.getView<RecyclerView>(R.id.recyclerView_evaluate)
        val adapter2 = EvaluateAdapter()
        rv.run {
            layoutManager = LinearLayoutManager(App.context)
            adapter = adapter2
        }
        if (item.hotComment != null) {
            if (item.hotComment.size < 4) {
                adapter2.setNewData(item.hotComment)
            } else {
                var list2 = mutableListOf<WorksBean.ListBean.HotCommentBean>()
                for (i in 1..3) {
                    list2.add(item.hotComment[i])
                }
                adapter2.setNewData(list2)
            }
        }


    }
}