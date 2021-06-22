package com.allergokiller.android.domain.datasource.rest.hotbed

import com.allergokiller.android.domain.entity.Hotbed
import io.reactivex.Single



interface RestAddNewHotbed {
     class Body(
        val lat: Double,
        val lng: Double,
        val title: String,
        val body: String
    )
    fun addNewHotbed(hotbedBody: Body): Single<Hotbed>
}