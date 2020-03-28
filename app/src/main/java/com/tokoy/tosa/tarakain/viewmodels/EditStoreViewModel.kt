package com.tokoy.tosa.tarakain.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.utils.Event

import kotlinx.coroutines.launch

class EditStoreViewModel constructor(
    private val storeRepo: StoreRepo,
    private val categoryRepo: CategoryRepo
): ViewModel() {
    var store: Store = Store(name = "", isFavorite = false)
    var categoryList: List<Category> = mutableListOf()
    var categoryNames = listOf<String>()
    var categoryIndex = ObservableField(0)
    var isStoreUpdated = MutableLiveData<Event<Boolean>>()

    fun isStoreValid() = store.name.isNotBlank()

    fun getCategories() = categoryRepo.getCategories()

    fun updateStore() {
        if (isStoreValid()) {
            store.category = categoryList[categoryIndex.get() ?: 0]
            viewModelScope.launch {
                storeRepo.addStore(store)
            }
            isStoreUpdated.value = Event(true)
        }
    }
}