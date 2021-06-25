package com.allergokiller.android.app

import android.content.Context
import com.allergokiller.android.cicerone.CiceroneModule
import com.allergokiller.android.data.DataModule
import com.allergokiller.android.datasource.rest.RestModule
import com.allergokiller.android.domain.usecases.UseCaseModule
import com.allergokiller.core.IAppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.processors.PublishProcessor
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

    @Singleton
    @Provides
    fun appEventBus(): PublishProcessor<Any> {
        return PublishProcessor.create()
    }
}

@Singleton
@Component(modules = [AppModule::class, RestModule::class, DataModule::class, UseCaseModule::class, CiceroneModule::class])
interface AppComponent: IAppComponent {
}