package com.allergokiller.android

import android.app.Application
import android.preference.PreferenceManager
import com.github.terrakok.cicerone.Cicerone
import org.osmdroid.config.Configuration

class App : Application() {
    companion object {
        internal lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
    }
}