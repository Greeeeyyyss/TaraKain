package com.tokoy.tosa.tarakain

import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.viewmodels.StoreListViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StoreListViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: StoreListViewModel

    @Before
    fun init() {
        viewModel = StoreListViewModel(storeRepo)
    }

    @Test
    fun testGetStores() {
        viewModel.getStores()
    }

    @Test
    fun testAddStoreToFavorite() {
        val category = categories.first()
        val store = Store(1, "Ordinary Store", false, category = category)
        viewModel.updateStore(store)
        assertEquals(store, viewModel.isStoreUpdated.value?.getContentIfNotHandled())
    }

    @Test
    fun testRemoveStoreFromFavorite() {
        val category = categories.last()
        val store = Store(2, "Favorite Store", true, category = category)
        viewModel.updateStore(store)
        assertEquals(store, viewModel.isStoreUpdated.value?.getContentIfNotHandled())
    }

    @Test
    fun testClearSearch() {
        viewModel.searchText.set("Store X")
        // viewModel.onClearSearch()
        // assertEquals("", viewModel.searchText.get())
    }

    @Test
    fun testSearchStore() {
        viewModel.searchText.set("Store X")
        // viewModel.searchStore()
    }
}