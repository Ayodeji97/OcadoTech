package com.danzucker.ocadoproducts.business.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ProductItemsDto(
    @SerializedName("id")
    val id : Int? = null,
    @SerializedName("price")
    val price : String? = null,
    @SerializedName("title")
    val title : String? = null,
    @SerializedName("size")
    val size : String? = null,
    @SerializedName("imageUrl")
    val imageUrl : String? = null,
    @SerializedName("description")
    val description : String? = null,
    @SerializedName("allergyInformation")
    val allergyInformation : String? = null,
)
