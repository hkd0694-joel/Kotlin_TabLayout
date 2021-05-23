package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab_layout.view.*
import kotlinx.android.synthetic.main.fragment_sample.view.*

/**
 * MainActivity
 * Class: MainActivity
 * Created by 한경동 (Joel) on 2021/05/23.
 * Description: TabLayout, ViewPager 를 보여줄 Main 화면
 */
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

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_today)))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_shinhan)))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_assets)))

        vp_main.adapter = adapter
        tl_main.setupWithViewPager(vp_main)

        tl_main.getTabAt(0)?.customView = createView(getString(R.string.fragment_title_today))
        tl_main.getTabAt(1)?.customView = createView(getString(R.string.fragment_title_shinhan))
        tl_main.getTabAt(2)?.customView = createView(getString(R.string.fragment_title_assets))

    }
}