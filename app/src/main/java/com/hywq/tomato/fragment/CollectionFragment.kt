package com.hywq.tomato.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ScreenUtils
import com.hywq.tomato.R


class CollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layout1 = view.findViewById<View>(R.id.layout1)
        val image1 = view.findViewById<View>(R.id.image1)
        val image2 = view.findViewById<View>(R.id.image2)
        val image3 = view.findViewById<View>(R.id.image3)
        val url =
            "https://m.ymatou.com/consignment/rankingDetail?pagepartid={模块id}&classifyid={分类id}&classifyname={分类名称}&productid={默认选中商品id}" +
                    "&backtype=1&winType=19"
        val winType = Uri.parse(url).getQueryParameter("winType")
        Log.d("liuyuzhe", "onMeasure: $winType")
        ScreenUtils.getScreenWidth()

        layout1.setOnClickListener {
//            image1.pivotX = image1.width.toFloat()
//            image1.pivotY = 0f
//            image1.rotation = -30f

//            image3.apply {
//                pivotX = 0f
//                pivotY = height.toFloat()
//                rotation = 0f
//            }

            image2.apply {
                pivotX = 0f
                pivotY = height.toFloat()
                rotation = -35f
            }

            image1.apply {
                pivotX = 0f
                pivotY = height.toFloat()
                rotation = -20f
            }
        }
    }

}