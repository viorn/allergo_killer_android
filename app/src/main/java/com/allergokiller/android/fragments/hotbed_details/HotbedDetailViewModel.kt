package com.allergokiller.android.fragments.hotbed_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.allergokiller.android.events.Event
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

class HotbedDetailViewModel : ViewModel() {
    private val _state = BehaviorProcessor.createDefault<HotbedDetailState>(HotbedDetailState())
    val state: LiveData<HotbedDetailState> = LiveDataReactiveStreams.fromPublisher(_state.distinctUntilChanged())

    private val _events = PublishProcessor.create<Event>()
    val events: LiveData<Event> = LiveDataReactiveStreams.fromPublisher(_events)


    fun init(hotbedId: Long) {

    }
}