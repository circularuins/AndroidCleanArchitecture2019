package com.circularuins.simplemercari.infra.repository

import com.circularuins.simplemercari.domain.model.Item
import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.infra.mapper.convert
import com.circularuins.simplemercari.infra.rest.ApiClient
import io.reactivex.Scheduler
import io.reactivex.Single

class ItemRepositoryImpl(private val client: ApiClient,
    private val subscribe: Scheduler,
    private val observe: Scheduler) : ItemRepository {

    override fun getItems(type: String): Single<List<Item>> = client.getItems(type)
        .map { it.map { item -> item.convert() } }
        .subscribeOn(subscribe)
        .observeOn(observe)
}