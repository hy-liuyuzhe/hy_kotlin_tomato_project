package com.hywq.tomato.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.asksira.loopingviewpager.LoopingViewPager
import com.blankj.utilcode.util.*
import com.bumptech.glide.Glide
import com.hywq.tomato.R
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.config.BannerConfig
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnPageChangeListener


class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, null, false)
    }

    lateinit var dataList: ArrayList<String>
    lateinit var text: TextView
    lateinit var banner: Banner<String, LoopingPageAdapter>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        banner = view.findViewById<Banner<String, LoopingPageAdapter>>(R.id.looping_viewPager)
        text = view.findViewById<TextView>(R.id.text)

        dataList = arrayListOf(
            "http://pic1.ymatou.com/G01/shangou/M00/1D/95/rBVlD2BIM3WAchMlAAV0bgycDG8632_500_667_n_w_o.jpg",
            "http://pic2.ymatou.com/2021/07/14/13/daa509563e1a4f90951965ab301b1db5_437_400_n_w_o.jpg",
            "http://pic2.ymatou.com/2021/07/14/13/a2ed0698b4cd4eac8b3aff0c89e011f5_1_1_n_w_o.jpg"
        )

        banner.addBannerLifecycleObserver(this)
            .addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    setCountIndex(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                }

            })
            .setAdapter(LoopingPageAdapter(dataList))
    }


    private fun setCountIndex(index: Int) {
        text.setText(
            java.lang.String.format(
                "%d/%d",
                index + 1,
                banner.getAdapter().realCount
            )
        )
    }

    inner class LoopingPageAdapter(dataList: List<String>) :
        BannerImageAdapter<String>(dataList) {
//        override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
//            val url = dataList[listPosition]
//            LogUtils.d("bind: " + url)
//            Glide.with(convertView).load(url).into(convertView.findViewById<ImageView>(R.id.image))
//        }
//
//        override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
//            return LayoutInflater.from(container.context)
//                .inflate(R.layout.item_pager, container, false)
//        }

        override fun onBindView(
            holder: BannerImageHolder?,
            data: String?,
            position: Int,
            size: Int
        ) {
            val url = dataList[position]
            LogUtils.d("bind: " + url)
            Glide.with(activity!!).load(url).into(holder!!.imageView)
        }

    }
}