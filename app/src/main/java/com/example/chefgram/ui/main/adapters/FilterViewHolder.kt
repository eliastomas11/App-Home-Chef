package com.example.chefgram.ui.main.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chefgram.databinding.FilterItemBinding

class FilterViewHolder(private val binding: FilterItemBinding) : ViewHolder(binding.root) {

    fun bind(filterItem: FilterItem, action: (FilterItem) -> Unit) {
        binding.filterChip.text = filterItem.category.name
        binding.filterChip.setOnCheckedChangeListener { _, isChecked ->
            filterItem.checked = isChecked
            action(filterItem)
        }
    }

}
