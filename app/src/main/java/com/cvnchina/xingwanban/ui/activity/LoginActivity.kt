package com.cvnchina.xingwanban.ui.activity

import android.content.Intent
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.orhanobut.logger.Logger
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2020/4/25
 */
class LoginActivity:BaseActivity() {
    override fun attachLayoutRes(): Int {
       return R.layout.activity_login
    }

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text="登录"
        toolbar_right_tv.text="跳过"
        toolbar_right_tv.visibility= View.VISIBLE
    }

    override fun initListener() {
        tv_other_login.setOnClickListener {
           startActivity(Intent(this,LoginOtherActivity::class.java))
        }
        toolbar_right_tv.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        iv_qq.setOnClickListener {//QQ登录
            UMShareAPI.get(this@LoginActivity).getPlatformInfo(this@LoginActivity, SHARE_MEDIA.QQ, authListener)
        }
        iv_wb.setOnClickListener {//微博
            UMShareAPI.get(this@LoginActivity).getPlatformInfo(this@LoginActivity, SHARE_MEDIA.SINA, authListener)
        }
        iv_wx.setOnClickListener {//微信
            UMShareAPI.get(this@LoginActivity).getPlatformInfo(this@LoginActivity, SHARE_MEDIA.WEIXIN, authListener)
        }
    }

    /**
     * 第三方登录回调
     */
    internal var authListener: UMAuthListener = object : UMAuthListener {

        /**
         * 开始登录的回调
         * @param platform 第三方登录的平台名称
         */
        override fun onStart(platform: SHARE_MEDIA) {
            Logger.d("登录的第三方平台是:" + platform)
        }

        /**
         * 登录成功回调
         * @param platform
         * @param action
         * @param map
         */
        override fun onComplete(platform: SHARE_MEDIA, action: Int, map: Map<String, String>) {
            //  遍历map集合，取出QQ登录后回调给我们的信息
            for (key in map.keys) {
                Logger.d("key值是：" + key + "  对应的具体值:" + map[key] + "\n")
//              将取出的QQ账户信息存储到SharedPreferences中
               // ShareUtils.putString(this@LoginActivity, key, map[key])
            }
            Logger.d("登录成功")
        }

        /**
         * 失败
         * @param platform
         * @param action
         * @param t
         */
        override fun onError(platform: SHARE_MEDIA, action: Int, t: Throwable) {
            Logger.d("登录失败" + t.message)
        }

        /**
         * 取消登录的回调
         * @param platform
         * @param action
         */
        override fun onCancel(platform: SHARE_MEDIA, action: Int) {
            Logger.d("取消登录")
        }
    }

    /**
     * QQ登录必须加入此回调
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }
}