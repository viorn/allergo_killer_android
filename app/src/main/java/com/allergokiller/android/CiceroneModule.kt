package com.allergokiller.android

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class CiceroneModule {
    @Provides
    fun cicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @Provides
    fun router(cicerone: Cicerone<Router>): Router{
        return cicerone.router
    }

    @Provides
    fun navigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}