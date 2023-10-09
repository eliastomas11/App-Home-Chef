package com.example.chefgram.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chefgram.R
import com.example.chefgram.databinding.ActivityMainBinding
import com.example.chefgram.ui.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.screen_fragment_container, homeFragment).commit()
    }

}

