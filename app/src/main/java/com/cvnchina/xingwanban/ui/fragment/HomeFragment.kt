package com.cvnchina.xingwanban.ui.fragment

import android.support.v4.app.FragmentTransaction
import android.view.View
import com.cvnchina.xingwanban.R
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by heCunCun on 2020/4/29
 */
class HomeFragment :BaseFragment(), View.OnClickListener {
    private var worksFragment:WorksFragment?=null
    private var draftFragment:DraftFragment?=null
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
    }

    override fun lazyLoad() {
    }

    override fun onClick(v: View) {
        val transaction =activity!!.supportFragmentManager.beginTransaction()
        hideAllFragment(transaction)
        when(v.id){
            R.id.rl_works->{
                tv_works.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_draft.setTextColor(resources.getColor(R.color.color_gray_999999))
                iv_works.visibility=View.VISIBLE
                iv_draft.visibility=View.INVISIBLE

                if (worksFragment==null){
                    worksFragment=WorksFragment.getInstance()
                    transaction.add(R.id.rl_container, worksFragment!!)
                }
                transaction.show(worksFragment!!)
            }
            R.id.rl_draft->{
                tv_draft.setTextColor(resources.getColor(R.color.color_gray_F9F9F9))
                tv_works.setTextColor(resources.getColor(R.color.color_gray_999999))
                iv_works.visibility=View.INVISIBLE
                iv_draft.visibility=View.VISIBLE
                if (draftFragment==null){
                    draftFragment= DraftFragment.getInstance()
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
}