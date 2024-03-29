package com.circularuins.simplemercari.app.viewdata

/**
 * 商品データ（表示用）
 */
data class Item(

    val id: String,

    val name: String,

    val isSoldOut: Boolean,

    val numLikes: Int,

    val numComments: Int,

    val price: String,

    val photo: String
) : ListViewData {
    override val type: ListViewType
        get() = ListViewType.Item
}