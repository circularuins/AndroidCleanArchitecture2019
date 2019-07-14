package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.infra.repository.ItemRepositoryImpl
import com.circularuins.simplemercari.infra.repository.MasterRepositoryImpl
import com.circularuins.simplemercari.infra.repository.TestSchedulerProvider
import com.circularuins.simplemercari.infra.rest.ApiClient
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TestModule {

    @Singleton
    @Provides
    fun provideMockWebServer(): MockWebServer = MockWebServer()

    @Singleton
    @Provides
    fun provideRetrofit(server: MockWebServer): Retrofit {
        val url = server.url("/")

        return Retrofit.Builder()
            .baseUrl(url.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Singleton
    @Provides
    fun provideSchedulerProvider(): TestSchedulerProvider = TestSchedulerProvider()

    @Provides
    fun provideMasterRepository(client: ApiClient,
        schedulerProvider: TestSchedulerProvider
    ): MasterRepository =
        MasterRepositoryImpl(client, schedulerProvider.io(), schedulerProvider.ui())

    @Provides
    fun provideItemRepository(client: ApiClient,
        schedulerProvider: TestSchedulerProvider
    ): ItemRepository =
        ItemRepositoryImpl(client, schedulerProvider.io(), schedulerProvider.ui())
}