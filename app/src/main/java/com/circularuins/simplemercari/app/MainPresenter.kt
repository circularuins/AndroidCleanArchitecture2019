package com.circularuins.simplemercari.app

import android.annotation.SuppressLint
import com.circularuins.simplemercari.domain.usecase.StartUseCase

class MainPresenter(
    private val view: MainContract.View,
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
            .subscribe({
                view.setTab(it)
            }, {
                view.showError(it)
            })
    }
}