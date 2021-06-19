package com.allergokiller.android.factories.map

import com.allergokiller.android.data.entity.Hotbed
import org.osmdroid.api.IGeoPoint
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint

interface IHotbedOSMOverlayFactory {
    class DataGeoPoint(aLatitude: Double, aLongitude: Double, aLabel: String?, val data: Hotbed) :
        LabelledGeoPoint(aLatitude, aLongitude, aLabel)

    interface PointClickListener {
        fun onClickListener(point: DataGeoPoint)
    }

    fun buildOverlay(hotbedList: List<Hotbed>, clickListener: PointClickListener? = null): Overlay
}