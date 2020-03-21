package com.tokoy.tosa.tarakain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tokoy.tosa.tarakain.db.dao.CategoryDao
import com.tokoy.tosa.tarakain.db.dao.StoreDao
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                .addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
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
                        // TODO should we use global scope
                        GlobalScope.launch(Dispatchers.IO){
                            getInstance(context).categoryDao()
                                .insertAll(categories)
                        }
                    }
                })
                .build()
        }
    }
}