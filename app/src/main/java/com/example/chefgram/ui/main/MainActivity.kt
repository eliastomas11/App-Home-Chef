package com.example.chefgram.ui.main

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chefgram.R
import com.example.chefgram.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<SharedViewModel>()
    private var visibilelist = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        val searchBar = binding.searchBar
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.filter(it)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrBlank()){
                    viewModel.getOriginalList()
                }else{
                    viewModel.filter(newText)
                }
                return true
            }
        })
    }


    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.screen_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.btmNavigation.setupWithNavController(navController)
    }


}

