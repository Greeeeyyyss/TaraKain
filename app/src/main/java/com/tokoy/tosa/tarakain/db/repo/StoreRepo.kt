package com.tokoy.tosa.tarakain.db.repo

import com.tokoy.tosa.tarakain.db.dao.StoreDao
import com.tokoy.tosa.tarakain.db.models.Store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepo @Inject constructor(private val storeDao: StoreDao) {

    suspend fun addStore(store: Store) {
        storeDao.insert(store)
    }

    suspend fun removeStore(store: Store) {
        storeDao.removeStore(store)
    }

    fun getStores() = storeDao.getAll()

    fun getFavoriteStores() = storeDao.getAllFavorites()

    fun searchStore(search: String) = storeDao.searchStore(search)

    fun searchFavoriteStore(search: String) = storeDao.searchFavoriteStore(search)

    suspend fun addToFavorites(storeId: Int) = storeDao.addToFavorite(storeId)

    suspend fun removeFromFavorites(storeId: Int) = storeDao.removeFromFavorite(storeId)
}