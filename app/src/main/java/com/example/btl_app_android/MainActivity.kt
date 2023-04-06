package com.example.btl_app_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.btl_app_android.mainFragment.NavBarMainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = NavBarMainAdapter(supportFragmentManager, lifecycle)
        pagerMain.adapter = adapter

        TabLayoutMediator(navbarMain, pagerMain) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.home_24px)
                }
                1 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.bookmark_24px)
                }
                2 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.shopping_bag_24px)
                }
                3 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.person_24px)
                }
            }
        }.attach()
    }
}