package com.allergokiller.android.app

import android.app.Application
import android.preference.PreferenceManager
import com.allergokiller.core.AppComponentProvider
import com.allergokiller.core.IAppComponent
import org.osmdroid.config.Configuration

class App : Application(), AppComponentProvider {
    override lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
    }
}