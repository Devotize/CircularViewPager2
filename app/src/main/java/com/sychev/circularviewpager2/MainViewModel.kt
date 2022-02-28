package com.sychev.circularviewpager2

import androidx.lifecycle.ViewModel
import com.sychev.circularviewpager2.data.SimpleDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class MainViewModel: ViewModel() {

    val data = SimpleDataProvider.data

    private val _currentPageIndex: MutableStateFlow<Int?> = MutableStateFlow(null)
    val currentPageIndex = _currentPageIndex.filterNotNull()

    fun onPageSelected(page: Int) {
        _currentPageIndex.value = page
    }

}