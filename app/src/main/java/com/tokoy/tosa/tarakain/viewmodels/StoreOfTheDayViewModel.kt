package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.utils.Event
import javax.inject.Inject

class StoreOfTheDayViewModel @Inject constructor(
    private val storeRepo: StoreRepo
): ViewModel() {
    var isFavorites = false
    var onAddStoreEvent = MutableLiveData<Event<Boolean>>()
    var onRandomizedEvent = MutableLiveData<Event<Boolean>>()
    var onCheckStoresEvent = MutableLiveData<Event<Boolean>>()
    var storeList: List<Store> = mutableListOf()
    var chosenStore: Store = Store(name = "", isFavorite = false)

    fun getStores(): LiveData<List<Store>> {
        return if (isFavorites) {
            storeRepo.getFavoriteStores()
        } else {
            storeRepo.getStores()
        }
    }

    fun getRandomStore(): Store {
        if (storeList.isNotEmpty()) {
            chosenStore = storeList.random()
        }
        return chosenStore
    }

    fun randomize() {
        onRandomizedEvent.value = Event(true)
    }

    fun addStore() {
        onAddStoreEvent.value = Event(true)
    }

    fun checkStores() {
        onCheckStoresEvent.value = Event(true)
    }
}