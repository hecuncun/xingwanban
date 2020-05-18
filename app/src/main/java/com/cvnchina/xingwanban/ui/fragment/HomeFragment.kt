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
import com.cvnchina.xingwanban.ui.activity.MsgActivity
import com.cvnchina.xingwanban.ui.activity.ScanLoginActivity
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
        ll_scan.setOnClickListener {
            //二维码扫描
            jumpToScannerActivity()
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
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
                        val result = it.getString(CodeUtils.RESULT_STRING)
                        showToast("扫描结果为$result")
                        Logger.e("result=$result")
                            //扫描成功进入扫码登录页
                        val intent =Intent(activity, ScanLoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        showToast("扫描失败")
                    }
                }
            }
        }
    }
}