package com.example.chefgram.ui.main

import android.app.ActionBar.LayoutParams
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefgram.R
import com.example.chefgram.databinding.ActivityMainBinding
import com.example.chefgram.ui.main.adapters.FilterAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<SharedViewModel>()
    private val navController by lazy { navHostFragment.navController }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.screen_fragment_container) as NavHostFragment }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.notificationPermissionGranted(isGranted)
            } else {
                Toast.makeText(this, "Permission Needed to show notifications", Toast.LENGTH_SHORT).show()
                viewModel.notificationPermissionGranted(isGranted)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        viewModel.loading.observe(this) {
            splashScreen.setKeepOnScreenCondition {
                it
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initSplash() {
        val splashScreen = installSplashScreen()
        viewModel.loading.observe(this) {
            splashScreen.setKeepOnScreenCondition {
                it
            }
        }
    }

    private fun initUI() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requestPermissions()
        }
        initNavigation()
        initFilterBar()
        initSearchBar()
        initObservers()
        binding.toolbar.navigationIcon?.setVisible(false, false)


    }

    private fun initObservers() {

    }

    private fun initSearchBar() {
        binding.searchBar.setOnCloseListener {
            binding.searchBar.layoutParams.width = LayoutParams.WRAP_CONTENT
            binding.appBarTitleLogo.visibility = View.VISIBLE
            binding.searchBar.isActivated = false
            false
        }

        binding.searchBar.setOnSearchClickListener { v ->
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
            binding.appBarTitleLogo.visibility = View.GONE
            v.layoutParams.width = LayoutParams.MATCH_PARENT
        }

    }

    private fun initFilterBar() {
        binding.filterList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.filterList.observe(this) {
            binding.filterList.adapter = FilterAdapter(it){ filterItem ->
                viewModel.onCategoryClick(filterItem)
            }
        }
    }


    private fun initNavigation() {
        binding.btmNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener() { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> navigateToHome()
                R.id.settingsFragment -> navigateToSettings()
                R.id.detailScreen -> navigateToDetail()
            }
        }
    }

    private fun navigateToDetail() {
        navigateToSettings()
    }

    private fun navigateToHome() {
        binding.toolbar.navigationIcon = null
        binding.searchBar.visibility = View.VISIBLE
        binding.filterBar.visibility = View.VISIBLE
        binding.appBtmBar.performShow()
    }

    private fun navigateToSettings() {
        binding.appBtmBar.performHide()
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.searchBar.visibility = View.GONE
        binding.toolbar.setNavigationOnClickListener {
            if (navController.currentBackStackEntry?.destination?.id != R.id.homeFragment) {
                navController.navigateUp()
            }
        }
        binding.filterBar.visibility = View.GONE
    }



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.notificationPermissionGranted(true)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                Snackbar.make(
                    this,
                    binding.root,
                    getString(R.string.notification_permission_rationale),
                    Snackbar.LENGTH_LONG
                ).show()
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }

            else -> requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)

        }
    }

}

