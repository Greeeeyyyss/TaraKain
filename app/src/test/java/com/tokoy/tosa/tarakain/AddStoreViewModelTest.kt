package com.tokoy.tosa.tarakain

import com.tokoy.tosa.tarakain.viewmodels.AddStoreViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddStoreViewModelTest: BaseViewModelTest() {
    private lateinit var viewModel: AddStoreViewModel

    @Before
    fun init() {
        viewModel = AddStoreViewModel(storeRepo, categoryRepo)
        viewModel.categoryList = categories
    }

    @Test
    fun testAddStore() {
        viewModel.storeName.set("Store")
        viewModel.addStore()
        assertEquals(true, viewModel.storeAdded.value?.getContentIfNotHandled())
    }

    @Test
    fun testGetCategories() {
        viewModel.getCategories()
    }

    @Test
    fun testStoreInputValid() {
        viewModel.storeName.set("Store")
        assertEquals(viewModel.isStoreValid(), true)
    }

    @Test
    fun testStoreInputInvalid() {
        viewModel.storeName.set("")
        assertEquals(viewModel.isStoreValid(), false)
    }

    @Test
    fun testStoreEmptyNameInvalid() {
        viewModel.storeName.set(" ")
        assertEquals(viewModel.isStoreValid(), false)
    }
}