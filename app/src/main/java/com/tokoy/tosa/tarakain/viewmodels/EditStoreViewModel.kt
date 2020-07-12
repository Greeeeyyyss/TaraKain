package com.tokoy.tosa.tarakain.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.utils.Event
import javax.inject.Inject
import kotlinx.coroutines.launch

class EditStoreViewModel @Inject constructor(
    private val context: Application,
    private val storeRepo: StoreRepo,
    private val categoryRepo: CategoryRepo
) : AndroidViewModel(context) {
    var store: Store = Store(name = "", isFavorite = false)
    var categoryList: List<Category> = mutableListOf()
    var categoryNames = listOf<String>()
    var categoryIndex = ObservableField(0)
    var isStoreUpdated = MutableLiveData<Event<Boolean>>()
    var isStoreDeleted = MutableLiveData<Event<Boolean>>()

    var nameError = ObservableField("")
    var minPriceError = ObservableField("")
    var maxPriceError = ObservableField("")

    fun isStoreValid(): Boolean {
        var isValid = true
        val maxPrice = store.maxPrice
        val minPrice = store.minPrice

        minPriceError.set("")
        maxPriceError.set("")

        if (maxPrice != null && minPrice != null && maxPrice <= minPrice) {
            isValid = false
            minPriceError.set(context.getString(R.string.error_store_min_price))
            maxPriceError.set(context.getString(R.string.error_store_max_price))
        }

        if (store.name.isBlank()) {
            isValid = false
            nameError.set(context.getString(R.string.error_store_name))
        } else {
            nameError.set("")
        }
        return isValid
    }

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

    fun deleteStore() {
        viewModelScope.launch {
            storeRepo.removeStore(store)
        }
        isStoreDeleted.value = Event(true)
    }
}