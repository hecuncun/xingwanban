package com.cvnchina.xingwanban.ui.fragment

import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.aliyun.svideo.editor.EditorMediaActivity
import com.aliyun.svideo.editor.bean.AlivcEditInputParam
import com.aliyun.svideo.recorder.activity.AlivcSvideoRecordActivity
import com.aliyun.svideo.recorder.bean.AlivcRecordInputParam
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.bean.MsgCountBean
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.net.CallbackListObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import com.cvnchina.xingwanban.ui.activity.MsgActivity
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by heCunCun on 2020/4/29
 */
class HomeFragment :BaseFragment(), View.OnClickListener {
    private var worksFragment:WorksFragment?=null
    private var draftFragment:DraftFragment?=null
    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
    override fun attachLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
    }

    override fun initListener() {
        rl_works.setOnClickListener(this)
        rl_draft.setOnClickListener(this)
        rl_works.performClick()
        tv_msg.setOnClickListener {
            startActivity(Intent(activity, MsgActivity::class.java))
        }

        ll_edit.setOnClickListener {
            val param = AlivcEditInputParam.Builder()
                .build()
            EditorMediaActivity.startImport(context, param)
        }
        ll_take.setOnClickListener {
            val recordParam = AlivcRecordInputParam.Builder()
                .build()
            AlivcSvideoRecordActivity.startRecord(context, recordParam)
        }
    }

    override fun lazyLoad() {
        //获取未读消息数
        if (isLogin){
            val msgCountCall = SLMRetrofit.instance.api.msgCountCall()
            msgCountCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<MsgCountBean>(){
                override fun onSucceed(t: MsgCountBean) {
                    if (t.msg=="1"){
                        if (t.count==0){tv_msg.visibility=View.GONE } else tv_msg.visibility=View.VISIBLE
                        tv_msg.text="您有${t.count}条新消息"
                    }else{
                        showToast(t.msgCondition)
                    }

                }

                override fun onFailed() {

                }
            })
        }

    }

    override fun onClick(v: View) {
        val transaction =activity!!.supportFragmentManager.beginTransaction()
        hideAllFragment(transaction)
        when(v.id){
            R.id.rl_works->{
                tv_works.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_draft.setTextColor(resources.getColor(R.color.color_gray_999999))
                iv_works.visibility=View.VISIBLE
                iv_draft.visibility=View.INVISIBLE

                if (worksFragment==null){
                    worksFragment=WorksFragment.getInstance()
                    transaction.add(R.id.rl_container, worksFragment!!)
                }
                transaction.show(worksFragment!!)
            }
            R.id.rl_draft->{
                tv_draft.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_works.setTextColor(resources.getColor(R.color.color_gray_999999))
                iv_works.visibility=View.INVISIBLE
                iv_draft.visibility=View.VISIBLE
                if (draftFragment==null){
                    draftFragment= DraftFragment.getInstance()
                    transaction.add(R.id.rl_container, draftFragment!!)
                }
                transaction.show(draftFragment!!)
            }
        }
        transaction.commit()
    }

    private fun hideAllFragment(transaction: FragmentTransaction) {
        draftFragment?.let {
            transaction.hide(it)
        }
        worksFragment?.let {
            transaction.hide(it)
        }

    }
}