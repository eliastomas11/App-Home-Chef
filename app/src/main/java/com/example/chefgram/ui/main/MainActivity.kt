package com.example.chefgram.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chefgram.R
import com.example.chefgram.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<SharedViewModel>()
    private val navController by lazy { navHostFragment.navController }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.screen_fragment_container) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }


    private fun initUI() {
        initNavigation()
        binding.toolbar.navigationIcon?.setVisible(false,false)
        binding.searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.filter(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newText.let {
                    viewModel.filter(newText)
                }
                return true
            }

        })
    }

    private fun initNavigation() {
        binding.btmNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener() { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment -> navigateToHome()
                R.id.settingsFragment -> navigateToSettings()
            }
        }
    }

    private fun navigateToHome() {
        var navigationVisibility = binding.toolbar.navigationIcon?.isVisible
        navigationVisibility = false
        //binding.toolbar.navigationIcon = null
        binding.searchBar.visibility = View.VISIBLE
        binding.appBtmBar.performShow()
    }

    private fun navigateToSettings() {
        binding.appBtmBar.performHide()
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.searchBar.visibility = View.GONE
        binding.toolbar.setNavigationOnClickListener {
            if(navController.currentBackStackEntry?.destination?.id != R.id.homeFragment){
                navController.popBackStack()
            }
        }
    }
}

