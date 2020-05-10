package com.cvnchina.xingwanban.ui.activity

import android.content.Intent
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.base.BaseNoDataBean
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.net.CallbackListObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_login_other.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020/4/27
 */
class LoginOtherActivity : BaseActivity() {
    override fun attachLayoutRes(): Int {
        return R.layout.activity_login_other
    }

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text = "登录"
        toolbar_right_tv.visibility = View.VISIBLE
        toolbar_right_tv.text = "随便看看"
        toolbar_right_tv.setTextColor(resources.getColor(R.color.color_gray_999999))
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        tv_get_code.setOnClickListener {
            //获取验证码
            val phone = et_phone.text.toString().trim()
            val observable = SLMRetrofit.getInstance().api.phoneCodeCall(phone)
            observable.compose(ThreadSwitchTransformer())
                .subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                    override fun onSucceed(t: BaseNoDataBean?) {
                        if (t?.msg == "1") {
                            showToast("获取验证码成功")
                        } else {
                            showToast("获取验证码失败")
                        }
                    }

                    override fun onFailed() {

                    }

                })

        }
    }
}