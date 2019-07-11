package com.circularuins.simplemercari.app

import android.annotation.SuppressLint
import com.circularuins.simplemercari.app.mapper.convert
import com.circularuins.simplemercari.domain.usecase.ListUseCase

class ListPresenter(
    private val view: ListContract.View,
    private val useCase: ListUseCase
) : ListContract.Presenter {

    @SuppressLint("CheckResult")
    override fun start(requestType: String) {
        view.showProgress()

        useCase.fetchListData(requestType)
            .doAfterTerminate {
                view.hideProgress()
            }
            .map { it.map { item -> item.convert() } } // ViewDataへ変換
            .subscribe({
                view.setList(it)
            }, {
                view.showError(it)
            })
    }
}