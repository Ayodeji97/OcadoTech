package com.danzucker.ocadoproducts.business.utils.mapper.cacheMapper

import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import com.danzucker.ocadoproducts.business.domain.model.ProductItems
import com.danzucker.ocadoproducts.business.utils.mapper.base.BaseEntityMapper
import javax.inject.Inject

class ProductItemsEntityMapper @Inject constructor(): BaseEntityMapper<List<ProductsItemEntity>, List<ProductItems>> {
    override fun transformToDomain(type: List<ProductsItemEntity>): List<ProductItems> =
        type.map { productsItemEntity ->
            ProductItems(
                id = productsItemEntity.id,
                price = productsItemEntity.price,
                title = productsItemEntity.title,
                size = productsItemEntity.size,
                imageUrl = productsItemEntity.imageUrl
            )
        }

}