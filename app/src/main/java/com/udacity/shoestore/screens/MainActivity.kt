package com.udacity.shoestore.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initNavController()
        initAppBarWithNavController()

        initSharedViewModel()
        initObservers()

        Timber.plant(Timber.DebugTree())
    }

    override fun onBackPressed() {
        return if (navController.currentDestination?.id == R.id.shoeDetailFragment) {
            sharedViewModel.onCancelAddShoeStart()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navController.currentDestination?.id  == R.id.shoeDetailFragment) {
            sharedViewModel.onCancelAddShoeStart()
            true
        } else {
            navController.popBackStack()
        }
    }

    private fun initNavController() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

    }

    private fun initAppBarWithNavController() {

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.loginFragment, R.id.welcomeFragment, R.id.shoeListFragment))

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    private fun initSharedViewModel() {

        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

    }

    private fun initObservers() {

        sharedViewModel.eventCancelAddShoe.observe(this){ event ->
            if (event) {
                navController.popBackStack()
                sharedViewModel.onCancelAddShoeComplete()
            }
        }

    }
}
