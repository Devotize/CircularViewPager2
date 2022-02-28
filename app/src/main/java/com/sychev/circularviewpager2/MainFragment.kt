package com.sychev.circularviewpager2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    private lateinit var mainViewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        with(view) {
            mainViewPager = findViewById(R.id.main_view_pager)
            dotsIndicator = findViewById(R.id.main_dots_indicator)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
        initViewPager()
        initFlowCollectors()
    }

    private fun initViewPager() {
        mainViewPager.adapter = MainAdapter(
            this,
            viewModel.data
        )
        dotsIndicator.setViewPager2(mainViewPager)
        mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onPageSelected(position)
            }
        })
    }

    private fun initFlowCollectors() {
        viewModel.currentPageIndex.onEach {
            Log.d(TAG, "currentPageIndex: $it")
        }.launchIn(lifecycleScope)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

