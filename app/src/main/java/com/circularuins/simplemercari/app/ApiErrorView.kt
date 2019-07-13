package com.circularuins.simplemercari.app

interface ApiErrorView {
    fun showNetworkError()
    fun showError(error: String)
}