package infra.data

import com.google.gson.annotations.SerializedName

data class Master(

    @SerializedName("name")
    val name: String?,

    @SerializedName("data")
    val data: String?
)