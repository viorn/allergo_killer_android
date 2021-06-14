package com.allergokiller.android.datasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class SingleViewModelFactory<S: ViewModel>(private val singleClass: Class<S>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == singleClass) {
            return bind() as T
        } else {
            throw Throwable("factory not implementation")
        }
    }
    abstract fun bind(): S
}