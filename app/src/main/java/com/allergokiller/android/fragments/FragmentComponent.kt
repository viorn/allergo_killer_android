package com.allergokiller.android.fragments

import android.content.Context
import androidx.fragment.app.Fragment
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
}