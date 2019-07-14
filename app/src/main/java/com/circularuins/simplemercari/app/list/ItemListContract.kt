package com.circularuins.simplemercari.app.list

import com.circularuins.simplemercari.app.viewdata.ListViewData
import com.uber.autodispose.ScopeProvider

/**
 * 商品一覧画面のView/Presenter双方が実装するインターフェイス
 */
interface ItemListContract {

    interface View : ScopeProvider {
        /**
         * プログレスバーを表示
         */
        fun showProgress()

        /**
         * プログレスバーを非表示
         */
        fun hideProgress()

        /**
         * リストに商品データをセットする
         *
         * @param items 商品ビューデータのリスト
         */
        fun setList(items: List<ListViewData>)
    }

    interface Presenter {
        /**
         * 画面表示を開始する
         *
         * @param requestType 表示するリストのタイプ
         */
        fun start(requestType: String)
    }
}