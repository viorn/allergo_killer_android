package com.allergokiller.android.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

inline fun <T> observer(crossinline onChangedListener: Observer<T>.(T)->Unit) = object : Observer<T> {
    override fun onChanged(t: T) {
        this.onChangedListener(t)
    }
}

fun <X, Y> LiveData<X>.map(mapFunction: (X)->Y): LiveData<Y> = Transformations.map(this, mapFunction)

fun <X> LiveData<X>.distinctUntilChanged(): LiveData<X> = Transformations.distinctUntilChanged(this)

fun <X, Y> LiveData<X>.switchMap(mapFunction: (X)->LiveData<Y>): LiveData<Y> = Transformations.switchMap(this, mapFunction)