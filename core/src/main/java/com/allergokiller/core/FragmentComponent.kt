package com.allergokiller.hotbedmap.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.allergokiller.android.core.ActivityComponent
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FragmentModule(val fragment: Fragment) {
    @Provides
    fun fragment(): Fragment {
        return fragment
    }

    @Named("fragment")
    @Provides
    fun context(): Context {
        return fragment.requireContext()
    }
}

@Component(dependencies = [ActivityComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun ciceroneNavigatorHolder(): NavigatorHolder
    fun ciceroneRouter(): Router
}