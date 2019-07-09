package com.circularuins.simplemercari.infra.mapper

import com.circularuins.simplemercari.infra.data.Item

fun Item.convert(): com.circularuins.simplemercari.domain.model.Item {
    return com.circularuins.simplemercari.domain.model.Item(
        id ?: "",
        name ?: "",
        status ?: "",
        numLikes ?: 0,
        numComments ?: 0,
        price ?: 0,
        photo ?: ""
    )
}