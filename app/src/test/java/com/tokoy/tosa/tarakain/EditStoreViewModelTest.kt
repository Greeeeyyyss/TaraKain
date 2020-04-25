package com.tokoy.tosa.tarakain

import com.tokoy.tosa.tarakain.viewmodels.EditStoreViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EditStoreViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: EditStoreViewModel

    @Before
    fun init() {
        viewModel = EditStoreViewModel(context, storeRepo, categoryRepo)
        viewModel.categoryList = categories
    }

    @Test
    fun testStoreInputValid() {
        viewModel.store.name = "Store"
        assertEquals(viewModel.isStoreValid(), true)
    }

    @Test
    fun testStorePriceInputValid() {
        viewModel.store.name = "Store"
        viewModel.store.minPrice = 100
        viewModel.store.maxPrice = 500
        assertEquals(viewModel.isStoreValid(), true)
    }

    @Test
    fun testStoreInputInvalid() {
        viewModel.store.name = ""
        assertEquals(viewModel.isStoreValid(), false)

        viewModel.store.name = "Store"
        viewModel.store.minPrice = 500
        viewModel.store.maxPrice = 100
        assertEquals(viewModel.isStoreValid(), false)
    }

    @Test
    fun testStoreEmptyNameInvalid() {
        viewModel.store.name = "    "
        assertEquals(viewModel.isStoreValid(), false)
    }

    @Test
    fun testUpdateStore() {
        viewModel.store.name = "Store X"
        viewModel.categoryIndex.set(2)
        viewModel.updateStore()
        assertEquals(true, viewModel.isStoreUpdated.value?.getContentIfNotHandled())
    }

    @Test
    fun testGetCategories() {
        viewModel.getCategories()
    }

    @Test
    fun testDeleteStore() {
        viewModel.deleteStore()
        assertEquals(true, viewModel.isStoreDeleted.value?.getContentIfNotHandled())
    }
}