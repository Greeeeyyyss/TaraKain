package com.tokoy.tosa.tarakain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo

class CategoryViewModelFactory(
    private val repo: CategoryRepo
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = CategoryViewModel(repo) as T
}