package com.allergokiller.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.allergokiller.hotbedmap.fragments.DaggerFragmentComponent
import io.reactivex.disposables.CompositeDisposable

abstract class RxFragment: Fragment() {
    val appComponent get() = (activity as RxActivity).appComponent
    val activityComponent get() = (activity as RxActivity).activityComponent
    val fragmentComponent by lazy { DaggerFragmentComponent.builder().activityComponent(activityComponent).build() }
    val createViewCompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        createViewCompositeDisposable.clear()
        super.onDestroyView()
    }
}