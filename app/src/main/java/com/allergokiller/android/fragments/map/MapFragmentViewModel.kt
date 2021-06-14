package com.allergokiller.android.fragments.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allergokiller.android.App
import com.allergokiller.android.data.entity.Hotbed
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.data.gateway.IHotbedGateway
import com.allergokiller.android.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.usecases.hotbed.IFindHotbedByCircleInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MapFragmentViewModel(
    private val iAddHotbedInteractor: IAddHotbedInteractor = App.appComponent!!.addHotbedInteractor(),
    private val iFindHotbedByCircleInteractor: IFindHotbedByCircleInteractor = App.appComponent!!.findHotbedInteractor(),
    private val iHotbedGateway: IHotbedGateway = App.appComponent!!.hotbadGateway()
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _hotbedsList = MutableLiveData<List<Hotbed>>(emptyList())
    val hotbedsList: LiveData<List<Hotbed>> get() = _hotbedsList

    init {
        iHotbedGateway.getFlowLastSearchHotbedList().observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _hotbedsList.value = it
            }.addTo(compositeDisposable)
    }


    fun findByCenter(lat: Double, lng: Double) {
        iFindHotbedByCircleInteractor.run(
            Point(
                lat = lat,
                lng = lng
            ),
            10000.0
        ).subscribe { result, error ->
            error?.printStackTrace()
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

            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}