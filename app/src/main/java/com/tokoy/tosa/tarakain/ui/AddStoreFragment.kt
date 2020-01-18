package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentAddStoreBinding
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModel

class AddStoreFragment : Fragment() {
    private lateinit var binding: FragmentAddStoreBinding

    private val viewModel: StoreViewModel by viewModels {
        InjectorUtils.provideStoreViewModelFactory(requireContext())
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
        return binding.root
    }

    private fun onAddStoreClick() {
        val name = binding.storeName.text.toString()
        val maxPrice = binding.maxPrice.text.toString().toFloat()
        val minPrice = binding.minPrice.text.toString().toFloat()
        val store = Store(
            name = name,
            minPrice = minPrice,
            maxPrice = maxPrice,
            isFavorite = binding.addToFavorites.isChecked
        )
        viewModel.addStore(store)
        Toast.makeText(context, "Store saved successfully", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}