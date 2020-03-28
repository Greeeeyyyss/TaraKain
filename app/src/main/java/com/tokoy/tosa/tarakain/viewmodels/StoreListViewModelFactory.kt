package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokoy.tosa.tarakain.db.repo.StoreRepo

class StoreListViewModelFactory(
    private val repo: StoreRepo
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = StoreListViewModel(repo) as T
}