package com.example.btl_app_android

<<<<<<< HEAD
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
=======
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.adapters.RandomRecipeAdapter
<<<<<<< HEAD
=======
import com.example.btl_app_android.adapters.TagsAdapter
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
import com.example.btl_app_android.dialogs.ProgressDialog
import com.example.btl_app_android.mainFragment.NavBarMainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.btl_app_android.listeners.RandomRecipeResponseListener
import com.example.btl_app_android.models.RandomRecipeApiResponse
<<<<<<< HEAD
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dialog: AlertDialog
    private lateinit var progressBar: ProgressBar

    private lateinit var manager: RequestManager
    lateinit var randomRecipeAdapter: RandomRecipeAdapter
    lateinit var recyclerView: RecyclerView
=======
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var manager: RequestManager
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
    lateinit var builderDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD

        navBar()
        renderListRecipe()

    }

    private fun renderListRecipe() {
=======
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
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
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
<<<<<<< HEAD
=======
                    tab.icon?.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.purple_500
                        ), PorterDuff.Mode.SRC_IN
                    )
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
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
<<<<<<< HEAD
=======

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
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
    }

    private val randomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didFetch(response: RandomRecipeApiResponse?, message: String?) {
<<<<<<< HEAD
            recyclerView = findViewById(R.id.recycler_random)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            randomRecipeAdapter = RandomRecipeAdapter(this@MainActivity, response!!.recipes!!)
=======
            tagsRecipe()
            val recyclerView: RecyclerView = findViewById(R.id.recycler_random)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            val randomRecipeAdapter: RandomRecipeAdapter =
                RandomRecipeAdapter(this@MainActivity, response!!.recipes!!)
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
            recyclerView.adapter = randomRecipeAdapter
            builderDialog.dismiss()
        }

        override fun didError(message: String?) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
