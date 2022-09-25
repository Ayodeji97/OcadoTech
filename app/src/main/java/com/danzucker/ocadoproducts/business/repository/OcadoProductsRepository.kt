package com.danzucker.ocadoproducts.business.repository

import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.utils.Result
import kotlinx.coroutines.flow.Flow

interface OcadoProductsRepository {
    suspend fun getProducts() : Flow<Result<List<ProductsItemEntity>>>
    suspend fun getProductById(id : String) : Flow<Result<ProductItemsDto>>
}