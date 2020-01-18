package com.tokoy.tosa.tarakain.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Store constructor(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var name: String,
    var description: String? = null,
    var minPrice: Float? = null,
    var maxPrice: Float? = null,
    var isFavorite: Boolean
)