package com.allergokiller.hotbedmap.fragments.map

import com.allergokiller.android.domain.gateway.IHotbedGateway
import com.allergokiller.android.domain.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.domain.usecases.hotbed.IFindHotbedByCircleInteractor
import com.allergokiller.core.IAppComponent
import com.allergokiller.hotbedmap.factories.map.HotbedOSMOverlayFactory
import com.allergokiller.hotbedmap.factories.map.IHotbedOSMOverlayFactory
import com.allergokiller.hotbedmap.factories.map.MapFactoriesModule
import com.allergokiller.hotbedmap.fragments.FragmentComponent
import dagger.Component

@Component(dependencies = [IAppComponent::class, FragmentComponent::class], modules = [MapFactoriesModule::class])
interface MapFragmentComponent {
    fun hotbedOverlayFactory() : IHotbedOSMOverlayFactory
    fun iAddHotbedInteractor(): IAddHotbedInteractor
    fun iFindHotbedByCircleInteractor(): IFindHotbedByCircleInteractor
    fun iHotbedGateway(): IHotbedGateway
}