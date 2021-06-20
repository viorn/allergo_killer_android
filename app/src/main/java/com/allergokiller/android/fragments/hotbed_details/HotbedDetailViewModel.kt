package com.allergokiller.android.fragments.hotbed_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.allergokiller.android.events.Event
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

class HotbedDetailViewModel : ViewModel() {
    private val stateBehavior = BehaviorProcessor.createDefault<HotbedDetailState>(HotbedDetailState())
    val stateFlowable: Flowable<HotbedDetailState> = stateBehavior.distinctUntilChanged()
    val state: HotbedDetailState get() = stateBehavior.value!!

    private val _events = PublishProcessor.create<Event>()
    val events: Flowable<Event> = _events


    fun init(hotbedId: Long) {

    }
}