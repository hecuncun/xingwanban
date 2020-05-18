package com.cvnchina.xingwanban.ui.activity

import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import com.cvnchina.xingwanban.ext.showToast
import com.cvnchina.xingwanban.ui.fragment.HomeFragment
import com.cvnchina.xingwanban.ui.fragment.MineFragment
import com.cvnchina.xingwanban.widget.FullScreenDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    private var homeFragment:HomeFragment?=null
    private var mineFragment:MineFragment?=null
    private var dialog:FullScreenDialog?=null
    override fun attachLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        if (checkPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA,android.Manifest.permission.RECORD_AUDIO))){

        }else{
            requestPermission(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA,android.Manifest.permission.RECORD_AUDIO),0x333)
        }
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
        val transaction =supportFragmentManager.beginTransaction()
         hideAllFragment(transaction)
        when(v.id){
            R.id.tv_home->{
                tv_home.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_mine.setTextColor(resources.getColor(R.color.color_gray_999999))
                if (homeFragment==null){
                    homeFragment=HomeFragment.getInstance()
                    transaction.add(R.id.fragment_container, homeFragment!!)
                }
                transaction.show(homeFragment!!)
            }
            R.id.tv_mine->{
                tv_mine.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_home.setTextColor(resources.getColor(R.color.color_gray_999999))
                if (mineFragment==null){
                    mineFragment= MineFragment.getInstance()
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


}
