package com.tokoy.tosa.tarakain.db.repo

import com.tokoy.tosa.tarakain.db.dao.StoreDao
import com.tokoy.tosa.tarakain.db.models.Store

class StoreRepo constructor(private val storeDao: StoreDao) {

    suspend fun addStore(store: Store) {
        storeDao.insert(store)
    }

    suspend fun removeStore(store: Store) {
        storeDao.removeStore(store)
    }

    fun getStores() = storeDao.getAll()

    fun getFavoriteStores() = storeDao.getAllFavorites()

    suspend fun addToFavorites(storeId: Int) = storeDao.addToFavorite(storeId)

    suspend fun removeFromFavorites(storeId: Int) = storeDao.removeFromFavorite(storeId)

    companion object {
        @Volatile private var instance: StoreRepo? = null

        fun getInstance(storeDao: StoreDao) =
            instance ?: synchronized(this) {
                instance ?: StoreRepo(storeDao).also { instance = it }
            }
    }
}