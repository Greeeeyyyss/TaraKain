package com.tokoy.tosa.tarakain.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.utils.Event
import kotlin.random.Random

class HeadsOrTailsViewModel constructor(private val storeRepo: StoreRepo): ViewModel() {
    var storeNames = listOf<String>()
    var headIndex = ObservableField(0)
    var tailIndex = ObservableField(0)
    var randomStore = ObservableField("")
    var randomBoolean = ObservableField(false)
    var coinFlipped = MutableLiveData<Event<Boolean>>()

    val stores: LiveData<List<Store>> = storeRepo.getStores()

    fun randomize() {
        randomBoolean.set(Random.nextBoolean())

        if (randomBoolean.get() == true) {
            randomStore.set(storeNames[headIndex.get() ?: 0])
        } else {
            randomStore.set(storeNames[tailIndex.get() ?: 0])
        }
    }

    fun flipCoin() {
        coinFlipped.value = Event(true)
    }
}