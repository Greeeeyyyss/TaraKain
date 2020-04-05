package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentChooseCategoryBinding
import com.tokoy.tosa.tarakain.di.Injectable
import com.tokoy.tosa.tarakain.viewmodels.CategoryViewModel
import javax.inject.Inject

class ChooseCategoryFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentChooseCategoryBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_choose_category,
            container,
            false
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CategoryViewModel::class.java)
        displayCategories()
        return binding.root
    }

    private fun displayCategories() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            Log.v("TOSA", "Categories ${categories.count()}")
        })
    }
}