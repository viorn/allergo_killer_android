package com.allergokiller.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.allergokiller.core.actions.Action
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

/**
 * AStateViewModel is a viewmodel abstraction class that implements a single state and channel for one-time message approach
 */
abstract class StateViewModel<S, A: Action> : ViewModel() {
    abstract fun initState(): S

    protected val stateBehavior =
        BehaviorProcessor.createDefault<S>(initState())
    val stateLiveData: LiveData<S> = LiveDataReactiveStreams.fromPublisher(stateBehavior.distinctUntilChanged())
    val state: S get() = stateBehavior.value!!
    val stateFlowable: Flowable<S> = stateBehavior.distinctUntilChanged()

    protected val actionPublish = PublishProcessor.create<A>()
    val actionsFlowable: Flowable<A> = actionPublish
}