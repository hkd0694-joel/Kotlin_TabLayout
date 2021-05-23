package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab_layout.view.*
import kotlinx.android.synthetic.main.fragment_sample.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
    }

    private fun createView(name: String): View {
        val view = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        view.tv_title.text = name
        return view
    }

    private fun initViewPager() {
        val todayFragment = SampleFragment()
        todayFragment.name = "투데이"
        val shinhanFragment = SampleFragment()
        shinhanFragment.name = "신한Pay"
        val assetsFragment = SampleFragment()
        assetsFragment.name = "자산"

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addItems(todayFragment)
        adapter.addItems(shinhanFragment)
        adapter.addItems(assetsFragment)

        vp_main.adapter = adapter
        tl_main.setupWithViewPager(vp_main)

        tl_main.getTabAt(0)?.customView = createView("투데이")
        tl_main.getTabAt(1)?.customView = createView("신한Pay")
        tl_main.getTabAt(2)?.customView = createView("자산")

    }
}