package com.allergokiller.android

import com.allergokiller.android.data.DataModule
import com.allergokiller.android.data.gateway.IHotbedGateway
import com.allergokiller.android.datasource.rest.RestModule
import com.allergokiller.android.factories.map.IHotbedOSMOverlayFactory
import com.allergokiller.android.factories.map.MapFactoriesModule
import com.allergokiller.android.usecases.UseCaseModule
import com.allergokiller.android.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.usecases.hotbed.IFindHotbedByCircleInteractor
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import okhttp3.Route
import javax.inject.Singleton

@Singleton
@Component(modules = [RestModule::class, DataModule::class, UseCaseModule::class, MapFactoriesModule::class, CiceroneModule::class])
interface AppComponent {
    fun addHotbedInteractor(): IAddHotbedInteractor
    fun findHotbedInteractor(): IFindHotbedByCircleInteractor
    fun hotbadGateway(): IHotbedGateway

    fun ciceroneNavigatorHolder(): NavigatorHolder
    fun ciceroneRouter(): Router

    fun hotbedOverlayFactory(): IHotbedOSMOverlayFactory
}