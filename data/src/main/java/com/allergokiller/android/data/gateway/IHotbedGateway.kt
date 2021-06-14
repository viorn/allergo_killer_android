package com.allergokiller.android.data.gateway

import com.allergokiller.android.data.entity.Hotbed
import io.reactivex.Flowable
import io.reactivex.Single

interface IHotbedGateway {
    fun getFlowLastSearchHotbedList(): Flowable<List<Hotbed>>

    fun findHotbedByCircle(lat: Double, lng: Double): Single<List<Hotbed>>

    fun addHotbed(
        lat: Double,
        lng: Double,
        title: String,
        description: String
    ): Single<Hotbed>
}