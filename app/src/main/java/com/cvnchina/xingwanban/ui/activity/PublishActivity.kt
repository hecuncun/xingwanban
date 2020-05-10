package com.cvnchina.xingwanban.ui.activity

import android.content.Intent
import com.aliyun.apsara.alivclittlevideo.activity.AlivcLittlePreviewActivity
import com.aliyun.svideo.common.utils.image.ImageLoaderImpl
import com.aliyun.svideo.common.utils.image.ImageLoaderOptions
import com.aliyun.svideo.sdk.external.struct.common.AliyunVideoParam
import com.cvnchina.xingwanban.R
import com.cvnchina.xingwanban.base.BaseActivity
import kotlinx.android.synthetic.main.activity_publish.*

/**
 * Created by hecuncun on 2020-5-9
 */
class PublishActivity : BaseActivity() {
    private val KEY_PARAM_VIDEO_PARAM = "videoParam"
    private val KEY_PARAM_VIDEO_RATIO = "key_param_video_ratio"
    private val KEY_PARAM_CONFIG = "project_json_path"
    private val KEY_PARAM_THUMBNAIL = "svideo_thumbnail"
    /**
     * 配置文件地址
     */
    private var mConfigPath: String? = null
    /**
     * 封面图片地址
     */
    private var mThumbnailPath: String? = null
    private var videoRatio = 0f
    private var mVideoPram: AliyunVideoParam? = null
    override fun attachLayoutRes(): Int {
        return R.layout.activity_publish
    }

    override fun initData() {
        val intent = intent
        mConfigPath = intent.getStringExtra(KEY_PARAM_CONFIG)
        mThumbnailPath = intent.getStringExtra(KEY_PARAM_THUMBNAIL)
        videoRatio = intent.getFloatExtra(KEY_PARAM_VIDEO_RATIO, 0f)
        mVideoPram = intent.getSerializableExtra(KEY_PARAM_VIDEO_PARAM) as AliyunVideoParam
        ImageLoaderImpl().
            loadImage(this, mThumbnailPath!!, ImageLoaderOptions.Builder().skipMemoryCache().skipDiskCacheCache().build())
            .into(iv_cover)
    }

    override fun initView() {

    }

    override fun initListener() {
         tv_publish.setOnClickListener {//发布上传

         }

        iv_cover.setOnClickListener {
            val intent = Intent(this, AlivcLittlePreviewActivity::class.java)
            intent.putExtra(KEY_PARAM_CONFIG, mConfigPath)
            intent.putExtra(KEY_PARAM_VIDEO_PARAM, mVideoPram)
            //传入视频比列
            intent.putExtra(KEY_PARAM_VIDEO_RATIO, videoRatio)
            startActivity(intent)
        }

        ll_talk.setOnClickListener {
            startActivity(Intent(this,TalkActivity::class.java))
        }
        ll_sort.setOnClickListener {
            startActivity(Intent(this,SortActivity::class.java))
        }
        ll_location.setOnClickListener {
            startActivity(Intent(this,LocationActivity::class.java))
        }
        ll_permission.setOnClickListener {
            startActivity(Intent(this,CanShowActivity::class.java))
        }
    }
}