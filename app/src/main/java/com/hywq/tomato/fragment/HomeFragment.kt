package com.hywq.tomato.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hywq.tomato.R
import com.hywq.tomato.view.HttpsTrustManager
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.net.ssl.*

class HomeFragment : Fragment() {

    init {
        System.loadLibrary("ndk_yuwq")
    }

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

        val textJni = view.findViewById<TextView>(R.id.textJni)
        textJni.text = stringFromJNI().orEmpty()
    }

    external fun stringFromJNI(): String?


}