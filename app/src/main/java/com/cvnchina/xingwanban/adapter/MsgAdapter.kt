package com.cvnchina.xingwanban.adapter

import com.aliyun.svideo.common.baseAdapter.BaseQuickAdapter
import com.aliyun.svideo.common.baseAdapter.BaseViewHolder
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.bean.MsgBean

/**
 * Created by hecuncun on 2020-5-13
 */
class MsgAdapter :BaseQuickAdapter<MsgBean.DataListBean,BaseViewHolder>(R.layout.item_msg_list) {
    override fun convert(helper: BaseViewHolder, item: MsgBean.DataListBean?) {
        item?:return
        helper.setText(R.id.tv_nick_name,item.nickName)
              .setText(R.id.tv_content,item.content)
            //.setText(R.id.tv_date,item.)
    }
}