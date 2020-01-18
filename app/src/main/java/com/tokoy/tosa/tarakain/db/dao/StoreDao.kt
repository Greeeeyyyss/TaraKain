package com.tokoy.tosa.tarakain.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tokoy.tosa.tarakain.db.models.Store

@Dao
interface StoreDao {
    @Insert
    suspend fun insert(store: Store)

    @Query("UPDATE Store SET isFavorite = 1 WHERE id = :id")
    fun addToFavorite(id: Int)

    @Query("UPDATE Store SET isFavorite = 0 WHERE id = :id")
    fun removeFromFavorite(id: Int)

    @Query("SELECT * FROM Store")
    fun getAll(): LiveData<List<Store>>

    @Query("SELECT * FROM Store WHERE isFavorite = 1")
    fun getAllFavorites(): LiveData<List<Store>>
}