package com.example.tablayout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab_layout.view.*

/**
 * MainActivity
 * Class: MainActivity
 * Created by 한경동 (Joel) on 2021/05/23.
 * Description: TabLayout, ViewPager 를 보여줄 Main 화면
 */
class MainActivity : AppCompatActivity() {

    var fragmentIndex: Int?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentIndex = 0
        initViewPager()
    }

    private fun initViewPager() {

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_today)), getString(R.string.fragment_title_today))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_shinhan)),getString(R.string.fragment_title_shinhan))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_assets)),getString(R.string.fragment_title_assets))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_discover)),getString(R.string.fragment_title_discover))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_info)),getString(R.string.fragment_title_info))
        vp_main.adapter = adapter
        vp_main.offscreenPageLimit = adapter.count
        tl_main.setupWithViewPager(vp_main)

        tl_main.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                fragmentIndex = tab?.position
                vp_main.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    override fun onBackPressed() {
        when(fragmentIndex) {

        }
        super.onBackPressed()
    }

}