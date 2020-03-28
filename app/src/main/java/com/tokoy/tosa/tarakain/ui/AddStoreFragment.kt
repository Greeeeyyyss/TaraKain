package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentAddStoreBinding
import com.tokoy.tosa.tarakain.utils.EventObserver
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.utils.hideKeyboard
import com.tokoy.tosa.tarakain.utils.showSnackbar
import com.tokoy.tosa.tarakain.viewmodels.AddStoreViewModel

class AddStoreFragment : Fragment() {
    private lateinit var binding: FragmentAddStoreBinding
    private val viewModel: AddStoreViewModel by viewModels {
        InjectorUtils.provideAddStoreViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_store,
            container,
            false
        )
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.getCategories().observe(viewLifecycleOwner, Observer { categories ->
            viewModel.categoryList = categories
            viewModel.categoryNames = categories.map { it.name }
            binding.viewModel = viewModel
        })

        viewModel.storeAdded.observe(viewLifecycleOwner, EventObserver { storeAdded ->
            if (storeAdded) {
                hideKeyboard()
                showSnackbar(getString(R.string.store_saved_successful))
                findNavController().navigate(R.id.storeListFragment)
            }
        })
    }
}