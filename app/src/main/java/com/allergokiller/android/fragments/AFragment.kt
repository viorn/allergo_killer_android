package com.allergokiller.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.allergokiller.android.activities.AActivity
import com.allergokiller.android.app.App
import com.allergokiller.android.core.ViewModelProviderFactory
import com.allergokiller.android.fragments.FragmentModule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class AFragment: Fragment() {
    val fragmentComponent get() = (requireActivity() as AActivity).activityComponent.fragmentComponent(
        FragmentModule(this)
    )
    val createViewCompositeDisposable = CompositeDisposable()

    val viewModelProviderFactory: ViewModelProviderFactory get() = App.appComponent.viewModelProviderFactory()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        createViewCompositeDisposable.clear()
        super.onDestroyView()
    }
}