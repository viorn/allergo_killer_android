package com.allergokiller.hotbedmap.fragments.map

import com.allergokiller.android.domain.entity.Point
import com.allergokiller.android.domain.gateway.IHotbedGateway
import com.allergokiller.core.actions.ErrorAction
import com.allergokiller.core.actions.Action
import com.allergokiller.core.actions.MessageAction
import com.allergokiller.core.StateViewModel
import com.allergokiller.android.domain.entity.Hotbed
import com.allergokiller.android.domain.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.domain.usecases.hotbed.IFindHotbedByCircleInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MapFragmentViewModel @Inject constructor (
    private val iAddHotbedInteractor: IAddHotbedInteractor,
    private val iFindHotbedByCircleInteractor: IFindHotbedByCircleInteractor,
    private val iHotbedGateway: IHotbedGateway
) : StateViewModel<MapFragmentState, Action>() {
    private val compositeDisposable = CompositeDisposable()

    init {
        iHotbedGateway.getFlowLastSearchHotbedList().observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setHotbedsList(it)
            }.addTo(compositeDisposable)
    }


    override fun initState() = MapFragmentState()

    private fun setHotbedsList(list: List<Hotbed>) {
        stateBehavior.onNext(
            stateBehavior.value?.copy(
                hotbedList = list
            )
        )
    }

    fun findByCenter(lat: Double, lng: Double) {
        stateBehavior.onNext(stateBehavior.value!!.copy(loading = true))
        iFindHotbedByCircleInteractor.run(
            Point(
                lat = lat,
                lng = lng
            ),
            10000.0
        ).observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                stateBehavior.onNext(stateBehavior.value!!.copy(loading = false))
            }.subscribe { result, error ->
                if (error != null) {
                    error.printStackTrace()
                    actionPublish.onNext(ErrorAction(error.message ?: "error", error))
                }
            }.addTo(compositeDisposable)
    }

    fun addHotbed(title: String, body: String, lat: Double, lng: Double) {
        stateBehavior.onNext(stateBehavior.value!!.copy(loading = true))
        iAddHotbedInteractor.run(
            lat,
            lng,
            title,
            body
        ).observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                stateBehavior.onNext(stateBehavior.value!!.copy(loading = false))
            }.subscribe { result, error ->
                if (error != null) {
                    error.printStackTrace()
                    actionPublish.onNext(ErrorAction(error.message ?: "error", error))
                } else {
                    actionPublish.onNext(MessageAction("?????????? ??????????????????"))
                }
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}