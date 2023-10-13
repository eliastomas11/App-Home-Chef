package com.example.chefgram.ui.createrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.chefgram.R
import com.example.chefgram.databinding.FragmentCreateRecipeScreenBinding
import com.example.chefgram.databinding.FragmentFavoriteScreenBinding
import com.example.chefgram.ui.favorite.FavoriteAdapter
import com.example.chefgram.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateRecipeScreen : Fragment() {

    private var _binding: FragmentCreateRecipeScreenBinding? = null
    private val binding: FragmentCreateRecipeScreenBinding get() = _binding!!

    private val viewModel: CreateRecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateRecipeScreenBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initObservers() {
        viewModel.errorEmptyFields.observe(viewLifecycleOwner){ isError ->
            //Cada texto en vacio deberia mostrar el error
            //
        }
    }

    private fun initUI() {

    }

    private fun initListeners(){
        binding.createRecipeBtn.setOnClickListener {
            //viewModel.saveRecipe()
            //initObservers()
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}