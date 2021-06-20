package com.allergokiller.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class ADialogFragment: DialogFragment() {
    val viewCompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        viewCompositeDisposable.clear()
        super.onDestroyView()
    }
}