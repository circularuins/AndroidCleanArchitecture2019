package com.circularuins.simplemercari.domain.model

/**
 * 商品データ（モデル）
 */
data class Item(

    val id: String,

    val name: String,

    val status: Status,

    val numLikes: Int,

    val numComments: Int,

    val price: Int,

    val photo: String
) {
    /**
     * 販売状況のステータスを管理
     */
    enum class Status(val rawValue: String) {
        OnSale("on_sale"),
        SoldOut("sold_out"),
        None("");

        companion object {
            /**
             * 文字列から対応Enumを判定する
             */
            fun typeOf(str: String): Status =
                values().first {it.rawValue == str}
        }
    }
}