package com.danzucker.ocadoproducts.business.repository

import android.util.Log
import com.danzucker.ocadoproducts.business.datasource.cache.cachesource.OcadoProductsCacheSource
import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import com.danzucker.ocadoproducts.business.datasource.remote.remotesource.OcadoProductsRemoteSource
import com.danzucker.ocadoproducts.business.utils.Result
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsItemDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OcadoProductsRepositoryImpl @Inject constructor(
    private val ocadoProductsRemoteSource: OcadoProductsRemoteSource,
    private val ocadoProductsCacheSource: OcadoProductsCacheSource,
    private val productsItemDtoMapper: ProductsItemDtoMapper
) : OcadoProductsRepository {
    override suspend fun getProducts(): Flow<Result<List<ProductsItemEntity>>> =
        flow {
            when (val response = ocadoProductsRemoteSource.getProducts()) {
                is Result.Success -> {
                    var productListItems : List<ProductsItemEntity> = listOf()
                    if (response.data != null) {
                        val productItemsDto = response.data.clusters
                        productItemsDto.forEach {
                             productListItems = productsItemDtoMapper.transformToEntity(it.items)
                            ocadoProductsCacheSource.saveProductItems(productListItems)
                        }
                        emit(Result.Success(productListItems))
                    } else {
                        emit(Result.Success(null))
                    }
                }

                is Result.Error -> {
                    emit(Result.Error(response.exception))
                }
            }
        }
}