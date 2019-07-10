package com.circularuins.simplemercari.app.viewdata

data class Item(

    val id: String,

    val name: String,

    val isSoldOut: Boolean,

    val numLikes: Int,

    val numComments: Int,

    val price: String,

    val photo: String
)