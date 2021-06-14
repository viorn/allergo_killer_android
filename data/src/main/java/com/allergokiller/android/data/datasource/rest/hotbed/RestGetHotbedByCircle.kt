package com.allergokiller.android.data.datasource.rest.hotbed

import com.allergokiller.android.data.entity.Hotbed
import io.reactivex.Single

interface RestGetHotbedByCircle {
    fun getHotbedByCircle(lat: Double, lng: Double): Single<List<Hotbed>>
}