package com.circularuins.simplemercari.infra.rest

import com.circularuins.simplemercari.infra.data.Item
import com.circularuins.simplemercari.infra.data.Master
import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {

    @GET("/master.json")
    fun getMaster(): Single<List<Master>>

    @GET("/all.json")
    fun getAll(): Single<List<Item>>

    @GET("/men.json")
    fun getMen(): Single<List<Item>>

    @GET("/women.json")
    fun getWomen(): Single<List<Item>>
}