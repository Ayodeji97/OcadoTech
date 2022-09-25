package com.danzucker.ocadoproducts.business.domain.usecase

import android.util.Log
import com.danzucker.ocadoproducts.business.datasource.cache.cachesource.OcadoProductsCacheSource
import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import com.danzucker.ocadoproducts.business.domain.model.ProductItems
import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepository
import com.danzucker.ocadoproducts.business.utils.Result
import com.danzucker.ocadoproducts.business.utils.mapper.cacheMapper.ProductItemsEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class OcadoProductsUseCase @Inject constructor(
    private val ocadoProductsRepository: OcadoProductsRepository,
    private val ocadoProductsCacheSource: OcadoProductsCacheSource,
    private val productEntityMapper: ProductItemsEntityMapper
) {
    suspend operator fun invoke(): List<ProductItems> {
        var productItemsEntity: List<ProductsItemEntity> = listOf()
        ocadoProductsRepository.getProducts().collect {
            when (it) {
                is Result.Success -> {
                    productItemsEntity = if (it.data.isNullOrEmpty()) {
                        listOf()
                    } else {
                        ocadoProductsCacheSource.getAllProductItems()
                    }
                }
                is Result.Error -> {
                    productItemsEntity = listOf()
                }
            }
        }
        return productEntityMapper.transformToDomain(productItemsEntity)
    }
}