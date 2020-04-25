package com.tokoy.tosa.tarakain

import com.tokoy.tosa.tarakain.viewmodels.AddStoreViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddStoreViewModelTest: BaseViewModelTest() {
    private lateinit var viewModel: AddStoreViewModel

    @Before
    fun init() {
        viewModel = AddStoreViewModel(context, storeRepo, categoryRepo)
        viewModel.categoryList = categories
    }

    @Test
    fun testAddStore() {
        viewModel.newStore.name = "Store"
        viewModel.addStore()
        assertEquals(true, viewModel.storeAdded.value?.getContentIfNotHandled())
    }

    @Test
    fun testGetCategories() {
        viewModel.getCategories()
    }

    @Test
    fun testStoreInputValid() {
        viewModel.newStore.name = "Store"
        assertEquals(viewModel.isStoreValid(), true)
    }

    @Test
    fun testStorePriceInputValid() {
        viewModel.newStore.name = "Store"
        viewModel.newStore.minPrice = 100
        viewModel.newStore.maxPrice = 500
        assertEquals(viewModel.isStoreValid(), true)
    }

    @Test
    fun testStoreInputInvalid() {
        viewModel.newStore.name = ""
        assertEquals(viewModel.isStoreValid(), false)

        viewModel.newStore.name = "Store"
        viewModel.newStore.minPrice = 200
        viewModel.newStore.maxPrice = 100
        assertEquals(viewModel.isStoreValid(), false)
    }

    @Test
    fun testStoreEmptyNameInvalid() {
        viewModel.newStore.name = " "
        assertEquals(viewModel.isStoreValid(), false)
    }
}