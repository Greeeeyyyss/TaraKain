package com.tokoy.tosa.tarakain.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddStoreViewModel constructor (
    private val storeRepo: StoreRepo,
    private val categoryRepo: CategoryRepo
): ViewModel() {
    var categoryNames = listOf<String>()
    var categoryIndex = ObservableField(0)

    val categories = categoryRepo.getCategories()

    fun addStore(store: Store) {
        GlobalScope.launch(Dispatchers.IO) {
            storeRepo.addStore(store)
        }
    }
}