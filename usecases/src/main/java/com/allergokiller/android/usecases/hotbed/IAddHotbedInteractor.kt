package com.allergokiller.android.usecases.hotbed

import com.allergokiller.android.data.entity.Hotbed
import io.reactivex.Single

interface IAddHotbedInteractor {
    fun run(lat: Double,
            lng: Double,
            title: String,
            description: String): Single<Hotbed>
}