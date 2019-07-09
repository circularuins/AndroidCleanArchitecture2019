package com.circularuins.simplemercari.infra.rest

import com.circularuins.simplemercari.infra.data.Item
import com.circularuins.simplemercari.infra.data.Master
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    companion object {
        const val BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"
    }

    @GET("master.json")
    fun getMaster(): Single<List<Master>>

    @GET("{type}.json")
    fun getItems(@Path("type") type: String): Single<List<Item>>
}