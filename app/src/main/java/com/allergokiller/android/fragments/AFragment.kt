package com.allergokiller.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.allergokiller.android.activities.AActivity
import com.allergokiller.android.fragments.FragmentModule
import io.reactivex.disposables.CompositeDisposable

abstract class AFragment: Fragment() {
    val fragmentComponent get() = (requireActivity() as AActivity).activityComponent.fragmentComponent(
        FragmentModule(this)
    )
    val createViewCompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        createViewCompositeDisposable.clear()
        super.onDestroyView()
    }
}