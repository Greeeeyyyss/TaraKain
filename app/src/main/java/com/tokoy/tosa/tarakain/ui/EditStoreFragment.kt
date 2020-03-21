package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentEditStoreBinding
import com.tokoy.tosa.tarakain.db.dao.TKConverter
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.utils.hideKeyboard
import com.tokoy.tosa.tarakain.viewmodels.CategoryViewModel
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModel

class EditStoreFragment : Fragment() {
    private lateinit var binding: FragmentEditStoreBinding
    private val args: EditStoreFragmentArgs by navArgs()
    private var categoryList: List<Category> = mutableListOf()
    private val viewModel: StoreViewModel by viewModels {
        InjectorUtils.provideStoreViewModelFactory(requireContext())
    }

    private val categoryViewModel: CategoryViewModel by viewModels {
        InjectorUtils.provideCategoryViewModelFactory(requireContext())
    }

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
        val store = TKConverter.stringToStore(args.store)
        binding.store = store
        binding.btnSave.setOnClickListener {
            onSaveClick()
        }

        loadCategories()
        return binding.root
    }

    private fun loadCategories() {
        categoryViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categoryList = categories
            categoryViewModel.categoryNames = categories.map { it.name }
            val index = binding.store?.category?.id ?: 1
            categoryViewModel.categoryIndex.set(index - 1)
            binding.viewModel = categoryViewModel
        })
    }

    private fun onSaveClick() {
        hideKeyboard()

        binding.store?.let { store ->
            val index = categoryViewModel.categoryIndex.get() ?: 0
            store.category = categoryList[index]
            viewModel.addStore(store)
            Toast.makeText(
                context,
                getString(R.string.store_saved_successful),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
    }
}