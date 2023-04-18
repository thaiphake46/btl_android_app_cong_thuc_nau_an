package com.example.btl_app_android

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.adapters.RandomRecipeAdapter
import com.example.btl_app_android.dialogs.ProgressDialog
import com.example.btl_app_android.mainFragment.NavBarMainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.btl_app_android.listeners.RandomRecipeResponseListener
import com.example.btl_app_android.models.RandomRecipeApiResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dialog: AlertDialog
    private lateinit var progressBar: ProgressBar

    private lateinit var manager: RequestManager
    lateinit var randomRecipeAdapter: RandomRecipeAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var builderDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navBar()

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

    private val randomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didFetch(response: RandomRecipeApiResponse?, message: String?) {
            recyclerView = findViewById(R.id.recycler_random)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            randomRecipeAdapter = RandomRecipeAdapter(this@MainActivity, response!!.recipes!!)
            recyclerView.adapter = randomRecipeAdapter
            builderDialog.dismiss()
        }

        override fun didError(message: String?) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
