package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo

class CategoryViewModel internal constructor(
    private val categoryRepo: CategoryRepo
): ViewModel() {
    val categories = categoryRepo.getCategories()

    fun addCategory(category: Category) = categoryRepo.addCategory(category)
}