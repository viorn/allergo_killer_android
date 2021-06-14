package com.allergokiller.android.tools

import androidx.lifecycle.Observer

inline fun <T> observer(crossinline onChangedListener: Observer<T>.(T)->Unit) = object : Observer<T> {
    override fun onChanged(t: T) {
        this.onChangedListener(t)
    }
}