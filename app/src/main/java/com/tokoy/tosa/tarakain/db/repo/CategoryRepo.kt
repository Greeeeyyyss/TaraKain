package com.tokoy.tosa.tarakain.db.repo

import com.tokoy.tosa.tarakain.db.dao.CategoryDao
import com.tokoy.tosa.tarakain.db.models.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepo @Inject constructor(private val categoryDao: CategoryDao) {
    init {
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

        CoroutineScope(Dispatchers.IO).launch {
            if (categoryDao.getCount() == 0) {
                categoryDao.insertAll(categories)
            }
        }
    }

    fun getCategories() = categoryDao.getAll()

    fun addCategory(category: Category) = categoryDao.insert(category)
}