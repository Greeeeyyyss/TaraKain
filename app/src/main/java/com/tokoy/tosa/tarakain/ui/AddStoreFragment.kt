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
import androidx.navigation.fragment.navArgs
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentAddStoreBinding
import com.tokoy.tosa.tarakain.utils.*
import com.tokoy.tosa.tarakain.viewmodels.AddStoreViewModel

class AddStoreFragment : Fragment() {
    private lateinit var binding: FragmentAddStoreBinding
    private val viewModel: AddStoreViewModel by viewModels {
        InjectorUtils.provideAddStoreViewModelFactory(requireContext())
    }
    private val args: AddStoreFragmentArgs by navArgs()

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
        viewModel.isFavorite.set(args.isFavorites)

        setupObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
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
                findNavController().navigate(R.id.action_addStore_to_storeList)
            }
        })
    }
}