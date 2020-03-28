package com.tokoy.tosa.tarakain.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tokoy.tosa.tarakain.db.models.Store

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(store: Store)

    @Query("UPDATE Store SET isFavorite = 1 WHERE id = :id")
    suspend fun addToFavorite(id: Int)

    @Query("UPDATE Store SET isFavorite = 0 WHERE id = :id")
    suspend fun removeFromFavorite(id: Int)

    @Query("SELECT * FROM Store ORDER BY name")
    fun getAll(): LiveData<List<Store>>

    @Query("SELECT * FROM Store WHERE isFavorite = 1 ORDER BY name")
    fun getAllFavorites(): LiveData<List<Store>>

    @Delete
    suspend fun removeStore(store: Store)
}