package com.example.btl_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import com.example.btl_app_android.models.InstructionsRecipeResponse

class InstructionsAdapter(var context: Context, var list: List<InstructionsRecipeResponse>) :
    RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder>() {

    inner class InstructionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView_instructions_name: TextView =
            itemView.findViewById(R.id.textView_instructions_name)
        val recycler_instruction_steps: RecyclerView =
            itemView.findViewById(R.id.recycler_instruction_steps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        return InstructionsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        holder.textView_instructions_name.text = list[position].name
        holder.recycler_instruction_steps.setHasFixedSize(true)
        holder.recycler_instruction_steps.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var instructionsStepAdapter = InstructionsStepAdapter(context, list[position].steps)
        holder.recycler_instruction_steps.adapter = instructionsStepAdapter
    }

    override fun getItemCount(): Int {
        return list.size
    }
}