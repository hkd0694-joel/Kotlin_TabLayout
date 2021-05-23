# Kotlin_TabLayout
TabLayout, ViewPager 를 이용한 화면 이동 ( Kotlin )


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