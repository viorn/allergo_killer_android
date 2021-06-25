package com.allergokiller.android.core

import android.app.Activity
import android.content.Context
import com.allergokiller.core.IAppComponent
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule(val activity: Activity) {
    @Provides
    fun activity(): Activity {
        return activity
    }

    @Named("activity")
    @Provides
    fun context(): Context {
        return activity
    }
}

@Component(dependencies = [IAppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun ciceroneNavigatorHolder(): NavigatorHolder
    fun ciceroneRouter(): Router
}