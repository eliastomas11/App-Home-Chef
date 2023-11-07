package com.example.chefgram.ui.main.adapters

import com.example.chefgram.data.repository.reciperepo.category.Categories

data class FilterItem(val category: Categories, var checked: Boolean = false)
