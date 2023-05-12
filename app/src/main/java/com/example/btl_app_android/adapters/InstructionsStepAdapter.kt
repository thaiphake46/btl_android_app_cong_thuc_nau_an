package com.example.btl_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import com.example.btl_app_android.models.Step

class InstructionsStepAdapter(var context: Context, var list: List<Step>) :
    RecyclerView.Adapter<InstructionsStepAdapter.InstructionsStepViewHolder>() {
    inner class InstructionsStepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView_steps_number: TextView = itemView.findViewById(R.id.textView_steps_number)
        val textView_steps_title: TextView = itemView.findViewById(R.id.textView_steps_title)
        val recycler_steps_equiments: RecyclerView =
            itemView.findViewById(R.id.recycler_steps_equiments)
        val recycler_steps_ingredients: RecyclerView =
            itemView.findViewById(R.id.recycler_steps_ingredients)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsStepViewHolder {
        return InstructionsStepViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_instructions_steps, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InstructionsStepViewHolder, position: Int) {
        holder.textView_steps_number.text = list[position].number.toString()
        holder.textView_steps_title.text = list[position].step

        holder.recycler_steps_ingredients.setHasFixedSize(true)
        holder.recycler_steps_ingredients.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val instructionsIngredientsAdapter = InstructionsIngredientsAdapter(
            context,
            list[position].ingredients!!
        )
        holder.recycler_steps_ingredients.adapter = instructionsIngredientsAdapter

        holder.recycler_steps_equiments.setHasFixedSize(true)
        holder.recycler_steps_equiments.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val instructionsEquimentsAdapter = InstructionsEquimentsAdapter(
            context,
            list[position].equipment!!
        )
        holder.recycler_steps_equiments.adapter = instructionsEquimentsAdapter
    }

    override fun getItemCount(): Int {
        return list.size
    }
}