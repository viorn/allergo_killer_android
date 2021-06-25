package com.allergokiller.hotbedmap.fragments.map

import com.allergokiller.android.domain.entity.Hotbed


data class MapFragmentState (
   val hotbedList: List<Hotbed> = emptyList(),
   val loading: Boolean = false
)