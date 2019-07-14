package com.circularuins.simplemercari.app.common

import android.content.Context
import com.circularuins.simplemercari.R
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import java.io.IOException

abstract class BaseApiSingleObserver<T>(
    private val apiErrorView: ApiErrorView,
    private val context: Context
) : DisposableSingleObserver<T>() {

    override fun onError(e: Throwable) {
        // ネットワークエラー
        if (e is IOException) {
            apiErrorView.showNetworkError()
            return
        }
        // サーバーエラー
        if (e is HttpException) {
            val statusCode = e.code()
            val errorBody = e.response()?.errorBody() ?: context.getString(R.string.message_error_server)
            apiErrorView.showError("$errorBody : $statusCode")
            return
        }
        // NullPointerException等
        apiErrorView.showError(context.getString(R.string.message_error_unexpected))
    }
}