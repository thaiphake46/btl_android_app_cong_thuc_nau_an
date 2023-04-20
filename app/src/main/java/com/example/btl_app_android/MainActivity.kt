package com.example.btl_app_android

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.adapters.RandomRecipeAdapter
import com.example.btl_app_android.adapters.TagsAdapter
import com.example.btl_app_android.dialogs.ProgressDialog
import com.example.btl_app_android.mainFragment.NavBarMainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.btl_app_android.listeners.RandomRecipeResponseListener
import com.example.btl_app_android.models.RandomRecipeApiResponse
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var manager: RequestManager
    lateinit var builderDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navBar()
        dialogListRecipe()
    }

    private fun tagsRecipe() {
        val tagsType = resources.getStringArray(R.array.tags).toList()
        val a: RecyclerView = findViewById(R.id.recycler_tags)
        a.setHasFixedSize(true)
        a.layoutManager = GridLayoutManager(this@MainActivity, 2)
        val adapter = TagsAdapter(this@MainActivity, tagsType)
        a.adapter = adapter
    }

    private fun dialogListRecipe() {
        builderDialog = ProgressDialog(this, "Loading ...")
        builderDialog.show()
        manager = RequestManager(this)
        manager.getRandomRecipe(randomRecipeResponseListener)
    }

    private fun navBar() {
        val adapter = NavBarMainAdapter(supportFragmentManager, lifecycle)
        pagerMain.adapter = adapter
        TabLayoutMediator(navbarMain, pagerMain) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.home_24px)
                    tab.icon?.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.purple_500
                        ), PorterDuff.Mode.SRC_IN
                    )
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

        navbarMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.purple_500
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    1 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.purple_500
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    2 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.purple_500
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    3 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.purple_500
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    // Add more cases for other tabs if necessary
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.black
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    1 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.black
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    2 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.black
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    3 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.black
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    // Add more cases for other tabs if necessary
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing
            }
        })
    }

    private val randomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didFetch(response: RandomRecipeApiResponse?, message: String?) {
            tagsRecipe()
            val recyclerView: RecyclerView = findViewById(R.id.recycler_random)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            val randomRecipeAdapter: RandomRecipeAdapter =
                RandomRecipeAdapter(this@MainActivity, response!!.recipes!!)
            recyclerView.adapter = randomRecipeAdapter
            builderDialog.dismiss()
        }

        override fun didError(message: String?) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
