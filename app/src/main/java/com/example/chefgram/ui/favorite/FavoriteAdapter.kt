package com.example.chefgram.ui.favorite

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chefgram.databinding.FavoriteItemBinding
import com.example.chefgram.domain.model.Recipe

class FavoriteAdapter(private val action: (Int) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var favoriteRecipesList: List<Recipe> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemBinding.inflate(layoutInflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteRecipesList.size
    }


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(
            favoriteRecipesList[position].title,
            favoriteRecipesList[position].description,
            favoriteRecipesList[position].image,
            favoriteRecipesList[position].id
        )

    }

    fun setData(recipeList: List<Recipe>?) {

        favoriteRecipesList = recipeList ?: emptyList()

    }

    inner class FavoriteViewHolder(private val favoriteItem: FavoriteItemBinding) :
        RecyclerView.ViewHolder(favoriteItem.root) {

        fun bind(name: String, description: String, imageUrl: String, id: Int) {

            favoriteItem.recipeFavoriteTitleTv.text = name
            favoriteItem.recipeDescriptionTv.text = description
            Glide.with(favoriteItem.root.context)
                .load(imageUrl)
                .error(favoriteItem.recipeImageIv.drawable)
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .fitCenter()
                .encodeQuality(80)
                .into(favoriteItem.recipeImageIv)
            favoriteItem.root.setOnClickListener { action(id) }
        }
    }

}
