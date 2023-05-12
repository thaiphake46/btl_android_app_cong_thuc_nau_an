package com.example.btl_app_android.mainFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class NavBarMainAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomePage()
            }
            1 -> {
                BookmarkPage()
            }
            2 -> {
                ShoppingPage()
            }
            else -> {
                InfoPage()
            }
        }
    }
}