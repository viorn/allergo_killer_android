package com.allergokiller.android.app

import android.content.Context
import com.allergokiller.android.cicerone.CiceroneModule
import com.allergokiller.android.core.ActivityComponent
import com.allergokiller.android.core.ActivityModule
import com.allergokiller.android.core.ViewModelFactoryModule
import com.allergokiller.android.data.DataModule
import com.allergokiller.android.datasource.rest.RestModule
import com.allergokiller.android.factories.map.MapFactoriesModule
import com.allergokiller.android.domain.usecases.UseCaseModule
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