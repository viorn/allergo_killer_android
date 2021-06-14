package com.allergokiller.android.usecases.hotbed

import com.allergokiller.android.data.entity.Hotbed
import com.allergokiller.android.data.entity.Point
import io.reactivex.Single

interface IFindHotbedByCircleInteractor {
    fun run(center: Point, radius: Double): Single<List<Hotbed>>
}