package com.tokoy.tosa.tarakain.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo

class CategoryViewModel internal constructor(
    private val categoryRepo: CategoryRepo
): ViewModel() {
    var categoryNames = listOf<String>()
    var categoryIndex = ObservableField(0)

    val categories = categoryRepo.getCategories()

    fun addCategory(category: Category) = categoryRepo.addCategory(category)
}