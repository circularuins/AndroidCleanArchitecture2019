package com.circularuins.simplemercari.app.list

import com.circularuins.simplemercari.app.viewdata.ListViewData
import com.uber.autodispose.ScopeProvider

interface ListContract {

    interface View : ScopeProvider {
        fun showProgress()
        fun hideProgress()
        fun setList(items: List<ListViewData>)
    }

    interface Presenter {
        fun start(requestType: String)
    }
}