package com.circularuins.simplemercari.app.common

interface ApiErrorView {
    fun showNetworkError()
    fun showError(error: String)
}