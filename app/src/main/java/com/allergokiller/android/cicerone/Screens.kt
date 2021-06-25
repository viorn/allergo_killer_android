package com.allergokiller.android.cicerone

import com.allergokiller.hotbedmap.fragments.hotbed_details.HotbedDetailFragment
import com.allergokiller.hotbedmap.fragments.map.MapFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MapFragment() }
    fun HotbedDetails(params: HotbedDetailFragment.Params) = FragmentScreen { HotbedDetailFragment.init(params) }
}