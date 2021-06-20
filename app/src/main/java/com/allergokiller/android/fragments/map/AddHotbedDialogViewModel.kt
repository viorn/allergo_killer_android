package com.allergokiller.android.fragments.map

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.allergokiller.android.data.entity.Point
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import java.util.concurrent.TimeUnit

class AddHotbedDialogViewModel() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val stateBehavior =
        BehaviorProcessor.createDefault<AddHotbedDialogState>(AddHotbedDialogState())
    val stateFlowable: Flowable<AddHotbedDialogState> = stateBehavior.distinctUntilChanged()
    val state: AddHotbedDialogState get() = stateBehavior.value!!

    fun init(point: Point) {
        if (stateBehavior.value!!.point == null || stateBehavior.value!!.point != point)
            stateBehavior.onNext(AddHotbedDialogState(point = point))
    }

    fun reset() {
        stateBehavior.onNext(AddHotbedDialogState())
    }

    fun setTitle(title: String) {
        stateBehavior.onNext(stateBehavior.value!!.copy(title = title))
    }

    fun setDescription(description: String) {
        stateBehavior.onNext(stateBehavior.value!!.copy(description = description))
    }

    fun addImage(uri: Uri) {
        stateBehavior.onNext(stateBehavior.value!!.copy(loading = true))
        //MOCK preloader
        Single.timer(5, TimeUnit.SECONDS)
            .doFinally {
                stateBehavior.onNext(stateBehavior.value!!.copy(loading = false))
            }
            .subscribe()
    }

    fun deleteImage(imageId: Long) {

    }

    val title: String get() = stateBehavior.value!!.title
    val description: String get() = stateBehavior.value!!.description

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}