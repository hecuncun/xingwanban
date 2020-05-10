package com.cvnchina.xingwanban.ui.fragment

import android.content.Intent
import android.view.View
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.bean.PersonalInfoBean
import com.cvnchina.xingwanban.glide.GlideUtils
import com.cvnchina.xingwanban.net.CallbackObserver
import com.cvnchina.xingwanban.net.SLMRetrofit
import com.cvnchina.xingwanban.net.ThreadSwitchTransformer
import com.cvnchina.xingwanban.ui.activity.*
import com.cvnchina.xingwanban.utils.CommonUtil
import com.flyco.dialog.widget.ActionSheetDialog
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2020/4/29
 */
class MineFragment : BaseFragment() {
    private var moreDialog: ActionSheetDialog? = null

    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun attachLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(view: View) {
        toolbar_title.text = "个人中心"
        toolbar_right_img.setImageResource(R.mipmap.icon_more)
        toolbar_right_img.visibility = View.VISIBLE
        moreDialog = ActionSheetDialog(activity, arrayOf("设置", "分享"), null)
        moreDialog?.run {
            isTitleShow(false)
            itemTextColor(resources.getColor(R.color.color_primary_bar))
            cancelText(resources.getColor(R.color.color_primary_bar))
        }
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
        tv_good_evaluate.setOnClickListener {
            //给个好评
            CommonUtil.toMarket(activity!!, "com.cvnchina.xingwanban", null)
            //CommonUtil.toMarket(activity!!,"com.tencent.mm",null)
        }
        tv_contact.setOnClickListener {
            //联系我们
            startActivity(Intent(activity, ContactUsActivity::class.java))
        }
        toolbar_right_img.setOnClickListener {
            //更多
            moreDialog?.show()
            moreDialog?.setOnOperItemClickL { parent, view, position, id ->
                moreDialog!!.dismiss()
                if (position==0){
                    //设置
                    startActivity(Intent(activity, SettingActivity::class.java))
                }else{
                    //分享
                }
            }
        }

    }

    override fun lazyLoad() {
        //请求个人信息
        val personalInfoCall = SLMRetrofit.instance.api.personalInfoCall()
        personalInfoCall.compose(ThreadSwitchTransformer()).subscribe(object:
            CallbackObserver<PersonalInfoBean>(){
            override fun onSucceed(t: PersonalInfoBean, desc: String) {
                //初始化个人信息
                initPersonalInfo(t)
            }

            override fun onFailed() {
            }
        })
    }

    /**
     * 设置个人信息
     */
    private fun initPersonalInfo(t: PersonalInfoBean) {
        GlideUtils.showCircle(iv_head_photo,t.headPic,R.mipmap.icon_def_head)
        tv_nick_name.text=t.nickName
        tv_id.text=t.id
        tv_sex.text=t.sex
        tv_age.text=t.age
        tv_star.text=t.constellation
        tv_city.text=t.location
    }
}