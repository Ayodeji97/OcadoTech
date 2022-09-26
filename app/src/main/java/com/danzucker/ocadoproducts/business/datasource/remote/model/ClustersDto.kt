package com.danzucker.ocadoproducts.business.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ClustersDto(
    @SerializedName("clusters")
    val clusters : List<ProductDto>
)
