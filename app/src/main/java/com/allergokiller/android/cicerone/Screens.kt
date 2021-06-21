package com.allergokiller.android.cicerone

import com.allergokiller.android.fragments.hotbed_details.HotbedDetailFragment
import com.allergokiller.android.fragments.map.MapFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MapFragment() }
    fun HotbedDetails(params: HotbedDetailFragment.Params) = FragmentScreen { HotbedDetailFragment.init(params) }
}