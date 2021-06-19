package com.allergokiller.android.factories.map

import com.allergokiller.android.data.entity.Hotbed
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint

interface IHotbedOSMOverlayFactory {
    class DataGeoPoint(aLatitude: Double, aLongitude: Double, aLabel: String?, override val hotbed: Hotbed) :
        LabelledGeoPoint(aLatitude, aLongitude, aLabel), HotbedPoint

    fun buildOverlay(hotbedList: List<Hotbed>, clickListener: PointClickListener? = null): Overlay
}