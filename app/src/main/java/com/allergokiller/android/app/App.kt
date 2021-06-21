package com.allergokiller.android.app

import android.app.Application
import android.preference.PreferenceManager
import com.allergokiller.android.core.AppComponent
import com.allergokiller.android.core.AppModule
import com.allergokiller.android.core.DaggerAppComponent
import org.osmdroid.config.Configuration

class App : Application() {
    companion object {
        internal lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
    }
}