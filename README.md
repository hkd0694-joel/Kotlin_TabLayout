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

    /**
     * PagerAdapter.kt 초기화 및 Item 추가 Method 
     */
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