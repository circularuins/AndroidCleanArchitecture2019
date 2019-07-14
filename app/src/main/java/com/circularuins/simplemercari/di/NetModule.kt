package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.infra.common.SchedulerProvider
import com.circularuins.simplemercari.infra.repository.ItemRepositoryImpl
import com.circularuins.simplemercari.infra.repository.MasterRepositoryImpl
import com.circularuins.simplemercari.infra.rest.ApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * ネットワーク通信関係の依存性を作成
 */
@Module
class NetModule {

    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Provides
    fun provideMasterRepository(client: ApiClient,
        schedulerProvider: SchedulerProvider
    ): MasterRepository =
        MasterRepositoryImpl(client, schedulerProvider.io, schedulerProvider.ui)

    @Provides
    fun provideItemRepository(client: ApiClient,
        schedulerProvider: SchedulerProvider
    ): ItemRepository =
        ItemRepositoryImpl(client, schedulerProvider.io, schedulerProvider.ui)
}