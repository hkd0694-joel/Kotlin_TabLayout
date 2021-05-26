package com.example.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_sample.*
import kotlinx.android.synthetic.main.fragment_sample.view.*


/**
 * SampleFragment
 * Class: SampleFragment
 * Created by 한경동 (Joel) on 2021/05/23.
 * Description: TabLayout, ViewPager 를 통해 화면 이동할 경우 붙여줄 Fragment
 */
class SampleFragment(var name: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()
        webview.webChromeClient = WebChromeClient()
        when (name) {
            "투데이" -> webview.loadUrl("https://m.blog.naver.com/PostView.naver?blogId=qbxlvnf11&logNo=221641795446&proxyReferer=https:%2F%2Fwww.google.com%2F")
            "신한Pay" -> webview.loadUrl("https://qastack.tistory.com/entry/Webview%EB%8A%94-%EB%AC%B4%ED%95%9C-%EC%8A%A4%ED%81%AC%EB%A1%A4-%EA%B0%80%EB%8A%A5-%EA%B3%B5%EB%B0%B1%EC%9D%84-%EC%9C%A0%EB%B0%9C%ED%95%A9%EB%8B%88%EB%8B%A4")
            "자산" -> webview.loadUrl("https://www.daum.net")
        }
        super.onViewCreated(view, savedInstanceState)
    }

}