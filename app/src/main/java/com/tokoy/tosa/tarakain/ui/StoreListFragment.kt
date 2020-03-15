package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentStoreListBinding
import com.tokoy.tosa.tarakain.databinding.ViewStoreItemBinding
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModel

class StoreListFragment : Fragment() {
    private lateinit var binding: FragmentStoreListBinding
    private val viewModel: StoreViewModel by viewModels {
        InjectorUtils.provideStoreViewModelFactory(requireContext())
    }

    private var storeListAdapter: StoreListAdapter? = null

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
        storeListAdapter = StoreListAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = storeListAdapter
        }
        getStores()
        return binding.root
    }

    private fun getStores() {
        viewModel.stores.observe(viewLifecycleOwner, Observer { stores ->
            storeListAdapter?.setStores(stores)
            storeListAdapter?.notifyDataSetChanged()
        })
    }
}

class StoreListAdapter : RecyclerView.Adapter<StoreListAdapter.StoreListViewHolder>() {
    private var stores: List<Store> = mutableListOf()

    fun setStores(stores: List<Store>) {
        this.stores = stores
    }

    class StoreListViewHolder(val binding: ViewStoreItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(store: Store) {
            binding.store = store
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewStoreItemBinding.inflate(layoutInflater, parent, false)
        return StoreListViewHolder(binding)
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: StoreListViewHolder, position: Int) {
        val store = stores[position]
        holder.bind(store)
    }
}