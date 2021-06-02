package com.example.tablayout

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_timeline.*

/**
 * MainActivity
 * Class: MainActivity
 * Created by 한경동 (Joel) on 2021/05/23.
 * Description: TabLayout, ViewPager 를 보여줄 Main 화면
 */
class MainActivity : AppCompatActivity() {

    var fragmentIndex: Int?= null
    var isJoin = true
    var isTabChange = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentIndex = 0
        initViewPager()
        tl_main.setSelectedTabIndicatorColor(android.R.color.transparent)
        // 기본 색상, 선택시 쌕상
        tl_main.tabRippleColor = null
        // tl_main.setTabRippleColorResource(R.color.ok)
        //tl_main.setTabTextColors(Color.parseColor("#555555"), Color.parseColor("#000000"));
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
                Log.d("onPageTabSelected", "${tab?.position}asd")
                if(!isJoin) {
                    Log.d("onPageTabSelect", "${tab?.position} : $fragmentIndex : $isTabChange")
                    fragmentIndex = tab?.position
                    if(tab?.position == 0) {
                        vp_main.currentItem = tab.position
                        isTabChange = true
                    } else if(tab?.position == 4){
                        vp_main.currentItem = 1
                        isTabChange = false
                    }
                } else {
                    fragmentIndex = tab?.position
                    vp_main.currentItem = tab?.position!!
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("onPageUnselected", "${tab?.position}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("onPageReselected", "${tab?.position}")
            }

        })

        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                if(!isJoin) {
                    isTabChange = position == 0
                    Log.d("onPageState", "$position : $isTabChange")
                }
            }
            override fun onPageScrollStateChanged(state: Int) {
                if(ViewPager.SCROLL_STATE_IDLE == state) {
                    Log.d("onPageState", "$state")
                    if(!isJoin) {
                        val tabLay = tl_main.getChildAt(0) as LinearLayout              // TabLayout 자식 LinearLayout
                        val tParentLayout = tabLay.getChildAt(0) as LinearLayout        // 투데이 TextView 를 감싸는 부모 LinearLayout
                        val tChildTextView = tParentLayout.getChildAt(1) as TextView    // 투데이 Textview
                        val iParentLayout = tabLay.getChildAt(4) as LinearLayout        // 안내 TextView 를 감싸는 부모 LinearLayout
                        val iChildTextview = iParentLayout.getChildAt(1) as TextView    // 안내 Textview
                        fragmentIndex = if(isTabChange) {
                            tParentLayout.setBackgroundResource(R.drawable.tab_btn_shpae)
                            tChildTextView.setTextColor(Color.parseColor("#ffffff"))
                            iParentLayout.setBackgroundResource(R.drawable.tab_default_btn_shape)
                            iChildTextview.setTextColor(Color.parseColor("#000000"))
                            0
                        } else {
                            tParentLayout.setBackgroundResource(R.drawable.tab_default_btn_shape)
                            tChildTextView.setTextColor(Color.parseColor("#000000"))
                            iParentLayout.setBackgroundResource(R.drawable.tab_btn_shpae)
                            iChildTextview.setTextColor(Color.parseColor("#ffffff"))
                            4
                        }
                    }
                }
            }
        })
    }

    override fun onBackPressed() {
        when(fragmentIndex) {
            0 -> {
                super.onBackPressed()
            }
            1 -> {
                fragmentIndex = 0
                vp_main.currentItem = fragmentIndex as Int
            }
            2 -> {
                fragmentIndex = 1
                vp_main.currentItem = fragmentIndex as Int
            }
            3 -> {
                fragmentIndex = 2
                vp_main.currentItem = fragmentIndex as Int
            }
            4 -> {
                if(!isJoin) {
                    fragmentIndex = 0
                    isTabChange = true
                    tl_main.getTabAt(0)?.select()
                } else fragmentIndex = 3
                vp_main.currentItem = fragmentIndex as Int
            }
        }
    }
}