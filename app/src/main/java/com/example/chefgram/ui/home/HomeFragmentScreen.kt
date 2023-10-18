package com.example.chefgram.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefgram.R
import com.example.chefgram.databinding.HomeFragmentBinding
import com.example.chefgram.ui.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragmentScreen : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding
    private val navController by lazy { findNavController() }

    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter = RecipeAdapter() { selectedRecipeId ->
        viewModel.onRecipeClick(selectedRecipeId)
        navController.navigate(R.id.action_homeFragment_to_detailScreen)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = HomeFragmentBinding.bind(view)
        initUI()

    }

    private fun initUI() {
        initRecycler()
        initObservers()
        initListeners()
    }



    private fun initRecycler() {
        binding.recipeHomeRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recipeHomeRecyclerView.adapter = adapter

    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingHomeProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            Log.i("ERROR MESSAGE", errorMessage)
        }

        viewModel.mealsList.observe(viewLifecycleOwner) { recipeList ->
            adapter.setData(recipeList)
            adapter.notifyDataSetChanged()

        }

    }

    private fun initListeners() {

    }

}