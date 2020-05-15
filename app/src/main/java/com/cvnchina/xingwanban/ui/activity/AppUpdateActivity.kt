package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.bean.UpdateAppBean
import kotlinx.android.synthetic.main.activity_app_update.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-5
 */
class AppUpdateActivity:BaseActivity() {
    override fun attachLayoutRes(): Int {
        return R.layout.activity_app_update
    }

    override fun initData() {
        val bean = intent.getParcelableExtra<UpdateAppBean>("updateAppBean")
        tv_desc.text=bean.updateDesc
        tv_app_version.text=bean.appVersion
        tv_notify.text="星顽半视频有新版本${bean.appVersion}更新啦！"
    }

    override fun initView() {
      toolbar_title.text="版本更新"
    }

    override fun initListener() {
        tv_update.setOnClickListener {
            //开始下载更新
        }
    }
}