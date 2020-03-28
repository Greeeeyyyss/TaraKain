package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokoy.tosa.tarakain.db.repo.StoreRepo

@Suppress("UNCHECKED_CAST")
class HeadsOrTailsViewModelFactory (
    private val storeRepo: StoreRepo
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        HeadsOrTailsViewModel(storeRepo) as T
}