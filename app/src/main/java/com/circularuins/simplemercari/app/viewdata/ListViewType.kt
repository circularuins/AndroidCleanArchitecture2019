package com.circularuins.simplemercari.app.viewdata

/**
 * リストに表示するデータの種類
 */
enum class ListViewType(val rawValue: Int) {
    Item(1); // 商品

    companion object {
        /**
         * 表示データの型を取得する
         */
        fun typeOf(num: Int): ListViewType = values().first {it.rawValue == num}
    }
}