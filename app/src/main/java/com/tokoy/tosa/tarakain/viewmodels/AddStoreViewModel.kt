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
import javax.inject.Inject
import kotlinx.coroutines.launch

class AddStoreViewModel @Inject constructor (
    private val storeRepo: StoreRepo,
    private val categoryRepo: CategoryRepo
) : ViewModel() {
    var categoryList: List<Category> = mutableListOf()
    var categoryNames = listOf<String>()
    var categoryIndex = ObservableField(0)
    var isFavorite = false
    var storeAdded = MutableLiveData<Event<Boolean>>()
    var newStore: Store = Store(
        name = "",
        isFavorite = isFavorite
    )

    fun isStoreValid() = newStore.name.isNotBlank()

    fun getCategories() = categoryRepo.getCategories()

    fun addStore() {
        if (isStoreValid()) {
            newStore.category = categoryList[categoryIndex.get() ?: 0]
            viewModelScope.launch {
                storeRepo.addStore(newStore)
            }
            storeAdded.value = Event(true)
        }
    }
}