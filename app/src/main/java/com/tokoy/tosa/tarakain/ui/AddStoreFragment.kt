package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentAddStoreBinding

class AddStoreFragment : Fragment() {
    private lateinit var binding: FragmentAddStoreBinding

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
        Toast.makeText(context, "Store saved successfully", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}