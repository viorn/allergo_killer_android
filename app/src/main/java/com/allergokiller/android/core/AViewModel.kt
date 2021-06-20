package com.allergokiller.android.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.allergokiller.android.gactions.Action
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

abstract class AViewModel<S, A: Action> : ViewModel() {
    abstract fun initState(): S

    protected val stateBehavior =
        BehaviorProcessor.createDefault<S>(initState())
    val stateLiveData: LiveData<S> = LiveDataReactiveStreams.fromPublisher(stateBehavior.distinctUntilChanged())
    val state: S get() = stateBehavior.value!!
    val stateFlowable: Flowable<S> = stateBehavior

    protected val actionPublish = PublishProcessor.create<A>()
    val actionsFlowable: Flowable<A> = actionPublish
}