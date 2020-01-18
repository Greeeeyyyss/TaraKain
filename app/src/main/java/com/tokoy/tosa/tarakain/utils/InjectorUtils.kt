package com.tokoy.tosa.tarakain.utils

import android.content.Context
import com.tokoy.tosa.tarakain.db.TaraKainDatabase
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.viewmodels.CategoryViewModelFactory
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModelFactory

object InjectorUtils {
    private fun getCategoryRepository(context: Context): CategoryRepo {
        return CategoryRepo.getInstance(
            TaraKainDatabase.getInstance(context.applicationContext).categoryDao())
    }

    private fun getStoreRepository(context: Context): StoreRepo {
        return StoreRepo.getInstance(
            TaraKainDatabase.getInstance(context.applicationContext).storeDao())
    }

    fun provideCategoryViewModelFactory(context: Context): CategoryViewModelFactory {
        return CategoryViewModelFactory(getCategoryRepository(context))
    }

    fun provideStoreViewModelFactory(context: Context): StoreViewModelFactory {
        return StoreViewModelFactory(getStoreRepository(context))
    }
}