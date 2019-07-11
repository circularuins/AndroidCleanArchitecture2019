package com.circularuins.simplemercari.app

import com.circularuins.simplemercari.app.viewdata.Item

interface ListContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setList(items: List<Item>)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun start(requestType: String)
    }
}