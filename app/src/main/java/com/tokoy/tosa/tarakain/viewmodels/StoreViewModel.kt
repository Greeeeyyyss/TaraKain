package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StoreViewModel internal constructor(
    private val storeRepo: StoreRepo
): ViewModel() {
    val stores: LiveData<List<Store>> = storeRepo.getStores()

    val favorites: LiveData<List<Store>> = storeRepo.getFavoriteStores()

    fun addStore(store: Store) {
        GlobalScope.launch(Dispatchers.IO) {  storeRepo.addStore(store) }
    }

    fun addToFavorites(storeId: Int) = storeRepo.addToFavorites(storeId)

    fun removeFromFavorites(storeId: Int) = storeRepo.removeFromFavorites(storeId)
}