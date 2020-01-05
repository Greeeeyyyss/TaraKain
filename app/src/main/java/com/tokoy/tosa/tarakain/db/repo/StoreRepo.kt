package com.tokoy.tosa.tarakain.db.repo

import com.tokoy.tosa.tarakain.db.dao.StoreDao

class StoreRepo constructor(private val storeDao: StoreDao) {
    // TODO dagger inject repo on fragments

    fun getStores() = storeDao.getAll()

    fun getFavoriteStores() = storeDao.getAllFavorites()

    fun addToFavorites(storeId: Int) = storeDao.addToFavorite(storeId)

    fun removeFromFavorites(storeId: Int) = storeDao.removeFromFavorite(storeId)
}