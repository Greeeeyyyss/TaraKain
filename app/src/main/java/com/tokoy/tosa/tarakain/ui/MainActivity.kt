package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.ActivityMainBinding
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbarLeftButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        val navController = NavHostFragment.findNavController(nav_host_fragment)
        binding.navigationView.setupWithNavController(navController)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_store_of_the_day -> {
                    binding.toolbarTitle.text = getString(R.string.store_of_the_day)
                }
                R.id.nav_favorite_stores -> {
                    binding.toolbarTitle.text = getString(R.string.favorites_stores)
                }
                R.id.nav_featured_stores -> {
                    binding.toolbarTitle.text = getString(R.string.featured_stores)
                }
                R.id.nav_promos -> {
                    binding.toolbarTitle.text = getString(R.string.promos)
                }
                R.id.nav_heads_or_tails -> {
                    binding.toolbarTitle.text = getString(R.string.heads_or_tails)
                }
            }
            menuItem.isChecked = true
            binding.drawerLayout.closeDrawers()
            true
        }
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id != R.id.splashFragment) {
            binding.toolbar.visibility = View.VISIBLE
        }

        when (destination.id) {
            R.id.storeOfTheDayFragment -> {
                binding.toolbarTitle.text = getString(R.string.store_of_the_day)
            }
        }
    }

    fun getCategories(): List<Category> {
        return listOf()
    }

    fun getStores(): List<Store> {
        return listOf()
    }
}
