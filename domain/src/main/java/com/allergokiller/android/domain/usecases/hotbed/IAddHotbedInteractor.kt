package com.allergokiller.android.domain.usecases.hotbed

import com.allergokiller.android.domain.entity.Hotbed
import io.reactivex.Single

interface IAddHotbedInteractor {
    fun run(lat: Double,
            lng: Double,
            title: String,
            description: String): Single<Hotbed>
}