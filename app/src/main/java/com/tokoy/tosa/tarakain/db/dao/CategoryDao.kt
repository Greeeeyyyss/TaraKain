package com.tokoy.tosa.tarakain.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tokoy.tosa.tarakain.db.models.Category

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Insert
    fun insertAll(categories: List<Category>)

    @Query("SELECT * FROM Category")
    fun getAll(): LiveData<List<Category>>
}