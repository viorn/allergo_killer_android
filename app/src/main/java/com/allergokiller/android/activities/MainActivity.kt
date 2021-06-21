package com.allergokiller.android.activities

import android.os.Bundle
import com.allergokiller.android.R
import com.allergokiller.android.app.App
import com.allergokiller.android.cicerone.Screens
import com.github.terrakok.cicerone.androidx.AppNavigator


class MainActivity : AActivity() {
    private val navigator by lazy { AppNavigator(this, R.id.main_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null) {
            App.appComponent.ciceroneRouter().newRootScreen(Screens.Main())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.appComponent.ciceroneNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        App.appComponent.ciceroneNavigatorHolder().removeNavigator()
        super.onPause()
    }
}