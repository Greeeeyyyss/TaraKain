package com.tokoy.tosa.tarakain.db

import androidx.room.Database
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
}