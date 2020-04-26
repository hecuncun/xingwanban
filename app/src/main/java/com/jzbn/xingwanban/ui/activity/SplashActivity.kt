package com.jzbn.xingwanban.ui.activity

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.jzbn.xingwanban.R
import com.jzbn.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * Created by hecuncun on 2019/11/12
 */
class SplashActivity : BaseActivity() {
    private var alphaAnimation: AlphaAnimation? = null


    override fun attachLayoutRes(): Int = R.layout.activity_splash
    override fun initData() {
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