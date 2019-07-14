package com.circularuins.simplemercari.infra.mapper

import com.circularuins.simplemercari.infra.data.Item

/**
 * Item(Data) -> Item(Model)への変換ロジック
 */
fun Item.convert(): com.circularuins.simplemercari.domain.model.Item {
    return com.circularuins.simplemercari.domain.model.Item(
        id ?: "",
        name ?: "",
        convertToStatus(),
        numLikes ?: 0,
        numComments ?: 0,
        price ?: 0,
        photo ?: ""
    )
}

/**
 * 文字列表記のステータスをEnumに変換する
 */
fun Item.convertToStatus(): com.circularuins.simplemercari.domain.model.Item.Status {
    return status?.let {
        com.circularuins.simplemercari.domain.model.Item.Status.typeOf(it)
    } ?: com.circularuins.simplemercari.domain.model.Item.Status.typeOf("")
}
