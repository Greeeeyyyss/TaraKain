package com.tokoy.tosa.tarakain.db.repo

import com.tokoy.tosa.tarakain.db.dao.CategoryDao
import com.tokoy.tosa.tarakain.db.models.Category

class CategoryRepo constructor(private val categoryDao: CategoryDao) {
    fun getCategories() = categoryDao.getAll()

    fun addCategory(category: Category) = categoryDao.insert(category)

    companion object {
        @Volatile private var instance: CategoryRepo? = null

        fun getInstance(categoryDao: CategoryDao) =
            instance ?: synchronized(this) {
                instance ?: CategoryRepo(categoryDao).also { instance = it }
            }
    }
}