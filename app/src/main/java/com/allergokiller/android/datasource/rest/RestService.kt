package com.allergokiller.android.datasource.rest

import com.allergokiller.android.data.datasource.rest.hotbed.RestAddNewHotbed
import com.allergokiller.android.data.datasource.rest.hotbed.RestGetHotbedByCircle
import com.allergokiller.android.domain.entity.Hotbed
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestService : RestGetHotbedByCircle, RestAddNewHotbed {
    @GET("hotbed")
    override fun getHotbedByCircle(@Query("lat") lat: Double, @Query("lng") lng: Double): Single<List<Hotbed>>

    @POST("hotbed")
    override fun addNewHotbed(@Body hotbedBody: RestAddNewHotbed.Body): Single<Hotbed>
}