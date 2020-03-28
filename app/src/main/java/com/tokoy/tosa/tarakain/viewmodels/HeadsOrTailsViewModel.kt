package com.tokoy.tosa.tarakain.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.StoreRepo

class HeadsOrTailsViewModel constructor(private val storeRepo: StoreRepo): ViewModel() {
    var storeNames = listOf<String>()
    var headIndex = ObservableField(0)
    var tailIndex = ObservableField(0)

    val stores: LiveData<List<Store>> = storeRepo.getStores()
}