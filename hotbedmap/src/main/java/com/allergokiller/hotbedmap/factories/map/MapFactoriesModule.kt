package com.allergokiller.hotbedmap.factories.map

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapFactoriesModule {
    @Provides
    fun hotbedOverlayBuilder(): IHotbedOSMOverlayFactory {
        return HotbedOSMOverlayFactory()
    }
}