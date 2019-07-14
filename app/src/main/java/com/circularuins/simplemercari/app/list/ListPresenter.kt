package com.circularuins.simplemercari.app.list

import android.annotation.SuppressLint
import android.content.Context
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.app.common.BaseApiSingleObserver
import com.circularuins.simplemercari.app.mapper.convert
import com.circularuins.simplemercari.app.viewdata.Item
import com.circularuins.simplemercari.domain.usecase.ListUseCase
import com.uber.autodispose.autoDisposable

class ListPresenter(
    private val context: Context,
    private val view: ListContract.View,
    private val errorView: ApiErrorView,
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
            .autoDisposable(view)
            .subscribe(object : BaseApiSingleObserver<List<Item>>(errorView, context) {
                override fun onSuccess(it: List<Item>) {
                    view.setList(it)
                }
            })
    }
}