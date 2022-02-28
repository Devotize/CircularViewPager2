package com.sychev.circularviewpager2

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sychev.circularviewpager2.data.SimpleData

class MainAdapter(
    fragment: Fragment,
    items: List<SimpleData>
): CircularViewPager2Adapter<SimpleData>(fragment, items) {

    override fun onCreateFragment(position: Int, items: List<SimpleData>): Fragment =
        MainPageFragment.createInstance(items[position])

}