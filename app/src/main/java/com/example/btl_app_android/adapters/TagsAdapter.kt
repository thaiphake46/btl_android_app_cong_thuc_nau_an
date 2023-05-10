package com.example.btl_app_android.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R

class TagsAdapter(
    private val context: Context,
    private val tags: List<String>,
    val onCLickTags: RvTagsInterface
) :
    RecyclerView.Adapter<TagsAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_tag = itemView.findViewById<TextView>(R.id.txt_tag)
    }

    companion object {
        var selectedPosition: Int = 0;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, parent, false)
        return ItemViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.txt_tag.text = tags[position]

        holder.txt_tag.setOnClickListener {
            onCLickTags.onCLickTag(position)
            selectedPosition = position
        }

        if (position == selectedPosition) {
            holder.txt_tag.background =
                ContextCompat.getDrawable(context, R.drawable.button_tag_enb)
            holder.txt_tag.setTextColor(Color.WHITE)
        } else {
            holder.txt_tag.background =
                ContextCompat.getDrawable(context, R.drawable.button_tag_dis)
            holder.txt_tag.setTextColor(ContextCompat.getColor(context, R.color.textcl))
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}