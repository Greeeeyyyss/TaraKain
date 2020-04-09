package com.tokoy.tosa.tarakain

import com.tokoy.tosa.tarakain.viewmodels.StoreOfTheDayViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class StoreOfTheDayViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: StoreOfTheDayViewModel

    @Before
    fun init() {
        viewModel = StoreOfTheDayViewModel(storeRepo)
    }

    @Test
    fun testGetStores() {
        viewModel.getStores()
    }

    @Test
    fun testRandomStoreDefault() {
        viewModel.getRandomStore()
        assertEquals("", viewModel.chosenStore.name)
        assertEquals(false, viewModel.chosenStore.isFavorite)
    }

    @Test
    fun testRandomStore() {
        viewModel.storeList = stores
        viewModel.getRandomStore()
        assertNotEquals("", viewModel.chosenStore.name)
    }

    @Test
    fun testAddStore() {
        viewModel.addStore()
        assertEquals(true, viewModel.onAddStoreEvent.value?.getContentIfNotHandled())
    }

    @Test
    fun testRandomize() {
        viewModel.randomize()
        assertEquals(true, viewModel.onRandomizedEvent.value?.getContentIfNotHandled())
    }

    @Test
    fun testCheckStore() {
        viewModel.checkStores()
        assertEquals(true, viewModel.onCheckStoresEvent.value?.getContentIfNotHandled())
    }
}