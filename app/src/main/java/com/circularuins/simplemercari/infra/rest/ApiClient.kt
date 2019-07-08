package com.circularuins.simplemercari.infra.rest

import com.circularuins.simplemercari.infra.data.Item
import com.circularuins.simplemercari.infra.data.Master
import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {

    @GET("master")
    fun getMaster(): Single<List<Master>>

    @GET("all")
    fun getAll(): Single<List<Item>>

    @GET("men")
    fun getMen(): Single<List<Item>>

    @GET("women")
    fun getWomen(): Single<List<Item>>
}