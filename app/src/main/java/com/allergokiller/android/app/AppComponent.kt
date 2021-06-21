package com.allergokiller.android.core

import android.content.Context
import com.allergokiller.android.app.App
import com.allergokiller.android.cicerone.CiceroneModule
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
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun context(): Context {
        return app
    }
}

@Singleton
@Component(modules = [AppModule::class, RestModule::class, DataModule::class, UseCaseModule::class, MapFactoriesModule::class, CiceroneModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}