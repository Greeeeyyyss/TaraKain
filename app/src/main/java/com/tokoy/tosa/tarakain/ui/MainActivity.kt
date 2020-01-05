package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
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
                binding.toolbarLeftButton.setImageResource(R.drawable.ic_menu)
                binding.toolbarLeftButton.setOnClickListener {
                    openDrawer()
                }
                binding.toolbarRightButton.visibility = View.VISIBLE
                binding.toolbarRightButton.setOnClickListener {
                    navController().navigate(R.id.action_storeOfTheDay_to_addStore)
                }
            }
            R.id.addStoreFragment -> {
                binding.toolbarTitle.text = getString(R.string.add_store)
                binding.toolbarLeftButton.setImageResource(R.drawable.ic_back)
                binding.toolbarLeftButton.setOnClickListener {
                    navController().popBackStack()
                }
                binding.toolbarRightButton.visibility = View.GONE
            }
            R.id.chooseCategoryFragment -> {
                binding.toolbarTitle.text = getString(R.string.choose_category)
                binding.toolbarLeftButton.setImageResource(R.drawable.ic_back)
                binding.toolbarLeftButton.setOnClickListener {
                    navController().popBackStack()
                }
                binding.toolbarRightButton.visibility = View.VISIBLE
                binding.toolbarRightButton.setOnClickListener {
                    // TODO add category screen
                }
            }

        }
    }

    fun getCategories(): List<Category> {
        return listOf(
            Category(1, "Filipino"),
            Category(2, "American"),
            Category(3, "Japanese"),
            Category(4, "Korean"),
            Category(5, "Chinese"),
            Category(6, "Italian"),
            Category(7, "Spanish"),
            Category(8, "Mexican"),
            Category(9, "Thai"),
            Category(10, "Cafe"),
            Category(11, "Fast Food"),
            Category(12, "Buffet"),
            Category(13, "Pub"),
            Category(14, "Fine Dining")
        )
    }

    fun getStores(): List<Store> {
        return listOf()
    }

    private fun navController(): NavController {
        return Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    private fun closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.END)
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
}
