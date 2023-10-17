package com.example.chefgram.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chefgram.R
import com.example.chefgram.domain.model.FilterParams

class FilterAdapter(
    private val optionsList: List<CategoryItem>,
    private val filterOptionsList: List<FilterParams>,
    private val action: (params: String) -> Unit
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    inner class FilterViewHolder(itemView: View) : ViewHolder(itemView) {
        private val categoryName: TextView = itemView.findViewById(R.id.category_name_item)
        private val menuArrow: ImageView = itemView.findViewById(R.id.category_arrow)
        private val contentRecycler: RecyclerView = itemView.findViewById(R.id.content_recycler)

        fun bind(item: CategoryItem) {
            categoryName.text = item.name
            menuArrow.setOnClickListener {
                item.isExpandable = !item.isExpandable
                if (item.isExpandable) {
                    contentRecycler.visibility = View.VISIBLE
                    val nestedAdapter = CategoryContentAdapter(filterOptionsList,action)
                    contentRecycler.layoutManager = LinearLayoutManager(contentRecycler.context)
                    contentRecycler.adapter = nestedAdapter
                } else {
                    contentRecycler.visibility = View.GONE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_filter_item, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(optionsList[position])
    }

    override fun getItemCount(): Int = optionsList.size


}