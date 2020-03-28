package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.StoreRepo

class StoreOfTheDayViewModel constructor(
    private val storeRepo: StoreRepo
): ViewModel() {
    val stores: LiveData<List<Store>> = storeRepo.getStores()

    val favorites: LiveData<List<Store>> = storeRepo.getFavoriteStores()
}