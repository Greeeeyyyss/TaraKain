package com.tokoy.tosa.tarakain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tokoy.tosa.tarakain.db.dao.CategoryDao
import com.tokoy.tosa.tarakain.db.dao.StoreDao
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store

@Database(
    entities = [
        Category::class,
        Store::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TaraKainDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun storeDao(): StoreDao

    companion object {
        @Volatile
        private var instance: TaraKainDatabase? = null

        fun getInstance(context: Context): TaraKainDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TaraKainDatabase {
            return Room.databaseBuilder(context, TaraKainDatabase::class.java, "tarakain.db")
                .build()
        }
    }
}