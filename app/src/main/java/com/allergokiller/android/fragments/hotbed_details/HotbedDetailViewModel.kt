package com.allergokiller.android.fragments.hotbed_details

import com.allergokiller.android.core.actions.Action
import com.allergokiller.android.core.AStateViewModel

class HotbedDetailViewModel : AStateViewModel<HotbedDetailState, Action>() {


    fun init(hotbedId: Long) {

    }

    override fun initState() = HotbedDetailState()
}