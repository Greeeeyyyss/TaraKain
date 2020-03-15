package com.tokoy.tosa.tarakain.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category constructor(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var name: String
)

