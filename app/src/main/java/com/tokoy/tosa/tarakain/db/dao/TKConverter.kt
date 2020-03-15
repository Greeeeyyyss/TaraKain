package com.tokoy.tosa.tarakain.db.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tokoy.tosa.tarakain.db.models.Category

object TKConverter {
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