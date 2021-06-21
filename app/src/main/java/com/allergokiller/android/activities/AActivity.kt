package com.allergokiller.android.activities

import androidx.appcompat.app.AppCompatActivity
import com.allergokiller.android.app.App
import com.allergokiller.android.core.ActivityModule

abstract class AActivity: AppCompatActivity() {
    val activityComponent get() = App.appComponent.activityComponent(ActivityModule(this))
}