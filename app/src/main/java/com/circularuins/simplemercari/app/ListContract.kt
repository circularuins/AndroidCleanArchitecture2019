package com.circularuins.simplemercari.app

import com.circularuins.simplemercari.app.viewdata.ListViewData

interface ListContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setList(items: List<ListViewData>)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun start(requestType: String)
    }
}