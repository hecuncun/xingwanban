package com.cvnchina.xingwanban.ui.fragment

import android.content.Intent
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.ui.activity.*
import com.cvnchina.xingwanban.utils.CommonUtil
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2020/4/29
 */
class MineFragment :BaseFragment(){
    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }
    override fun attachLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(view: View) {
        toolbar_title.text="个人中心"
        toolbar_right_img.setImageResource(R.mipmap.icon_more)
        toolbar_right_img.visibility=View.VISIBLE
    }

    override fun initListener() {
        iv_head_photo.setOnClickListener {
          startActivity(Intent(activity, PersonInfoActivity::class.java))
        }
        ll_person_info.setOnClickListener {
            startActivity(Intent(activity, InfoEditActivity::class.java))
        }
        tv_about.setOnClickListener {
            startActivity(Intent(activity, AboutUsActivity::class.java))
        }
        tv_agreement.setOnClickListener {
            startActivity(Intent(activity, AgreementActivity::class.java))
        }
        tv_good_evaluate.setOnClickListener { //给个好评
            CommonUtil.toMarket(activity!!,"com.cvnchina.xingwanban",null)
            //CommonUtil.toMarket(activity!!,"com.tencent.mm",null)
        }
        tv_contact.setOnClickListener { //联系我们
            startActivity(Intent(activity, ContactUsActivity::class.java))
        }

    }

    override fun lazyLoad() {
    }
}