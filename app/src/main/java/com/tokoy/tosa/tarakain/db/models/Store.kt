package com.tokoy.tosa.tarakain.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Store constructor(
    @PrimaryKey var id: Int,
    var name: String,
    var description: String?,
    var categories: List<Category>,
    var minPrice: Float?,
    var maxPrice: Float?,
    var isFavorite: Boolean
)