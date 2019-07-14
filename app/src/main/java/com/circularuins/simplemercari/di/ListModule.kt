package com.circularuins.simplemercari.di

import android.content.Context
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.app.list.ItemListContract
import com.circularuins.simplemercari.app.list.ItemListPresenter
import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.usecase.ListUseCase
import dagger.Module
import dagger.Provides

/**
 * リスト画面の依存性作成
 */
@Module
class ListModule(
    private val context: Context,
    private val view: ItemListContract.View,
    private val errorView: ApiErrorView
) {

    @Provides
    fun provideListUseCase(itemRepository: ItemRepository): ListUseCase =
        ListUseCase(itemRepository)

    @Provides
    fun provideListPresenter(useCase: ListUseCase): ItemListContract.Presenter =
        ItemListPresenter(context, view, errorView, useCase)
}