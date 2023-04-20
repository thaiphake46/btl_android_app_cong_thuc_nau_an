package com.example.btl_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import kotlinx.android.synthetic.main.tag_item.view.*

class TagsAdapter(var context: Context,private val tags: List<String>) : RecyclerView.Adapter<TagsAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_tag = itemView.findViewById<TextView>(R.id.txt_tag)
        init {
            txt_tag = txt_tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tag_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.txt_tag.text = tags[position]
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}