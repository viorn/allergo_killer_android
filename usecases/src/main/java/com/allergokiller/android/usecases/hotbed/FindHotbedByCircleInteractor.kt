package com.allergokiller.android.usecases.hotbed

import com.allergokiller.android.data.entity.Hotbed
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.data.gateway.IHotbedGateway
import io.reactivex.Scheduler
import io.reactivex.Single

internal class FindHotbedByCircleInteractor(
    private val scheduler: Scheduler,
    private val iHotbedGateway: IHotbedGateway
): IFindHotbedByCircleInteractor {
    override fun run(center: Point, radius: Double): Single<List<Hotbed>> {
        return iHotbedGateway.findHotbedByCircle(center.lat, center.lng).observeOn(scheduler)
    }
}