package com.circularuins.simplemercari.app.mapper

import com.circularuins.simplemercari.domain.model.Item

fun Item.convert(): com.circularuins.simplemercari.app.viewdata.Item {
    return com.circularuins.simplemercari.app.viewdata.Item(
        id,
        name,
        status, // TODO: モデルでEnumへ変換する。ここはbooleanにする
        numLikes,
        numComments,
        "$ $price", // TODO: カンマ入りの文字列にする。確かメソッドあったような・・
        photo
    )
}