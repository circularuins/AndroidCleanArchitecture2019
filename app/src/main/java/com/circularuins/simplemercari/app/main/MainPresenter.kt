package com.circularuins.simplemercari.app.main

import android.annotation.SuppressLint
import android.content.Context
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.app.common.BaseApiSingleObserver
import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.usecase.StartUseCase
import com.uber.autodispose.autoDisposable

class MainPresenter(
    private val context: Context,
    private val view: MainContract.View,
    private val errorView: ApiErrorView,
    private val useCase: StartUseCase
) : MainContract.Presenter {

    @SuppressLint("CheckResult")
    override fun start() {
        view.initToolBar()
        view.showProgress()

        useCase.fetchMasterData()
            .doAfterTerminate {
                view.hideProgress()
            }
            .autoDisposable(view)
            .subscribe(object : BaseApiSingleObserver<List<Master>>(errorView, context) {
                override fun onSuccess(it: List<Master>) {
                    view.setTab(it)
                }
            })
    }
}