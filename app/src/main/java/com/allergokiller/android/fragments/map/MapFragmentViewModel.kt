package com.allergokiller.android.fragments.map

import androidx.lifecycle.ViewModel
import com.allergokiller.android.App
import com.allergokiller.android.data.entity.Hotbed
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.data.gateway.IHotbedGateway
import com.allergokiller.android.events.ErrorEvent
import com.allergokiller.android.events.Event
import com.allergokiller.android.events.MessageEvent
import com.allergokiller.android.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.usecases.hotbed.IFindHotbedByCircleInteractor
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo

class MapFragmentViewModel(
    private val iAddHotbedInteractor: IAddHotbedInteractor = App.appComponent!!.addHotbedInteractor(),
    private val iFindHotbedByCircleInteractor: IFindHotbedByCircleInteractor = App.appComponent!!.findHotbedInteractor(),
    private val iHotbedGateway: IHotbedGateway = App.appComponent!!.hotbadGateway()
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val stateBehavior =
        BehaviorProcessor.createDefault<MapFragmentState>(MapFragmentState())
    val stateFlowable: Flowable<MapFragmentState> = stateBehavior.distinctUntilChanged()
    val state: MapFragmentState get() = stateBehavior.value!!

    private val eventsPublish = PublishProcessor.create<Event>()
    val eventsFlowable: Flowable<Event> = eventsPublish

    init {
        iHotbedGateway.getFlowLastSearchHotbedList().observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setHotbedsList(it)
            }.addTo(compositeDisposable)
    }

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
                    eventsPublish.onNext(ErrorEvent(error.message ?: "error", error))
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
                    eventsPublish.onNext(ErrorEvent(error.message ?: "error", error))
                } else {
                    eventsPublish.onNext(MessageEvent("Точка добавлена"))
                }
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}