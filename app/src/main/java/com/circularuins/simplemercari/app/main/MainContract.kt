package com.circularuins.simplemercari.app.main

import com.circularuins.simplemercari.domain.model.Master
import com.uber.autodispose.ScopeProvider

/**
 * メイン画面のView/Presenter双方が実装するインターフェイス
 */
interface MainContract {

    interface View : ScopeProvider {
        /**
         * ツールバーの初期化
         */
        fun initToolBar()

        /**
         * プログレスバーを表示
         */
        fun showProgress()

        /**
         * プログレスバーを非表示
         */
        fun hideProgress()

        /**
         * タブレイアウトにマスターデータをセットする
         *
         * @param masters マスターデータのリスト
         */
        fun setTab(masters: List<Master>)
    }

    interface Presenter {
        /**
         * 画面表示を開始する
         */
        fun start()
    }
}