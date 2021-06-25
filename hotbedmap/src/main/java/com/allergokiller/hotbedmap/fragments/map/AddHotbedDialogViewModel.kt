package com.allergokiller.hotbedmap.fragments.map

import android.net.Uri
import com.allergokiller.android.domain.entity.Point
import com.allergokiller.core.actions.Action
import com.allergokiller.core.StateViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddHotbedDialogViewModel @Inject constructor() : StateViewModel<AddHotbedDialogState, Action>() {
    private val compositeDisposable = CompositeDisposable()

    override fun initState() = AddHotbedDialogState()

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