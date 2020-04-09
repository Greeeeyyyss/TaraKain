package com.tokoy.tosa.tarakain

import com.tokoy.tosa.tarakain.viewmodels.HeadsOrTailsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HeadsOrTailViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: HeadsOrTailsViewModel

    @Before
    fun init() {
        viewModel = HeadsOrTailsViewModel(storeRepo)
        viewModel.storeNames = mutableListOf(
            "Pancake House",
            "Army Navy"
        )
        viewModel.headIndex.set(0)
        viewModel.tailIndex.set(1)
    }

    @Test
    fun testRandomize() {
        viewModel.randomize()
        if (viewModel.randomBoolean.get() == true) {
            assertEquals("Pancake House", viewModel.randomStore.get())
        } else {
            assertEquals("Army Navy", viewModel.randomStore.get())
        }
    }

    @Test
    fun testRandomizeMoreStore() {
        viewModel.storeNames = viewModel.storeNames.plus("Zoo Coffee")
        viewModel.storeNames = viewModel.storeNames.plus("Peri Peri Chicken")
        viewModel.headIndex.set(1)
        viewModel.tailIndex.set(3)
        viewModel.randomize()

        if (viewModel.randomBoolean.get() == true) {
            assertEquals("Army Navy", viewModel.randomStore.get())
        } else {
            assertEquals("Peri Peri Chicken", viewModel.randomStore.get())
        }
    }

    @Test
    fun testFlipCoin() {
        viewModel.flipCoin()
        assertEquals(true, viewModel.coinFlipEvent.value?.getContentIfNotHandled())
    }

    @Test
    fun addStore() {
        viewModel.addStore()
        assertEquals(true, viewModel.addStoreEvent.value?.getContentIfNotHandled())
    }
}