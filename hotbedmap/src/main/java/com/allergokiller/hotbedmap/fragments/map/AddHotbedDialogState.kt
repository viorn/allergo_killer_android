package com.allergokiller.hotbedmap.fragments.map

import com.allergokiller.android.domain.entity.Point

data class AddHotbedDialogState(
    val title: String = "",
    val description: String = "",
    val point: Point? = null,
    val loading: Boolean = false
)