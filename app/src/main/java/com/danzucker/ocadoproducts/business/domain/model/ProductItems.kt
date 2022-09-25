package com.danzucker.ocadoproducts.business.domain.model

import com.google.gson.annotations.SerializedName

data class ProductItems(
    val id : Int,
    val price : String,
    val title : String,
    val size : String,
    val imageUrl : String,
)
