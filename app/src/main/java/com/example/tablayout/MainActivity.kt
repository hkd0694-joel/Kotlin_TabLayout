package com.example.tablayout

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity
 * Class: MainActivity
 * Created by 한경동 (Joel) on 2021/05/23.
 * Description: TabLayout, ViewPager 를 보여줄 Main 화면
 */
class MainActivity : AppCompatActivity() {

    var fragmentIndex: Int?= null
    var isJoin = false
    var isTabChange = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentIndex = 0
        initViewPager()
    }

    private fun initViewPager() {

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_today)), getString(R.string.fragment_title_today))
        adapter.addItems(SampleFragment(getString(R.string.fragment_title_info)),getString(R.string.fragment_title_info))
        if(isJoin) {
            adapter.addItems(SampleFragment(getString(R.string.fragment_title_shinhan)),getString(R.string.fragment_title_shinhan))
            adapter.addItems(SampleFragment(getString(R.string.fragment_title_assets)),getString(R.string.fragment_title_assets))
            adapter.addItems(SampleFragment(getString(R.string.fragment_title_discover)),getString(R.string.fragment_title_discover))
        }

        vp_main.adapter = adapter
        vp_main.offscreenPageLimit = adapter.count

        if(isJoin) {
            tl_main.setupWithViewPager(vp_main)
        } else {
            val tabLay = tl_main.getChildAt(0) as LinearLayout
            val layout = tabLay.getChildAt(0) as LinearLayout
            val tvTab = layout.getChildAt(1) as TextView
            layout.setBackgroundResource(R.drawable.tab_btn_shpae)
            tvTab.setTextColor(Color.parseColor("#ffffff"))
            for (i in 1 until 4 ) {
                tabLay.getChildAt(i).isClickable = false
                tabLay.getChildAt(i).isSelected = false
                tabLay.getChildAt(i).isEnabled = false
            }
        }
        tl_main.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(!isJoin) {
                    if(tab?.position == 0) {
                        fragmentIndex = tab.position
                        vp_main.currentItem = tab.position
                        isTabChange = true
                    } else {
                        fragmentIndex = 1
                        vp_main.currentItem = 1
                        isTabChange = false
                    }
                } else {
                    fragmentIndex = tab.position
                    vp_main.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if(!isJoin) {
                    isTabChange = position == 0
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                if(ViewPager.SCROLL_STATE_IDLE == state) {
                    val tabLay = tl_main.getChildAt(0) as LinearLayout
                    val tParentLayout = tabLay.getChildAt(0) as LinearLayout        // 투데이 TextView 를 감싸는 부모 LinearLayout
                    val tChildTextView = tParentLayout.getChildAt(1) as TextView    // 투데이 Textview
                    val iParentLayout = tabLay.getChildAt(4) as LinearLayout        // 안내 TextView 를 감싸는 부모 LinearLayout
                    val iChildTextview = iParentLayout.getChildAt(1) as TextView    // 안내 Textview
                    if(isTabChange) {
                        tParentLayout.setBackgroundResource(R.drawable.tab_btn_shpae)
                        tChildTextView.setTextColor(Color.parseColor("#ffffff"))
                        iParentLayout.setBackgroundResource(R.drawable.tab_default_btn_shape)
                        iChildTextview.setTextColor(Color.parseColor("#000000"))
                    } else {
                        tParentLayout.setBackgroundResource(R.drawable.tab_default_btn_shape)
                        tChildTextView.setTextColor(Color.parseColor("#000000"))
                        iParentLayout.setBackgroundResource(R.drawable.tab_btn_shpae)
                        iChildTextview.setTextColor(Color.parseColor("#ffffff"))
                    }
                }
            }
        })

    }

    override fun onBackPressed() {
        when(fragmentIndex) {

        }
        super.onBackPressed()
    }

}