package com.allergokiller.android.fragments.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allergokiller.android.App
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.usecases.hotbed.IAddHotbedInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class AddHotbedDialogViewModel() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _pointLiveData = MutableLiveData<Point>()
    val pointLiveDate: LiveData<Point> get() = _pointLiveData

    val titleLiveData = MutableLiveData<String>("")

    val bodyLiveData = MutableLiveData<String>("")

    fun init(point: Point) {
        titleLiveData.value = ""
        bodyLiveData.value = ""
        _pointLiveData.value = point
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}