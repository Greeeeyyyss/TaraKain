package com.tokoy.tosa.tarakain.db.repo

import com.tokoy.tosa.tarakain.db.dao.StoreDao
import com.tokoy.tosa.tarakain.db.models.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoreRepo constructor(private val storeDao: StoreDao) {
    // TODO use coroutines here

    suspend fun addStore(store: Store) {
        withContext(Dispatchers.IO) {
            storeDao.insert(store)
        }
    }

    fun getStores() = storeDao.getAll()

    fun getFavoriteStores() = storeDao.getAllFavorites()

    fun addToFavorites(storeId: Int) = storeDao.addToFavorite(storeId)

    fun removeFromFavorites(storeId: Int) = storeDao.removeFromFavorite(storeId)

    companion object {
        @Volatile private var instance: StoreRepo? = null

        fun getInstance(storeDao: StoreDao) =
            instance ?: synchronized(this) {
                instance ?: StoreRepo(storeDao).also { instance = it }
            }
    }
}