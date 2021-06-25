package com.allergokiller.core

import com.allergokiller.android.core.ActivityComponent
import com.allergokiller.android.core.ActivityModule
import com.allergokiller.android.domain.gateway.IHotbedGateway
import com.allergokiller.android.domain.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.domain.usecases.hotbed.IFindHotbedByCircleInteractor
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface IAppComponent {
    fun ciceroneNavigatorHolder(): NavigatorHolder
    fun ciceroneRouter(): Router
    fun iAddHotbedInteractor(): IAddHotbedInteractor
    fun iFindHotbedByCircleInteractor(): IFindHotbedByCircleInteractor
    fun iHotbedGateway(): IHotbedGateway
}