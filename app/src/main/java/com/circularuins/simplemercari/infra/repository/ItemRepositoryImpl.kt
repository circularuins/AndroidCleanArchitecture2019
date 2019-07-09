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

    override fun getAll(): Single<List<Item>> = client.getAll()
        .map { it.map { item -> item.convert() } }
        .subscribeOn(subscribe)
        .observeOn(observe)

    override fun getMen(): Single<List<Item>> = client.getMen()
        .map { it.map { item -> item.convert() } }
        .subscribeOn(subscribe)
        .observeOn(observe)

    override fun getWomen(): Single<List<Item>> = client.getWomen()
        .map { it.map { item -> item.convert() } }
        .subscribeOn(subscribe)
        .observeOn(observe)
}