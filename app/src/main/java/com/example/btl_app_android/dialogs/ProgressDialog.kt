package com.example.btl_app_android.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.example.btl_app_android.R

class ProgressDialog(private val context: Context, private val title: String) {

    private lateinit var dialog: AlertDialog
    private lateinit var progressBar: ProgressBar

    init {
        createDialog()
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
        progressBar = view.findViewById(R.id.progressBar)
        builder.setView(view)
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}
