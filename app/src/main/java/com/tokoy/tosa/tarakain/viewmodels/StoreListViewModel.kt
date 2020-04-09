package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.utils.Event
import javax.inject.Inject
import kotlinx.coroutines.launch

class StoreListViewModel @Inject constructor(
    private val storeRepo: StoreRepo
) : ViewModel() {
    var isFavorites = false
    var isStoreUpdated = MutableLiveData<Event<Store>>()

    fun getStores(): LiveData<List<Store>> {
        return if (isFavorites) {
            storeRepo.getFavoriteStores()
        } else {
            storeRepo.getStores()
        }
    }

    fun updateStore(store: Store) {
        val storeId = store.id ?: return
        viewModelScope.launch {
            if (store.isFavorite) {
                storeRepo.removeFromFavorites(storeId)
            } else {
                storeRepo.addToFavorites(storeId)
            }
        }
        isStoreUpdated.value = Event(store)
    }
}