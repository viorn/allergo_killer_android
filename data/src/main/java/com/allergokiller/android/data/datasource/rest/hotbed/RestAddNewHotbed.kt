package com.allergokiller.android.data.datasource.rest.hotbed

import com.allergokiller.android.data.entity.Hotbed
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