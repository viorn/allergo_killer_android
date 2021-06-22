package com.allergokiller.android.domain.entity

data class Hotbed (
    val id: Long = -1,
    val title: String,
    val body: String,
    val position: Point,
    val images: List<Image> = emptyList()
)