package com.allergokiller.android.data

import com.allergokiller.android.data.datasource.rest.hotbed.RestAddNewHotbed
import com.allergokiller.android.data.datasource.rest.hotbed.RestGetHotbedByCircle
import com.allergokiller.android.data.gateway.HotbedGateway
import com.allergokiller.android.data.gateway.IHotbedGateway
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    @Named("data")
    fun dataScheduler(): Scheduler = Schedulers.single();

    @Provides
    @Singleton
    fun hotbedGateway(
        @Named("data") scheduler: Scheduler,
        getRestGetHotbedByCircle: RestGetHotbedByCircle,
        addNewHotbed: RestAddNewHotbed
    ): IHotbedGateway {
        return HotbedGateway(scheduler, getRestGetHotbedByCircle, addNewHotbed)
    }
}