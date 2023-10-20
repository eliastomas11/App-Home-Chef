package com.example.chefgram.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.chefgram.R
import com.example.chefgram.databinding.DetailScreenBinding
import com.example.chefgram.domain.model.Recipe
import com.example.chefgram.domain.model.RecipeIngredient
import com.example.chefgram.ui.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private var _binding: DetailScreenBinding? = null
    private val binding: DetailScreenBinding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailScreenBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        viewModel.recipeSelected.observe(viewLifecycleOwner) { recipe ->
            setScreenState(recipe)
        }

        viewModel.isSaved.observe(viewLifecycleOwner) { saved ->
            changeFavState(saved)
        }

        binding.detailFavButton.setOnClickListener {
            viewModel.saveRecipe()
        }


    }

    private fun setScreenState(recipe: Recipe) {
        loadImage(recipe.image)
        setTitle(recipe.title)
        setIngredients(recipe.ingredients)
        setDescription(recipe.description)
    }

    private fun setDescription(description: String) {
        binding.detailDescriptionTv.text =
            Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT).toString()
    }

    private fun loadImage(imageUrl: String) {
        Glide.with(this).load(imageUrl).into(binding.detailImageIv)
    }

    private fun setTitle(title: String) {
        binding.detailTitleTv.text = title
    }

    private fun setIngredients(ingredients: List<RecipeIngredient>) {
        binding.detailIngredientsTv.text = "Ingredients:\n"
        ingredients.forEach { ingredient ->
            binding.detailIngredientsTv.append(
                "${ingredient.name} : ${
                    ingredient.amount.toString().dropWhile { it.toString().length > 4 }
                } ${ingredient.unit}\n"
            )
        }
    }

    private fun changeFavState(saved: Boolean?) {
        if (saved == true) {
            binding.detailFavButton.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.detailFavButton.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}