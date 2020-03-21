package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentAddStoreBinding
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.utils.Constants
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.utils.hideKeyboard
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModel

class AddStoreFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentAddStoreBinding
    private var storeCategory: String? = ""

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
        binding.spinner.onItemSelectedListener = this
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.food_categories,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }
        }
        return binding.root
    }

    private fun onAddStoreClick() {
        val name = binding.storeName.text?.toString() ?: return
        val store = Store(name = name, isFavorite = binding.addToFavorites.isChecked)
        if (storeCategory?.isNotEmpty() == true) {
            store.category = Category(name = storeCategory ?: "")
        }
        viewModel.addStore(store)

        Toast.makeText(
            context,
            getString(R.string.store_saved_successful),
            Toast.LENGTH_SHORT
        ).show()
        hideKeyboard()
        findNavController().navigate(R.id.storeListFragment)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        storeCategory = parent?.getItemAtPosition(position) as? String
    }
}