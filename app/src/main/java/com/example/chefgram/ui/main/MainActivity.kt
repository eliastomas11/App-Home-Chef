package com.example.chefgram.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefgram.databinding.ActivityMainBinding
import com.example.chefgram.domain.model.FilterParams
import com.example.chefgram.domain.model.RecipeIngredient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<SharedViewModel>()
    private var visibilelist = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoyList = listOf<CategoryItem>(CategoryItem("ingredients"))
        val recipeIngredient = RecipeIngredient(12061,"almonds",5.0,"g","","","almond")
        val parametros = listOf<FilterParams>(FilterParams(listOf(recipeIngredient)))
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = FilterAdapter(categoyList,parametros) {
            val recipe = RecipeIngredient(12061,it,5.0,"g","","","almond")
            viewModel.onFilterClick(FilterParams(listOf(recipe)))
        }
        binding.dropdownArrow.setOnClickListener {
            visibilelist = !visibilelist
            if (visibilelist) {
                binding.categoryList.visibility = View.VISIBLE
            } else {
                binding.categoryList.visibility = View.GONE
            }
        }
    }

}

