package com.tokoy.tosa.tarakain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tokoy.tosa.tarakain.databinding.ViewStoreItemBinding
import com.tokoy.tosa.tarakain.db.models.Store

class StoreListAdapter : RecyclerView.Adapter<StoreListAdapter.StoreListViewHolder>() {
    private var stores: List<Store> = mutableListOf()
    var onStoreItemClick: (Store) -> Unit = {}
    var onUpdateStoreItemClick: (Store) -> Unit = {}

    fun setStores(stores: List<Store>) {
        this.stores = stores
    }

    class StoreListViewHolder(
        val binding: ViewStoreItemBinding,
        private val onStoreItemClick: (Store) -> Unit,
        private val onUpdateStoreItemClick: (Store) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(store: Store) {
            binding.store = store
            binding.layoutStoreItem.setOnClickListener {
                onStoreItemClick.invoke(store)
            }
            binding.imgFavorite.setOnClickListener {
                onUpdateStoreItemClick.invoke(store)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewStoreItemBinding.inflate(layoutInflater, parent, false)
        return StoreListViewHolder(binding, onStoreItemClick, onUpdateStoreItemClick)
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: StoreListViewHolder, position: Int) {
        val store = stores[position]
        holder.bind(store)
    }
}