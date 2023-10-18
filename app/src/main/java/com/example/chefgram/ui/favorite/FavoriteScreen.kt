package com.example.chefgram.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chefgram.R
import com.example.chefgram.databinding.FragmentFavoriteScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint 
class FavoriteScreen : Fragment() {
    private var _binding: FragmentFavoriteScreenBinding? = null
    private val binding: FragmentFavoriteScreenBinding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private val navController by lazy { findNavController() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteScreenBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initObservers() {
        viewModel.favoriteRecipeList.observe(viewLifecycleOwner){
            binding.favoriteRecipesRecyclerView.adapter = FavoriteAdapter(it){recipeId ->
                viewModel.onRecipeClick(recipeId)
                navController.navigate(R.id.action_favoriteScreen_to_detailScreen)
            }

        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.favoriteLoadingProgressBar.visibility = if(it) View.VISIBLE else View.GONE
        }
    }

    private fun initUI() {

    }

    private fun initListeners(){
        binding.createRecipeFb.setOnClickListener {
            //Navigate to CreateRecipe Flow
        }

    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}