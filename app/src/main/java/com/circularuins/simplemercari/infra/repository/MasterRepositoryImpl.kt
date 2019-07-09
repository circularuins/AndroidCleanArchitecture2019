package com.circularuins.simplemercari.infra.repository

import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.infra.mapper.convert
import com.circularuins.simplemercari.infra.rest.ApiClient
import io.reactivex.Scheduler
import io.reactivex.Single

class MasterRepositoryImpl(private val client: ApiClient,
    private val subscribe: Scheduler,
    private val observe: Scheduler) : MasterRepository {

    override fun getMaster(): Single<List<Master>> = client.getMaster()
        .map { it.map { master -> master.convert() } }
        .subscribeOn(subscribe)
        .observeOn(observe)
}