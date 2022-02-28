package com.sychev.circularviewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sychev.circularviewpager2.data.SimpleData

class MainPageFragment(
    private val data: SimpleData
) : Fragment() {

    private lateinit var pageTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        with(view) {
            pageTextView = findViewById(R.id.page_text_view)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        pageTextView.text = data.text
    }

    companion object{
        fun createInstance(data: SimpleData) = MainPageFragment(data)
    }

}