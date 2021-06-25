package com.allergokiller.hotbedmap.fragments.hotbed_details

import com.allergokiller.core.actions.Action
import com.allergokiller.core.StateViewModel
import javax.inject.Inject

class HotbedDetailViewModel @Inject constructor() : StateViewModel<HotbedDetailState, Action>() {


    fun init(hotbedId: Long) {

    }

    override fun initState() = HotbedDetailState()
}