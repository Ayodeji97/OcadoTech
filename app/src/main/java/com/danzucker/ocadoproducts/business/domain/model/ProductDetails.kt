package com.danzucker.ocadoproducts.business.domain.model

data class ProductDetails(
    val id : Int,
    val price : String,
    val title : String,
    val size : String,
    val imageUrl : String,
    val description : String,
    val allergyInformation : String
)
