package com.allergokiller.core

import androidx.appcompat.app.AppCompatActivity
import com.allergokiller.android.core.DaggerActivityComponent
import io.reactivex.disposables.CompositeDisposable

abstract class RxActivity: AppCompatActivity() {
    val appComponent get() = (application as AppComponentProvider).appComponent
    val activityComponent by lazy { DaggerActivityComponent.builder().iAppComponent((appComponent)).build() }

    val createCompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        createCompositeDisposable.clear()
        super.onDestroy()
    }
}