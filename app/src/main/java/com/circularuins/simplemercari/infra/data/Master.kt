package com.circularuins.simplemercari.infra.data

import com.google.gson.annotations.SerializedName

/**
 * マスターデータ
 */
data class Master(

    @SerializedName("name")
    val name: String?,

    @SerializedName("data")
    val data: String?
)