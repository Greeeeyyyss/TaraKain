package com.tokoy.tosa.tarakain.db.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tokoy.tosa.tarakain.db.models.Category
import com.tokoy.tosa.tarakain.db.models.Store

object TKConverter {
    @TypeConverter
    @JvmStatic
    fun stringToStore(data: String?): Store? {
        val gson = Gson()
        return gson.fromJson(data, Store::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun storeToString(store: Store?): String? {
        val gson = Gson()
        return gson.toJson(store, Store::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun stringToCategory(data: String?): Category? {
        val gson = Gson()
        return gson.fromJson(data, Category::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun categoryToString(category: Category?): String? {
        val gson = Gson()
        return gson.toJson(category, Category::class.java)
    }
}