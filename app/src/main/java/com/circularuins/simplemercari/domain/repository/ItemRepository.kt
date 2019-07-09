package com.circularuins.simplemercari.domain.repository

import com.circularuins.simplemercari.domain.model.Item
import io.reactivex.Single

interface ItemRepository {

    fun getItems(type: String): Single<List<Item>>
}