# Kotlin_TabLayout
TabLayout, WebView, ViewPager 를 이용한 화면 이동 ( Kotlin )

## PagerAdapter.kt

~~~kotlin

class PagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 써줘야함!!

    private var fragmentList: MutableList<SampleFragment> = ArrayList()

    // FragmentList Size
    override fun getCount(): Int = fragmentList.size

    // getItem Method (position)
    override fun getItem(position: Int): Fragment = fragmentList[position]

    // fragmentList Item 추가 Method
    fun addItems(fragment: SampleFragment) = fragmentList.add(fragment)

}

~~~

## SampleFragement.kt

~~~kotlin

class SampleFragment(var name : String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        view.tv_name.text = name
        return view
    }

}

~~~

## MainActivity.kt
~~~kotlin

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


~~~

## activity_main.xml

~~~kotlin

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/appbarLayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

            <com.google.android.material.appbar.CollapsingToolbarLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_scrollFlags="scroll|enterAlwaysCollapsed">

                <com.example.tablayout.component.CustomToolbar
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    app:popupTheme="@style/Theme.AppCompat.Light"
                    app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />

            </com.google.android.material.appbar.CollapsingToolbarLayout>

            <com.google.android.material.tabs.TabLayout
                android:id="@+id/tl_main"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:tabGravity="fill"
                app:tabIndicatorColor="@color/cardview_dark_background"
                app:tabMode="fixed" />

        </com.google.android.material.appbar.AppBarLayout>

        <androidx.viewpager.widget.ViewPager
            android:id="@+id/vp_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

        </androidx.viewpager.widget.ViewPager>

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

</androidx.constraintlayout.widget.ConstraintLayout>


~~~