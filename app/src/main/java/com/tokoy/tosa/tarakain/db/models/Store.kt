package com.tokoy.tosa.tarakain.db.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tokoy.tosa.tarakain.db.dao.TKConverter

@Entity(indices = [Index(value = ["name"], unique = true)])
@TypeConverters(TKConverter::class)
data class Store constructor(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var name: String,
    var isFavorite: Boolean,
    var category: Category? = null,
    var minPrice: Int? = null,
    var maxPrice: Int? = null
)