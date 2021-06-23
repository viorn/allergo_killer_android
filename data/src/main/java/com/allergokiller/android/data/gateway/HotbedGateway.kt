package com.allergokiller.android.data.gateway

import com.allergokiller.android.data.datasource.rest.hotbed.RestAddNewHotbed
import com.allergokiller.android.data.datasource.rest.hotbed.RestGetHotbedByCircle
import com.allergokiller.android.domain.entity.Hotbed
import com.allergokiller.android.domain.gateway.IHotbedGateway
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor

internal class HotbedGateway(
    private val scheduler: Scheduler,
    private val restGetHotbedByCircle: RestGetHotbedByCircle,
    private val restAddNewHotbed: RestAddNewHotbed
) : IHotbedGateway {
    private val lastSearchHotbedList = BehaviorProcessor.createDefault<List<Hotbed>>(emptyList())

    override fun getFlowLastSearchHotbedList(): Flowable<List<Hotbed>> {
        return lastSearchHotbedList.onBackpressureLatest()
    }

    override fun findHotbedByCircle(lat: Double, lng: Double): Single<List<Hotbed>> {
        return restGetHotbedByCircle
            .getHotbedByCircle(lat, lng)
            .observeOn(scheduler)
            .doOnSuccess {
                lastSearchHotbedList.onNext(it)
            }
    }

    override fun addHotbed(
        lat: Double,
        lng: Double,
        title: String,
        description: String
    ): Single<Hotbed> {
        return restAddNewHotbed.addNewHotbed(
            RestAddNewHotbed.Body(
                title = title,
                lat = lat,
                lng = lng,
                body = description
            )
        ).observeOn(scheduler)
            .doOnSuccess {
                lastSearchHotbedList.onNext(lastSearchHotbedList.value!! + it)
            }
    }
}