package com.cvnchina.xingwanban.ui.activity


import android.content.Intent
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.widget.ClearCacheDialog
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020-5-5
 */
class SettingActivity : BaseActivity() {
    private var clearCacheDialog:ClearCacheDialog?=null

    override fun attachLayoutRes(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        clearCacheDialog= ClearCacheDialog(this)

    }

    override fun initView() {
        toolbar_title.text = "设置"
    }

    override fun initListener() {
        rl_clear_cache.setOnClickListener { //清除缓存
            clearCacheDialog?.show()
            clearCacheDialog?.setOnConfirmListener(View.OnClickListener {
                showToast("缓存已清理完毕")
                clearCacheDialog!!.dismiss()
            })
        }

        rl_app_update.setOnClickListener { //app检查更新
            val intent =Intent(this,AppUpdateActivity::class.java)
            startActivity(intent)
        }

        rl_feedback.setOnClickListener {
            val intent =Intent(this,FeedBackActivity::class.java)
            startActivity(intent)
        }

    }
}