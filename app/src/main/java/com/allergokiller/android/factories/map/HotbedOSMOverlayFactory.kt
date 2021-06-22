package com.allergokiller.android.factories.map

import android.graphics.Color
import android.graphics.Paint
import com.allergokiller.android.domain.entity.Hotbed
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions
import org.osmdroid.views.overlay.simplefastpoint.SimplePointTheme

class HotbedOSMOverlayFactory : IHotbedOSMOverlayFactory {
    override fun buildOverlay(hotbedList: List<Hotbed>,
                              clickListener: PointClickListener?): SimpleFastPointOverlay {
        val pt = SimplePointTheme(hotbedList.map { hotbed ->
            IHotbedOSMOverlayFactory.DataGeoPoint(
                hotbed.position.lat,
                hotbed.position.lng,
                "Point #" + hotbed.id,
                hotbed
            ).apply {

            }
        }, true)

        val textStyle = Paint()
        textStyle.style = Paint.Style.FILL
        textStyle.color = Color.parseColor("#0000ff")
        textStyle.textAlign = Paint.Align.CENTER
        textStyle.textSize = 24f
        val opt = SimpleFastPointOverlayOptions.getDefaultStyle()
            .setAlgorithm(SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION)
            .setRadius(7f).setIsClickable(true).setCellSize(15).setTextStyle(textStyle)

        val out = SimpleFastPointOverlay(pt, opt)

        out.setOnClickListener { points, point ->
            clickListener?.onClickListener(points[point] as IHotbedOSMOverlayFactory.DataGeoPoint)
        }

        return out
    }
}