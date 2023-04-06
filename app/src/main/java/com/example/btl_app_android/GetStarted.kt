package com.example.btl_app_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_started.*

class GetStarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        addClickEvents()
    }

    private fun addClickEvents() {
        handleBtnGetStarted()
    }

    private fun handleBtnGetStarted() {
        btnGetStarted.setOnClickListener {
            val gotoMainActivity = Intent(this, MainActivity::class.java)
            startActivity(gotoMainActivity)
        }
    }
}