package com.allergokiller.android.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.allergokiller.android.core.ViewModelProviderFactory
import com.allergokiller.android.factories.map.IHotbedOSMOverlayFactory
import com.allergokiller.android.fragments.map.MapFragment
import com.allergokiller.android.fragments.map.MapFragmentViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
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

@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun viewModelProviderFactory(): ViewModelProviderFactory
    fun hotbedOverlayFactory(): IHotbedOSMOverlayFactory
    fun ciceroneNavigatorHolder(): NavigatorHolder
    fun ciceroneRouter(): Router
}