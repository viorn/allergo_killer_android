package com.allergokiller.android.domain.usecases

import com.allergokiller.android.domain.gateway.IHotbedGateway
import com.allergokiller.android.domain.usecases.hotbed.AddHotbedInteractor
import com.allergokiller.android.domain.usecases.hotbed.FindHotbedByCircleInteractor
import com.allergokiller.android.domain.usecases.hotbed.IAddHotbedInteractor
import com.allergokiller.android.domain.usecases.hotbed.IFindHotbedByCircleInteractor
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    @Named("usecase")
    fun usecaseScheduler(): Scheduler = Schedulers.single();


    @Provides
    fun findHotbedByCircleInteractor(@Named("usecase") scheduler: Scheduler, iHotbedGateway: IHotbedGateway): IFindHotbedByCircleInteractor {
        return FindHotbedByCircleInteractor(scheduler, iHotbedGateway)
    }

    @Provides
    fun addHotbedInteractor(@Named("usecase") scheduler: Scheduler,iHotbedGateway: IHotbedGateway): IAddHotbedInteractor {
        return AddHotbedInteractor(scheduler, iHotbedGateway)
    }
}