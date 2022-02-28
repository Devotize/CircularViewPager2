package com.sychev.circularviewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val TAG = "AppDebug"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainFragment.newInstance())
            .commit()

    }
}