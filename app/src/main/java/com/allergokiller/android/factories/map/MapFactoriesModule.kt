package com.allergokiller.android.factories.map

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapFactoriesModule {
    @Singleton
    @Provides
    fun hotbedOverlayBuilder(): IHotbedOSMOverlayFactory {
        return HotbedOSMOverlayFactory()
    }
}