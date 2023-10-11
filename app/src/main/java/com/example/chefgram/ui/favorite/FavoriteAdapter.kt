package com.example.chefgram.ui.favorite

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.chefgram.databinding.FavoriteItemBinding
import com.example.chefgram.databinding.MealsRecyclerItemBinding
import com.example.chefgram.domain.model.Recipe

class FavoriteAdapter(private val favoriteRecipesList: List<Recipe>,private val action: (Int) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    private var mealsList: List<Recipe> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemBinding.inflate(layoutInflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount() = mealsList.size


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(
            mealsList[position].title,
            mealsList[position].description,
            mealsList[position].image,
            mealsList[position].id
        )

    }

    fun setData(recipeList: List<Recipe>?) {
        mealsList = recipeList ?: emptyList()
    }

    inner class FavoriteViewHolder(private val favoriteItem: FavoriteItemBinding) :
        RecyclerView.ViewHolder(favoriteItem.root) {

        fun bind(name: String, description: String, imageUrl: String,id: Int) {
            favoriteItem.recipeFavoriteTitleTv.text = name
            favoriteItem.recipeDescriptionTv.text = description
            Glide.with(favoriteItem.root.context)
                .load(imageUrl)
                .error(favoriteItem.recipeImageIv.drawable)
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .encodeQuality(80)
                .into(favoriteItem.recipeImageIv)
            favoriteItem.root.setOnClickListener { action(id) }
        }
    }

}
