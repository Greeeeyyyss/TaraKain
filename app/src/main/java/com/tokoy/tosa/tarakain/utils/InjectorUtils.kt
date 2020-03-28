package com.tokoy.tosa.tarakain.utils

import android.content.Context
import com.tokoy.tosa.tarakain.db.TaraKainDatabase
import com.tokoy.tosa.tarakain.db.repo.CategoryRepo
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.viewmodels.*

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

    fun provideStoreListViewModelFactory(context: Context): StoreListViewModelFactory {
        return StoreListViewModelFactory(getStoreRepository(context))
    }

    fun provideAddStoreViewModelFactory(context: Context): AddStoreViewModelFactory {
        return AddStoreViewModelFactory(getStoreRepository(context), getCategoryRepository(context))
    }

    fun provideEditStoreViewModelFactory(context: Context): EditStoreViewModelFactory {
        return EditStoreViewModelFactory(getStoreRepository(context), getCategoryRepository(context))
    }

    fun provideHeadsOrTailsViewModelFactory(context: Context): HeadsOrTailsViewModelFactory {
        return HeadsOrTailsViewModelFactory(getStoreRepository(context))
    }

    fun provideStoreOfTheDayViewModelFactory(context: Context): StoreOfTheDayViewModelFactory {
        return StoreOfTheDayViewModelFactory(getStoreRepository(context))
    }
}