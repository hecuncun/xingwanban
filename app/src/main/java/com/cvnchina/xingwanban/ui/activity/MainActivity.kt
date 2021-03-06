package com.cvnchina.xingwanban.ui.activity

import android.os.Environment
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import cn.jzvd.JzvdStd
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.bean.UpdateAppBean
import com.cvnchina.xingwanban.event.LogoutEvent
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.net.CallbackObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import com.cvnchina.xingwanban.ui.fragment.HomeFragment
import com.cvnchina.xingwanban.ui.fragment.MineFragment
import com.cvnchina.xingwanban.utils.PackageUtils
import com.cvnchina.xingwanban.widget.FullScreenDialog
import kotlinx.android.synthetic.main.activity_main.*
import listener.UpdateDownloadListener
import model.UpdateConfig
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import update.UpdateAppUtils

class MainActivity : BaseActivity(), View.OnClickListener {
    private var homeFragment: HomeFragment? = null
    private var mineFragment: MineFragment? = null
    private var dialog: FullScreenDialog? = null
    override fun useEventBus(): Boolean = true
    override fun attachLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        if (checkPermissions(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.RECORD_AUDIO
                )
            )
        ) {

        } else {
            requestPermission(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.RECORD_AUDIO
                ), 0x333
            )
        }

        //获取启动页图片
        val updateAppCall = SLMRetrofit.instance.api.updateAppCall()
        updateAppCall.compose(ThreadSwitchTransformer()).subscribe(object :
            CallbackObserver<UpdateAppBean>(){
            override fun onSucceed(updateAppBean: UpdateAppBean, desc: String?) {
                if (updateAppBean!=null){
                    if (PackageUtils.getVersionCode(this@MainActivity)<updateAppBean.appVersion.toInt()){
                        // 更新配置
                        val updateConfig = UpdateConfig().apply {
                            force = updateAppBean.isForcedUpdate=="1"
                            checkWifi = true
                            needCheckMd5 = false
                            isShowNotification = true
                            notifyImgRes = R.mipmap.logo
                            apkSavePath = Environment.getExternalStorageDirectory().absolutePath +"/xwb"
                            apkSaveName = "xwb"
                        }

                        UpdateAppUtils
                            .getInstance()
                            .apkUrl(updateAppBean.downloadUrl?:"")
                            .updateTitle("")
                            .updateContent(updateAppBean.updateDesc)
                            .updateConfig(updateConfig)
                            //.uiConfig(uiConfig)
                            .setUpdateDownloadListener(object : UpdateDownloadListener {
                                override fun onDownload(progress: Int) {

                                }

                                override fun onError(e: Throwable) {

                                }

                                override fun onFinish() {

                                }

                                override fun onStart() {

                                }
                                // do something
                            })
                            .update()
                    }
                }
            }

            override fun onFailed() {

            }
        })
    val updateAppBean =  intent.getParcelableExtra<UpdateAppBean>("updateAppBean")


    }

    override fun initView() {
        dialog = FullScreenDialog(this)
    }

    override fun initListener() {
        tv_home.setOnClickListener(this)
        tv_mine.setOnClickListener(this)
        tv_home.performClick()

        iv_publish.setOnClickListener {
            dialog?.show()
        }
    }

    override fun onClick(v: View) {
        val transaction = supportFragmentManager.beginTransaction()
        hideAllFragment(transaction)
        when (v.id) {
            R.id.tv_home -> {
                tv_home.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_mine.setTextColor(resources.getColor(R.color.color_gray_999999))
                if (homeFragment == null) {
                    homeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.fragment_container, homeFragment!!)
                }
                transaction.show(homeFragment!!)
            }
            R.id.tv_mine -> {
                tv_mine.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_home.setTextColor(resources.getColor(R.color.color_gray_999999))
                if (mineFragment == null) {
                    mineFragment = MineFragment.getInstance()
                    transaction.add(R.id.fragment_container, mineFragment!!)
                }
                transaction.show(mineFragment!!)
            }
        }
        transaction.commit()
    }

    private fun hideAllFragment(transaction: FragmentTransaction) {
        homeFragment?.let {
            transaction.hide(it)
        }
        mineFragment?.let {
            transaction.hide(it)
        }

    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun logout(event: LogoutEvent) {
        finish()
    }
    override fun onBackPressed() {
        if (JzvdStd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JzvdStd.goOnPlayOnPause()
    }

    override fun onResume() {
        super.onResume()
        JzvdStd.goOnPlayOnResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        JzvdStd.releaseAllVideos()
    }
}
