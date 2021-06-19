package com.allergokiller.android.fragments.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allergokiller.android.App
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.usecases.hotbed.IAddHotbedInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.addTo
import java.util.*

class AddHotbedDialogViewModel() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _state =
        BehaviorProcessor.createDefault<AddHotbedDialogState>(AddHotbedDialogState())
    val state: LiveData<AddHotbedDialogState> =
        LiveDataReactiveStreams.fromPublisher(_state.distinctUntilChanged())

    fun init(point: Point) {
        if (state.value!!.point != point)
            _state.onNext(AddHotbedDialogState(point = point))
    }

    fun reset() {
        _state.onNext(AddHotbedDialogState())
    }

    fun setTitle(title: String) {
        _state.onNext(_state.value!!.copy(title = title))
    }

    fun setDescription(description: String) {
        _state.onNext(_state.value!!.copy(description = description))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}