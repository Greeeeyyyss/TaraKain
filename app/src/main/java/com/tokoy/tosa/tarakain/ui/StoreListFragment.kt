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
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.adapters.StoreListAdapter
import com.tokoy.tosa.tarakain.databinding.FragmentStoreListBinding
import com.tokoy.tosa.tarakain.db.dao.TKConverter
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.di.Injectable
import com.tokoy.tosa.tarakain.utils.Constants
import com.tokoy.tosa.tarakain.utils.EventObserver
import com.tokoy.tosa.tarakain.viewmodels.StoreListViewModel
import javax.inject.Inject

class StoreListFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentStoreListBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StoreListViewModel
    private var storeListAdapter: StoreListAdapter? = null
    private val args: StoreListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_store_list,
            container,
            false
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(StoreListViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.isFavorites = args.isFavorites

        viewModel.stores.observe(viewLifecycleOwner, Observer { stores ->
            binding.showEmptyState = stores.isEmpty()
            storeListAdapter?.setStores(stores)
            storeListAdapter?.notifyDataSetChanged()
        })

        viewModel.isStoreUpdated.observe(viewLifecycleOwner, EventObserver { store ->
            storeListAdapter?.notifyItemChanged(store.id ?: 0)
            if (storeListAdapter?.itemCount == 0) {
                binding.showEmptyState = true
            }
        })

        setupList()

        return binding.root
    }

    private fun setupList() {
        viewModel.getStores()
        storeListAdapter = StoreListAdapter()
        storeListAdapter?.onStoreItemClick = { store ->
            goToEditStore(store)
        }
        storeListAdapter?.onUpdateStoreItemClick = { store ->
            viewModel.updateStore(store)
        }
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = storeListAdapter
        }
    }

    private fun goToEditStore(store: Store) {
        val bundle = Bundle()
        bundle.putString(Constants.Key.store, TKConverter.storeToString(store))
        findNavController().navigate(R.id.action_storeList_to_editStore, bundle)
    }
}