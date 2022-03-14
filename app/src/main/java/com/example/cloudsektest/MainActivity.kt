package com.example.cloudsektest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cloudsektest.databinding.ActivityMainBinding
import com.example.cloudsektest.ui.MainFragment
import com.example.cloudsektest.ui.displayApps.DisplayAppsFragment
import com.example.cloudsektest.ui.displayAssets.DisplayAssetsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val fragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "CloudSEK Test"
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, fragment)
            .commit()
    }

    fun addGetAllAssetsFragment(domain: String) {
        val displayAssetsFragment = DisplayAssetsFragment.newInstance(domain)
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, displayAssetsFragment)
            .addToBackStack(displayAssetsFragment.javaClass.name)
            .commit()
    }

    fun addGetAllAppsFragment() {
        val displayAppsFragment = DisplayAppsFragment()
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, displayAppsFragment)
            .addToBackStack(displayAppsFragment.javaClass.name)
            .commit()
    }

}