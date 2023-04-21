package com.example.btl_app_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import kotlinx.android.synthetic.main.tag_item.view.*

class TagsAdapter(private val tags: List<String>, val onCLickTags: RvTagsInterface) :
    RecyclerView.Adapter<TagsAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            txt_tag.text = tags[position]

            // click
            holder.itemView.setOnClickListener{
                onCLickTags.onCLickTag(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}