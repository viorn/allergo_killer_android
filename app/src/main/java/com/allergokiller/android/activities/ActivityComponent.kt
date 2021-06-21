package com.allergokiller.android.core

import android.app.Activity
import android.content.Context
import com.allergokiller.android.fragments.FragmentComponent
import com.allergokiller.android.fragments.FragmentModule
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
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

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent
}