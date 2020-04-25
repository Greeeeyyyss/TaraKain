package com.tokoy.tosa.tarakain.viewmodels

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
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
    var searchText = ObservableField("")
    var stores = MediatorLiveData<List<Store>>()

    init {
        searchText.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                searchStore()
            }
        })
    }

    fun getStores() {
        val source = if (isFavorites) {
            storeRepo.getFavoriteStores()
        } else {
            storeRepo.getStores()
        }
        stores.addSource(source) {
            stores.postValue(it)
        }
    }

    fun searchStore() {
        val search = "%${searchText.get() ?: ""}%"
        val source = if (isFavorites) {
            storeRepo.searchFavoriteStore(search)
        } else {
            storeRepo.searchStore(search)
        }
        stores.addSource(source) {
            stores.postValue(it)
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

    fun onClearSearch() {
        searchText.set("")
    }
}