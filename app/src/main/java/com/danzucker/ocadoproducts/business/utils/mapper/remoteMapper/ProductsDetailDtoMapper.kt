package com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper

import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.domain.model.ProductDetails
import com.danzucker.ocadoproducts.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class ProductsDetailDtoMapper @Inject constructor() :
    BaseDtoMapper<ProductItemsDto, ProductDetails> {
    override fun transformToEntity(type: ProductItemsDto): ProductDetails =
        ProductDetails(
            id = type.id ?: 0,
            price = type.price ?: "",
            title = type.title ?: "",
            size = type.size ?: "",
            imageUrl = type.imageUrl ?: "",
            description = type.description ?: "",
            allergyInformation = type.allergyInformation ?: ""
        )
}