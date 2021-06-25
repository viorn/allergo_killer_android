package com.allergokiller.hotbedmap.factories.map

import com.allergokiller.android.domain.entity.Hotbed
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint

interface IHotbedOSMOverlayFactory {
    class DataGeoPoint(aLatitude: Double, aLongitude: Double, aLabel: String?, override val hotbed: Hotbed) :
        LabelledGeoPoint(aLatitude, aLongitude, aLabel), HotbedPoint

    fun buildOverlay(hotbedList: List<Hotbed>, clickListener: PointClickListener? = null): Overlay
}