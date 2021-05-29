package com.example.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.custom_tab_layout.*
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
        val view = inflater.inflate(R.layout.fragment_sample_2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_title.text = name
        super.onViewCreated(view, savedInstanceState)
    }

}