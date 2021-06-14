package com.allergokiller.android.usecases.hotbed

import com.allergokiller.android.data.entity.Hotbed
import com.allergokiller.android.data.gateway.IHotbedGateway
import io.reactivex.Scheduler
import io.reactivex.Single

class AddHotbedInteractor(
    private val scheduler: Scheduler,
    private val hotbedGateway: IHotbedGateway
): IAddHotbedInteractor {
    override fun run(lat: Double, lng: Double, title: String, description: String): Single<Hotbed> {
        return hotbedGateway.addHotbed(lat, lng, title, description).observeOn(scheduler)
    }
}