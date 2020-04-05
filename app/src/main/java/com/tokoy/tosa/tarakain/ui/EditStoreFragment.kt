package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentEditStoreBinding
import com.tokoy.tosa.tarakain.db.dao.TKConverter
import com.tokoy.tosa.tarakain.di.Injectable
import com.tokoy.tosa.tarakain.utils.EventObserver
import com.tokoy.tosa.tarakain.utils.hideKeyboard
import com.tokoy.tosa.tarakain.utils.showSnackbar
import com.tokoy.tosa.tarakain.viewmodels.EditStoreViewModel
import javax.inject.Inject

class EditStoreFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentEditStoreBinding
    private val args: EditStoreFragmentArgs by navArgs()

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: EditStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_store,
            container,
            false
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EditStoreViewModel::class.java)

        TKConverter.stringToStore(args.store)?.let {
            viewModel.store = it
        } ?: findNavController().popBackStack()

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
            val index = viewModel.store.category?.id ?: 1
            viewModel.categoryIndex.set(index - 1)
            binding.viewModel = viewModel
        })

        viewModel.isStoreUpdated.observe(viewLifecycleOwner, EventObserver { storeUpdated ->
            if (storeUpdated) {
                hideKeyboard()
                showSnackbar(getString(R.string.store_saved_successful))
                findNavController().popBackStack()
            }
        })

        viewModel.isStoreDeleted.observe(viewLifecycleOwner, EventObserver { storeDeleted ->
            if (storeDeleted) {
                showSnackbar(getString(R.string.store_deleted_successful))
                findNavController().popBackStack()
            }
        })
    }
}