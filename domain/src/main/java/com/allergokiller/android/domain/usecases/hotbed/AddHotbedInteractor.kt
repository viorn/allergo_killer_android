package com.allergokiller.android.domain.usecases.hotbed

import com.allergokiller.android.domain.entity.Hotbed
import com.allergokiller.android.domain.gateway.IHotbedGateway
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