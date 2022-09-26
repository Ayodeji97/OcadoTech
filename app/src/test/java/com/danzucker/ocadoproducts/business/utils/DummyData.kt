package com.danzucker.ocadoproducts.business.utils

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductDto
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.domain.model.ProductItems
import com.google.gson.annotations.SerializedName

object DummyData {

    val error_text = "Exception thrown"
    val exception = Exception(error_text)

    val productItemsDto = ProductItemsDto(
        id = 23526,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV",
        description = "fdkbsn",
        allergyInformation = "svsdgs"
    )

    val productItems = ProductItems(
        id = 23526,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV"
    )

    val productItems2 = ProductItems(
        id = 235,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV"
    )

    val productItems3 = ProductItems(
        id = 23570,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV"
    )


    val productItemsDto2 = ProductItemsDto(
        id = 23520,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV",
        description = "fdkbsn",
        allergyInformation = "svsdgs"
    )

    val productDto = ProductDto(
        tag = "Fresh > Fruit > Bananas",
        items = listOf(productItemsDto, productItemsDto2)
    )

    val clustersDto = ClustersDto(
        clusters = listOf(productDto, productDto)
    )


    val productsItemEntity = ProductsItemEntity(
        id = 23520,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV",
    )

    val productsItemEntity2 = ProductsItemEntity(
        id = 235,
        price = "4.5",
        title = "See me",
        size = "DVSVS",
        imageUrl = "ASVSV",
    )

}