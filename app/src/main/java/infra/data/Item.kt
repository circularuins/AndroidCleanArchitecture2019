package infra.data

import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("num_likes")
    val numLikes: Int?,

    @SerializedName("num_comments")
    val numComments: Int?,

    @SerializedName("price")
    val price: Int?,

    @SerializedName("photo")
    val photo: String?
)