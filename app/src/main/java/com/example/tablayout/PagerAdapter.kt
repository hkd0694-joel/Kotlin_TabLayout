package com.example.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

/**
 * Tablayout
 * Class: PagerAdapter
 * Created by 한경동 (Joel) on 2021/05/23.
 * Description: ViewPager Adapter
 */
class PagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 써줘야함!!

    private var fragmentList: MutableList<SampleFragment> = ArrayList()

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

    fun addItems(fragment: SampleFragment) = fragmentList.add(fragment)

}