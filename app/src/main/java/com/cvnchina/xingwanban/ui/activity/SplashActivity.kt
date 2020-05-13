package com.cvnchina.xingwanban.ui.activity

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.bean.UpdateAppBean
import com.cvnchina.xingwanban.glide.GlideUtils
import com.cvnchina.xingwanban.net.CallbackObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * Created by hecuncun on 2019/11/12
 */
class SplashActivity : BaseActivity() {
    private var alphaAnimation: AlphaAnimation? = null


    override fun attachLayoutRes(): Int = R.layout.activity_splash
    override fun initData() {
        //获取启动页图片
        val updateAppCall = SLMRetrofit.instance.api.updateAppCall()
        updateAppCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackObserver<UpdateAppBean>(){
            override fun onSucceed(t: UpdateAppBean, desc: String?) {
               GlideUtils.showPlaceholder(this@SplashActivity,iv_splash,t.androidPic3,R.mipmap.splash)
            }

            override fun onFailed() {

            }
        })
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation?.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                   // jumpToMain()
                    jumpToGuide()

                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }

        splash_view.startAnimation(alphaAnimation)
    }

    private fun jumpToGuide() {
        val intent = Intent(this, GuideActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initListener() {
    }

}