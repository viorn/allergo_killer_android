package com.allergokiller.android.fragments.hotbed_details

import com.allergokiller.android.core.actions.Action
import com.allergokiller.android.core.AStateViewModel
import javax.inject.Inject

class HotbedDetailViewModel @Inject constructor() : AStateViewModel<HotbedDetailState, Action>() {


    fun init(hotbedId: Long) {

    }

    override fun initState() = HotbedDetailState()
}