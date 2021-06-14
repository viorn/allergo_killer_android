package com.allergokiller.android

import com.allergokiller.android.data.DataModule
import com.allergokiller.android.data.gateway.IHotbedGateway
import com.allergokiller.android.datasource.rest.RestModule
import com.allergokiller.android.usecases.UseCaseModule
import com.allergokiller.android.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.usecases.hotbed.IFindHotbedByCircleInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RestModule::class, DataModule::class, UseCaseModule::class])
interface AppComponent {
    fun addHotbedInteractor(): IAddHotbedInteractor
    fun findHotbedInteractor(): IFindHotbedByCircleInteractor
    fun hotbadGateway(): IHotbedGateway
}