package com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper

import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class ProductsItemDtoMapper @Inject constructor() : BaseDtoMapper<List<ProductItemsDto>, List<ProductsItemEntity>> {
    override fun transformToEntity(type: List<ProductItemsDto>): List<ProductsItemEntity> =
        type.map { productItemsDto ->
            ProductsItemEntity(
                id = productItemsDto.id,
                price = productItemsDto.price,
                title = productItemsDto.title,
                size = productItemsDto.size,
                imageUrl = productItemsDto.imageUrl
            )
        }
}