package com.allergokiller.android.domain.usecases.hotbed

import com.allergokiller.android.domain.entity.Hotbed
import com.allergokiller.android.domain.entity.Point
import io.reactivex.Single

interface IFindHotbedByCircleInteractor {
    fun run(center: Point, radius: Double): Single<List<Hotbed>>
}