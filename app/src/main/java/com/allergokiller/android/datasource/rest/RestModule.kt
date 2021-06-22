package com.allergokiller.android.datasource.rest

import com.allergokiller.android.domain.datasource.rest.hotbed.RestAddNewHotbed
import com.allergokiller.android.domain.datasource.rest.hotbed.RestGetHotbedByCircle
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RestModule {
    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun converterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .baseUrl("http://192.168.1.42:8080")
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun restService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }

    @Provides
    fun restGetHotbedByCircle(restService: RestService): RestGetHotbedByCircle {
        return restService
    }

    @Provides
    fun restAddNewHotbed(restService: RestService): RestAddNewHotbed {
        return restService
    }
}