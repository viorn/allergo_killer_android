package com.allergokiller.android

import android.app.Application
import android.preference.PreferenceManager
import org.osmdroid.config.Configuration

class App : Application() {

    companion object {
        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
    }
}