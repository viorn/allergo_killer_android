package com.allergokiller.android.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allergokiller.android.fragments.hotbed_details.HotbedDetailViewModel
import com.allergokiller.android.fragments.map.AddHotbedDialogViewModel
import com.allergokiller.android.fragments.map.MapFragmentViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(MapFragmentViewModel::class)
    internal abstract fun mapFragmentViewModel(mapFragmentViewModel: MapFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddHotbedDialogViewModel::class)
    internal abstract fun addHotbedDialogViewModel(addHotbedDialogViewModel: AddHotbedDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HotbedDetailViewModel::class)
    internal abstract fun hotbedDetailViewModel(hotbedDetailViewModel: HotbedDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}