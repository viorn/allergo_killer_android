package com.allergokiller.android.fragments.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
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

    private val _state = BehaviorProcessor.createDefault<MapFragmentState>(MapFragmentState())
    val state: LiveData<MapFragmentState> = LiveDataReactiveStreams.fromPublisher(_state.distinctUntilChanged())

    private val _events = PublishProcessor.create<Event>()
    val events: LiveData<Event> = LiveDataReactiveStreams.fromPublisher(_events)

    init {
        iHotbedGateway.getFlowLastSearchHotbedList().observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setHotbedsList(it)
            }.addTo(compositeDisposable)
    }

    private fun setHotbedsList(list: List<Hotbed>) {
        _state.onNext(
            _state.value?.copy(
                hotbedList = list
            )
        )
    }


    fun findByCenter(lat: Double, lng: Double) {
        iFindHotbedByCircleInteractor.run(
            Point(
                lat = lat,
                lng = lng
            ),
            10000.0
        ).subscribe { result, error ->
            if (error!=null) {
                error.printStackTrace()
                _events.onNext(ErrorEvent(error.message?:"error", error))
            }
        }.addTo(compositeDisposable)
    }

    fun addHotbed(title: String, body: String, lat: Double, lng: Double) {
        iAddHotbedInteractor.run(
            lat,
            lng,
            title,
            body
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                if (error!=null) {
                    error.printStackTrace()
                    _events.onNext(ErrorEvent(error.message?:"error", error))
                } else {
                    _events.onNext(MessageEvent("Точка добавлена"))
                }
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}