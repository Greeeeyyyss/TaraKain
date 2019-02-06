package com.tokoy.tosa.tarakain.models

data class Store constructor(
    val id: Int,
    val name: String,
    val categories: List<Category>,
    val minPrice: Float,
    val maxPrice: Float
)