package com.circularuins.simplemercari.domain.model

data class Item(

    val id: String,

    val name: String,

    val status: Status,

    val numLikes: Int,

    val numComments: Int,

    val price: Int,

    val photo: String
) {
    enum class Status(val rawValue: String) {
        OnSale("on_sale"),
        SoldOut("sold_out"),
        None("");

        companion object {
            fun typeOf(str: String): Status =
                values().first {it.rawValue == str}
        }
    }
}