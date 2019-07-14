package com.circularuins.simplemercari.di

import android.content.Context
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.app.main.MainContract
import com.circularuins.simplemercari.app.main.MainPresenter
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.domain.usecase.StartUseCase
import dagger.Module
import dagger.Provides

/**
 * メイン画面の依存性作成
 */
@Module
class MainModule(
    private val context: Context,
    private val view: MainContract.View,
    private val errorView: ApiErrorView
) {

    @Provides
    fun provideStartUseCase(masterRepository: MasterRepository): StartUseCase =
        StartUseCase(masterRepository)

    @Provides
    fun provideMainPresenter(useCase: StartUseCase): MainContract.Presenter =
        MainPresenter(context, view, errorView, useCase)
}