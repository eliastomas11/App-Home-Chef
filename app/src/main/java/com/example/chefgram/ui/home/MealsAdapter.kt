package com.example.chefgram.ui.home

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.BitmapCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chefgram.databinding.MealsRecyclerItemBinding
import com.example.chefgram.ui.model.Meal

class MealsAdapter(private val action: () -> Unit) :
    RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

    private var mealsList: List<Meal> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MealsRecyclerItemBinding.inflate(layoutInflater, parent, false)
        return MealsViewHolder(binding)
    }

    override fun getItemCount() = mealsList.size


    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bind(
            mealsList[position].title,
            mealsList[position].description,
            mealsList[position].image
        )

    }

    fun setData(mealList: List<Meal>?) {
        mealsList = mealList ?: emptyList()
    }

    inner class MealsViewHolder(private val mealsItem: MealsRecyclerItemBinding) :
        RecyclerView.ViewHolder(mealsItem.root) {

        fun bind(name: String, description: String, imageUrl: String) {
            mealsItem.mealTitleTv.text = name
            mealsItem.mealDescriptionTv.text = description
            Glide.with(mealsItem.root.context.applicationContext)
                .load(imageUrl)
                .error(mealsItem.imageView.drawable)
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .encodeQuality(80)
                .into(mealsItem.imageView)
            mealsItem.root.setOnClickListener { action() }
        }
    }

}
