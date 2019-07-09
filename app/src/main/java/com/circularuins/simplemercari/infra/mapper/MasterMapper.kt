package com.circularuins.simplemercari.infra.mapper

import com.circularuins.simplemercari.infra.data.Master

fun Master.convert(): com.circularuins.simplemercari.domain.model.Master {
    return com.circularuins.simplemercari.domain.model.Master(
        name ?: "",
        data ?: ""
    )
}