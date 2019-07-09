package com.circularuins.simplemercari.domain.model

data class Item(

    val id: String,

    val name: String,

    val status: String,

    val numLikes: Int,

    val numComments: Int,

    val price: Int,

    val photo: String
)