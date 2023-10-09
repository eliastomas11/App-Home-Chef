package com.example.chefgram.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefgram.R
import com.example.chefgram.data.repository.MealsRepositoryImpl
import com.example.chefgram.data.repository.local.FakeMealCacheDao
import com.example.chefgram.data.repository.local.FakeMealDao
import com.example.chefgram.data.repository.local.LocalDataSource
import com.example.chefgram.data.repository.remote.RemoteDataSource
import com.example.chefgram.databinding.HomeFragmentBinding
import kotlinx.coroutines.Dispatchers

class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding
    private val adapter: MealsAdapter = MealsAdapter(){
        Toast.makeText(requireActivity(), "Clicked", Toast.LENGTH_SHORT).show()
    }
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            MealsRepositoryImpl(
                Dispatchers.IO,
                LocalDataSource(FakeMealDao(), FakeMealCacheDao()),
                RemoteDataSource()
            )
        )
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