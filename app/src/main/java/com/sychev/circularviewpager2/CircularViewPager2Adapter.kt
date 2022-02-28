package com.sychev.circularviewpager2

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class CircularViewPager2Adapter <T>(
    fragment: Fragment,
    private val initialItems: List<T>
): FragmentStateAdapter(fragment) {

    private val finalItems = listOf(initialItems.last()) + initialItems + initialItems.first()

    final override fun getItemCount(): Int = finalItems.count()

    abstract fun onCreateFragment(position: Int, items: List<T>): Fragment

    final override fun createFragment(position: Int): Fragment = onCreateFragment(position, finalItems)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.scrollToPosition(1)
        recyclerView.addOnScrollListener(InfiniteScrollBehaviour(itemCount, recyclerView.layoutManager as LinearLayoutManager))
    }

    inner class InfiniteScrollBehaviour(
        private val itemCount: Int,
        private val layoutManager: LinearLayoutManager
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val firstItemVisible = layoutManager.findFirstVisibleItemPosition()
            val lastItemVisible = layoutManager.findLastVisibleItemPosition()
            if (firstItemVisible == (itemCount - 1) && dx > 0) {
                recyclerView.scrollToPosition(1)
            } else if (lastItemVisible == 0 && dx < 0) {
                recyclerView.scrollToPosition(itemCount - 2)
            }
        }
    }

}