package com.tokoy.tosa.tarakain.di.modules

import android.app.Application
import androidx.room.Room
import com.tokoy.tosa.tarakain.db.TaraKainDatabase
import com.tokoy.tosa.tarakain.db.dao.CategoryDao
import com.tokoy.tosa.tarakain.db.dao.StoreDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): TaraKainDatabase {
        // TODO add pre-category
        return Room
            .databaseBuilder(app, TaraKainDatabase::class.java, "tarakain.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: TaraKainDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Singleton
    @Provides
    fun provideStoreDao(db: TaraKainDatabase): StoreDao {
        return db.storeDao()
    }
}