package com.circularuins.simplemercari.app.main

import com.circularuins.simplemercari.domain.model.Master
import com.uber.autodispose.ScopeProvider

interface MainContract {

    interface View : ScopeProvider {
        fun initToolBar()
        fun showProgress()
        fun hideProgress()
        fun setTab(masters: List<Master>)
    }

    interface Presenter {
        fun start()
    }
}