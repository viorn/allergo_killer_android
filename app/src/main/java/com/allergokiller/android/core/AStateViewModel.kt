package com.allergokiller.android.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.allergokiller.android.core.actions.Action
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

/**
 * AStateViewModel is a viewmodel abstraction class that implements a single state and channel for one-time message approach
 */
abstract class AStateViewModel<S, A: Action> : ViewModel() {
    abstract fun initState(): S

    protected val stateBehavior =
        BehaviorProcessor.createDefault<S>(initState())
    val stateLiveData: LiveData<S> = LiveDataReactiveStreams.fromPublisher(stateBehavior.distinctUntilChanged())
    val state: S get() = stateBehavior.value!!
    val stateFlowable: Flowable<S> = stateBehavior

    protected val actionPublish = PublishProcessor.create<A>()
    val actionsFlowable: Flowable<A> = actionPublish
}