package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.infra.SchedulerProvider
import com.circularuins.simplemercari.infra.repository.ItemRepositoryImpl
import com.circularuins.simplemercari.infra.repository.MasterRepositoryImpl
import com.circularuins.simplemercari.infra.rest.ApiClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(ApiClient.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    fun provideMasterRepository(client: ApiClient,
        schedulerProvider: SchedulerProvider): MasterRepository =
        MasterRepositoryImpl(client, schedulerProvider.io, schedulerProvider.ui)

    @Provides
    fun provideItemRepository(client: ApiClient,
        schedulerProvider: SchedulerProvider): ItemRepository =
        ItemRepositoryImpl(client, schedulerProvider.io, schedulerProvider.ui)
}