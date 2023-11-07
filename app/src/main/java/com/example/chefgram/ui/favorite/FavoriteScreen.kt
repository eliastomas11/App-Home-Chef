package com.example.chefgram.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefgram.R
import com.example.chefgram.databinding.FragmentFavoriteScreenBinding
import com.example.chefgram.ui.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteScreen : Fragment(R.layout.fragment_favorite_screen) {
    private lateinit var binding: FragmentFavoriteScreenBinding

    private val viewModel by activityViewModels<SharedViewModel>()
    private val navController by lazy { findNavController() }
    private val favoriteAdapter = FavoriteAdapter() { recipeId ->
        viewModel.onRecipeClick(recipeId)
        navController.navigate(R.id.action_favoriteScreen_to_detailScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFavoriteScreenBinding.bind(view)
        initUI()
        initObservers()
    }


    private fun initObservers() {
        viewModel.favoriteInit()
        viewModel.recipeList.observe(viewLifecycleOwner) {
            favoriteAdapter.setData(it)
            favoriteAdapter.notifyDataSetChanged()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.favoriteLoadingProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.refreshing.observe(viewLifecycleOwner) {
            binding.favoriteSwipeRefresh.isRefreshing = it
        }

    }

    private fun initUI() {
        binding.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteRecipesRecyclerView.adapter = favoriteAdapter
        binding.favoriteSwipeRefresh.setOnRefreshListener {
            viewModel.favoriteInit()
        }
    }

}