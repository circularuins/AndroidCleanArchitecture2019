package com.circularuins.simplemercari.app

import com.circularuins.simplemercari.domain.model.Master
import com.uber.autodispose.ScopeProvider

interface MainContract {

    interface View : ScopeProvider {
        fun initToolBar()
        fun showProgress()
        fun hideProgress()
        fun setTab(masters: List<Master>)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun start()
    }
}