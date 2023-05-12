package com.example.btl_app_android

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.adapters.RandomRecipeAdapter
import com.example.btl_app_android.adapters.RvTagsInterface
import com.example.btl_app_android.adapters.TagsAdapter
import com.example.btl_app_android.dialogs.ProgressDialog
import com.example.btl_app_android.mainFragment.NavBarMainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.btl_app_android.listeners.RandomRecipeResponseListener
import com.example.btl_app_android.listeners.RecipeClickListener
import com.example.btl_app_android.models.RandomRecipeApiResponse
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var manager: RequestManager
    lateinit var randomRecipeAdapter: RandomRecipeAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var builderDialog: ProgressDialog
    var tagTypeGetRecipe: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navBar()
        renderListRecipe(tagTypeGetRecipe)
    }

    private fun tagsRecipe() {
        val tagsType = resources.getStringArray(R.array.tags).toList()
        val recycler_tags: RecyclerView = findViewById(R.id.recycler_tags)
        recycler_tags.setHasFixedSize(true)
        recycler_tags.layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val adapter = TagsAdapter(this@MainActivity, tagsType, object : RvTagsInterface {
            override fun onCLickTag(pos: Int) {
                tagTypeGetRecipe = tagsType[pos]
                if (pos == 0) {
                    tagTypeGetRecipe = ""
                }
                renderListRecipe(tagTypeGetRecipe)
            }
        })
        recycler_tags.adapter = adapter
    }

    private fun renderListRecipe(tagType: String) {
        builderDialog = ProgressDialog(this, "Loading ...")
        builderDialog.show()
        manager = RequestManager(this)
        manager.getRandomRecipe(randomRecipeResponseListener, tagType)
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
                            R.color.black
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                1 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.bookmark_24px)
                    tab.icon?.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.textcl
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                2 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.shopping_bag_24px)
                    tab.icon?.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.textcl
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                3 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.person_24px)
                    tab.icon?.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.textcl
                        ), PorterDuff.Mode.SRC_IN
                    )
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
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.textcl
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    1 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.textcl
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    2 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.textcl
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                    3 -> {
                        tab.icon?.setColorFilter(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.textcl
                            ), PorterDuff.Mode.SRC_IN
                        )
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private val randomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didFetch(response: RandomRecipeApiResponse?, message: String?) {
            tagsRecipe()
            recyclerView = findViewById(R.id.recycler_random)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            randomRecipeAdapter =
                RandomRecipeAdapter(this@MainActivity, response!!.recipes!!, recipeClickListener)
            recyclerView.adapter = randomRecipeAdapter
            builderDialog.dismiss()
        }

        override fun didError(message: String?) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    private val recipeClickListener = object : RecipeClickListener {
        override fun onRecipeClicked(id: String) {
            startActivity(
                Intent(this@MainActivity, RecipeDetailActivity::class.java)
                    .putExtra("id", id)
            )
        }
    }
}
