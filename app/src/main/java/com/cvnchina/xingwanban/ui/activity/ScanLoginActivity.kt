package com.cvnchina.xingwanban.ui.activity

import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.base.BaseNoDataBean
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.net.CallbackListObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_scan_login.*

/**
 * Created by hecuncun on 2020-5-18
 */
class ScanLoginActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_scan_login

    private var imei=""

    override fun initData() {
        imei = intent.getStringExtra("imei")!!
    }

    override fun initView() {
    }

    override fun initListener() {
        tv_login.setOnClickListener {
            val scanLoginCall = SLMRetrofit.instance.api.scanLoginCall(imei)
            scanLoginCall.compose(ThreadSwitchTransformer())
                .subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                    override fun onSucceed(t: BaseNoDataBean) {
                        if (t.msg == "1") {
                            showToast(t.msgCondition)
                            finish()
                        }
                    }

                    override fun onFailed() {

                    }
                })
        }

        tv_finish.setOnClickListener {
            finish()
        }
    }
}