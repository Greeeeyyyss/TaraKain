package com.tokoy.tosa.tarakain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tokoy.tosa.tarakain.asset.CoroutineTestRule
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito


open class BaseViewModelTest {
    @JvmField
    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    val storeRepo: StoreRepo = Mockito.mock(StoreRepo::class.java)
    val categoryRepo: CategoryRepo = Mockito.mock(CategoryRepo::class.java)

    val categories = mutableListOf(
        Category(name = "All"),
        Category(name = "American"),
        Category(name = "Buffet"),
        Category(name = "Cafe"),
        Category(name = "Chinese"),
        Category(name = "Filipino"),
        Category(name = "Fast Food"),
        Category(name = "Italian"),
        Category(name = "Japanese"),
        Category(name = "Korean"),
        Category(name = "Mexican"),
        Category(name = "Spanish"),
        Category(name = "Thai")
    )

    val stores = mutableListOf(
        Store(name = "All Store", isFavorite = false, category = categories.first()),
        Store(name = "American Store", isFavorite = false, category = categories[2]),
        Store(name = "Buffet Store", isFavorite = true, category = categories[3]),
        Store(name = "Thai Store", isFavorite = true, category = categories.last())
    )
}