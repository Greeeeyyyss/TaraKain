package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentChooseCategoryBinding
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.viewmodels.CategoryViewModel

class ChooseCategoryFragment : Fragment() {
    private lateinit var binding: FragmentChooseCategoryBinding

    private val viewModel: CategoryViewModel by viewModels {
        InjectorUtils.provideCategoryViewModelFactory(requireContext())
    }

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
        displayCategories()
        return binding.root
    }

    private fun displayCategories() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            Log.v("TOSA", "Categories ${categories.count()}")
        })
    }
}