package com.example.chefgram.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.chefgram.R
import com.example.chefgram.domain.model.FilterParams

class CategoryContentAdapter(
    private val filterOptionsList: List<FilterParams>,
    private val action: (params: String) -> Unit
) : RecyclerView.Adapter<CategoryContentAdapter.CategoryContentViewHolder>() {
    inner class CategoryContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contentOption: CheckBox = itemView.findViewById(R.id.filter_option_box)
        fun bind(name: String) {
            contentOption.text = name
            contentOption.setOnClickListener {
                action(name)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_item_list, parent, false)
        return CategoryContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryContentViewHolder, position: Int) {
        holder.bind(filterOptionsList[position].ingredient[0].name)
    }

    override fun getItemCount(): Int = filterOptionsList.size



}