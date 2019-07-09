package com.circularuins.simplemercari.app

import com.circularuins.simplemercari.domain.model.Master

interface MainContract {

    interface View {
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