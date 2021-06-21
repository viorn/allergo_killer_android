package com.allergokiller.android.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.allergokiller.android.fragments.map.MapFragment
import com.allergokiller.android.fragments.map.MapFragmentViewModel
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
    fun inject(fragment: MapFragment)
    fun inject(vm: MapFragmentViewModel)
}