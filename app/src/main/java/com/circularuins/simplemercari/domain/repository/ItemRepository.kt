package com.circularuins.simplemercari.domain.repository

import com.circularuins.simplemercari.domain.model.Item
import io.reactivex.Single

interface ItemRepository {

    fun getAll(): Single<List<Item>>

    fun getMen(): Single<List<Item>>

    fun getWomen(): Single<List<Item>>
}