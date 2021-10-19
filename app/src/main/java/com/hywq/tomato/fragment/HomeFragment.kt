package com.hywq.tomato.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.hywq.tomato.R
import com.hywq.tomato.view.HttpsTrustManager
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.net.ssl.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HttpsTrustManager.allowAllSSL()

        val webView = view.findViewById<WebView>(R.id.webView)
//        webView.loadUrl("https://m.ymatou.com/consignment/myCoupon?winType=19&bgcolor=ffffff")
        webView.loadUrl("https://m.ymatou.com/consignment/myCoupon?bgcolor=ffffff&UserId=26279592&AccessToken=0E1E1DE6CBB40F0D6F0E40B2E1877F9429F6FDBE1DE4581428455CE629E8C8A1F629465716CE192EBC22A5FD96744330F03B8FE86926DC70&DeviceId=ffffffff-d173-43c3-63a9-e1b41a6bf5df&DeviceToken=ffffffff-d173-43c3-63a9-e1b41a6bf5df&imei=867165046099191&winType=19&oaid=3f6ef1e7832baa5f")
    }
}