package com.allergokiller.android.domain.datasource.rest.hotbed

import com.allergokiller.android.domain.entity.Hotbed
import io.reactivex.Single

interface RestGetHotbedByCircle {
    fun getHotbedByCircle(lat: Double, lng: Double): Single<List<Hotbed>>
}