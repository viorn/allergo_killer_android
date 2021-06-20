package com.allergokiller.android.fragments.hotbed_details

import com.allergokiller.android.gactions.Action
import com.allergokiller.android.core.AViewModel

class HotbedDetailViewModel : AViewModel<HotbedDetailState, Action>() {


    fun init(hotbedId: Long) {

    }

    override fun initState() = HotbedDetailState()
}