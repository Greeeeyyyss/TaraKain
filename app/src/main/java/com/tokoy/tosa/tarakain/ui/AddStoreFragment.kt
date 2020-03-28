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
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentAddStoreBinding
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.utils.hideKeyboard
import com.tokoy.tosa.tarakain.viewmodels.AddStoreViewModel

class AddStoreFragment : Fragment() {
    private lateinit var binding: FragmentAddStoreBinding
    private var categoryList: List<Category> = mutableListOf()
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
        binding.btnAddStore.setOnClickListener {
            onAddStoreClick()
        }
        loadCategories()
        return binding.root
    }

    private fun loadCategories() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categoryList = categories
            viewModel.categoryNames = categories.map { it.name }
            binding.viewModel = viewModel
        })
    }

    private fun onAddStoreClick() {
        val name = binding.storeName.text?.toString() ?: return
        val store = Store(name = name, isFavorite = binding.addToFavorites.isChecked)
        val index = viewModel.categoryIndex.get() ?: 0
        store.category = categoryList[index]

        viewModel.addStore(store)

        Toast.makeText(
            context,
            getString(R.string.store_saved_successful),
            Toast.LENGTH_SHORT
        ).show()
        hideKeyboard()
        findNavController().navigate(R.id.storeListFragment)
    }
}