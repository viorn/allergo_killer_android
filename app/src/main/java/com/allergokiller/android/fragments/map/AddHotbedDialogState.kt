package com.allergokiller.android.fragments.map

import com.allergokiller.android.data.entity.Point

data class AddHotbedDialogState(
    val title: String = "",
    val description: String = "",
    val point: Point? = null,
    val loading: Boolean = false
)