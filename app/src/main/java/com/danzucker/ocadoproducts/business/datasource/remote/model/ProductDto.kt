package com.danzucker.ocadoproducts.business.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("tag")
    val tag : String,
    @SerializedName("items")
    val items : List<ProductItemsDto>
)
