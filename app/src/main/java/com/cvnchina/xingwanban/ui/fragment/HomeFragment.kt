package com.cvnchina.xingwanban.ui.fragment

import android.Manifest
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
import com.cvnchina.xingwanban.ui.activity.LoginActivity
import com.cvnchina.xingwanban.ui.activity.MsgActivity
import com.cvnchina.xingwanban.ui.activity.ScanLoginActivity
import com.cvnchina.xingwanban.ui.activity.WebViewActivity
import com.lhzw.bluetooth.base.BaseFragment
import com.orhanobut.logger.Logger
import com.uuzuche.lib_zxing.activity.CaptureActivity
import com.uuzuche.lib_zxing.activity.CodeUtils
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by heCunCun on 2020/4/29
 */
class HomeFragment : BaseFragment(), View.OnClickListener {
    private val REQUEST_CODE = 0x333
    private val PERMISS_REQUEST_CODE = 0x356

    private var worksFragment: WorksFragment? = null
    private var draftFragment: DraftFragment? = null

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
            if (isLogin) {
                startActivity(Intent(activity, MsgActivity::class.java))
            } else {
                startActivity(Intent(activity, LoginActivity::class.java))
            }

        }

        ll_edit.setOnClickListener {
            if (isLogin) {
                val param = AlivcEditInputParam.Builder()
                    .build()
                EditorMediaActivity.startImport(context, param)
            } else {
                startActivity(Intent(activity, LoginActivity::class.java))
            }

        }
        ll_take.setOnClickListener {
            if (isLogin) {
                val recordParam = AlivcRecordInputParam.Builder()
                    .build()
                AlivcSvideoRecordActivity.startRecord(context, recordParam)
            } else {
                startActivity(Intent(activity, LoginActivity::class.java))
            }

        }
        ll_scan.setOnClickListener {
            //二维码扫描
            if (isLogin) {
                jumpToScannerActivity()
            } else {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
        ll_edit_container.setOnClickListener {
            if (isLogin) {
                val param = AlivcEditInputParam.Builder()
                    .build()
                EditorMediaActivity.startImport(context, param)
            } else {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
    }


    private fun jumpToScannerActivity() {// Manifest.permission.VIBRATE允许访问振动设备
        if (checkPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.VIBRATE))) {
            val intent = Intent(activity, CaptureActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        } else {
            requestPermission(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.VIBRATE),
                PERMISS_REQUEST_CODE
            )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISS_REQUEST_CODE) {
            val intent = Intent(activity, CaptureActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun lazyLoad() {
        //获取未读消息数
        if (isLogin) {
            val msgCountCall = SLMRetrofit.instance.api.msgCountCall()
            msgCountCall.compose(ThreadSwitchTransformer())
                .subscribe(object : CallbackListObserver<MsgCountBean>() {
                    override fun onSucceed(t: MsgCountBean) {
                        if (t.msg == "1") {
                            if (t.count == 0) {
                                tv_msg.visibility = View.GONE
                            } else tv_msg.visibility = View.VISIBLE
                            tv_msg.text = "您有${t.count}条新消息"
                        } else {
                            showToast(t.msgCondition)
                        }

                    }

                    override fun onFailed() {

                    }
                })
        }

    }

    override fun onClick(v: View) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        hideAllFragment(transaction)
        when (v.id) {
            R.id.rl_works -> {
                tv_works.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_draft.setTextColor(resources.getColor(R.color.color_gray_999999))
                iv_works.visibility = View.VISIBLE
                iv_draft.visibility = View.INVISIBLE

                if (worksFragment == null) {
                    worksFragment = WorksFragment.getInstance()
                    transaction.add(R.id.rl_container, worksFragment!!)
                }
                transaction.show(worksFragment!!)
            }
            R.id.rl_draft -> {
                if (!isLogin) {
                    startActivity(Intent(activity, LoginActivity::class.java))
                    return
                }
                tv_draft.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_works.setTextColor(resources.getColor(R.color.color_gray_999999))
                iv_works.visibility = View.INVISIBLE
                iv_draft.visibility = View.VISIBLE
                if (draftFragment == null) {
                    draftFragment = DraftFragment.getInstance()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            //扫描结果
            if (data != null) {
                data.extras?.let {
                    if (it.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        var result = it.getString(CodeUtils.RESULT_STRING)
                        Logger.e("二维码==$result")
                        if (result != null&& result.contains("&doType=")) {
//                            result =
//                                "http://10.1.17.75:8055/comment?username=13810817985&token=02SGY7BApBRCs5AVsIEXV6VEgQH2Y@DBRXEX56UEELBHJ8VE0FBnV6SVtcXCooAFsIESUqV05RUiYpXBgCAHB4ARsKAiAvUBxUVyFyBEgAUHVyR1UQRjcuFxdTXiFpX1sDAHx6VUEDBH1zUFtP&imei=1820e9306332d5211bef5fb7e84a81c4&video_id=215976&doType=comment"
                            val str1 = result.substring(0, result.indexOf("&doType="))
                            val type = result.substring(str1.length + 8, result.length)
                            Logger.e("type=$type")
                            when (type) {
                                "comment" -> {//H5页面
                                    if (str1.contains("username=")) {//有userName 字段
                                        val username = str1.substring(
                                            str1.indexOf("username=") + 9,
                                            str1.indexOf("&token")
                                        )
                                        val locToken = str1.substring(str1.indexOf("&token=")+7,str1.indexOf("&imei="))
                                        Logger.e("username==$username")
                                        if (username == token) {//用户相同  跳H5
                                            val url = str1
                                            val intent = Intent(activity, WebViewActivity::class.java)
                                            intent.putExtra("type", 5)
                                            intent.putExtra("url", url)
                                            startActivity(intent)
                                        } else {//不同  跳是否同步登陆
                                            val imei=result.substring(result.indexOf("&imei=")+6,result.indexOf("&video_id="))
                                            val intent = Intent(activity, ScanLoginActivity::class.java)
                                            intent.putExtra("state","1")
                                            intent.putExtra("imei", imei)
                                            intent.putExtra("locUrl", str1)
                                            intent.putExtra("locToken",locToken)
                                            intent.putExtra("username",username)
                                            startActivity(intent)
                                        }
                                    } else {//没有userName字段
                                        val intent = Intent(activity, ScanLoginActivity::class.java)
                                        intent.putExtra("state","2")
                                        intent.putExtra("locUrl", str1)
                                        startActivity(intent)
                                    }
//                                    val imei=result.substring(result.indexOf("&imei=")+6,result.indexOf("&video_id="))
//                                    val intent =Intent(activity, WebViewActivity::class.java)
//                                    intent.putExtra("type",5)
//                                    intent.putExtra("url",url)
//                                    startActivity(intent)
                                }
                                "login" -> {
                                    //扫描成功进入扫码登录页
                                    val url = str1
                                    val intent = Intent(activity, WebViewActivity::class.java)
                                    intent.putExtra("type", 5)
                                    intent.putExtra("url", url)
                                    startActivity(intent)
                                }
                                "upload" -> {
                                    if (isLogin) {
                                        val recordParam = AlivcRecordInputParam.Builder()
                                            .build()
                                        AlivcSvideoRecordActivity.startRecord(context, recordParam)
                                    } else {
                                        startActivity(Intent(activity, LoginActivity::class.java))
                                    }
                                }
                                else -> {
                                    showToast("不是目标二维码$result")
                                }
                            }
                        }else{
                            showToast("目标二维码错误$result")
                        }


                    } else {
                        showToast("扫描失败")
                    }
                }
            }
        }
    }
}