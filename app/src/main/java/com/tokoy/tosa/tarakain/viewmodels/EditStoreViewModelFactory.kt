package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo

@Suppress("UNCHECKED_CAST")
class EditStoreViewModelFactory (
    private val storeRepo: StoreRepo,
    private val categoryRepo: CategoryRepo
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        EditStoreViewModel(storeRepo, categoryRepo) as T
}