package com.allergokiller.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class RxDialogFragment: DialogFragment() {
    val createViewCompositeDisposable = CompositeDisposable()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        createViewCompositeDisposable.clear()
        super.onDestroyView()
    }
}