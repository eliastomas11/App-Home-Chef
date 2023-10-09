package com.example.chefgram.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefgram.R
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.data.repository.MealsRepositoryImpl
import com.example.chefgram.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding
    private val navController by lazy { findNavController() }
    private val adapter: MealsAdapter = MealsAdapter() {
        navController.navigate(R.id.action_homeFragment_to_detailScreen)
    }
    private val viewModel by viewModels<HomeViewModel>()

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
        binding.mealsRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        binding.mealsRecyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewModel.mealsList.observe(viewLifecycleOwner) { mealList ->
            adapter.setData(mealList)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initListeners() {

    }

}